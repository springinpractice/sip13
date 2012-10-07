package com.springinpractice.ch13.helpdesk.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springinpractice.ch13.helpdesk.model.Ticket;
import com.springinpractice.ch13.helpdesk.model.TicketStatus;
import com.springinpractice.ch13.helpdesk.model.TicketStatusKeys;
import com.springinpractice.ch13.helpdesk.portal.model.Customer;
import com.springinpractice.ch13.helpdesk.portal.repo.CustomerRepository;
import com.springinpractice.ch13.helpdesk.repo.TicketCategoryRepository;
import com.springinpractice.ch13.helpdesk.repo.TicketRepository;
import com.springinpractice.ch13.helpdesk.repo.TicketStatusRepository;
import com.springinpractice.ch13.helpdesk.web.util.ModelKeys;
import com.springinpractice.ch13.helpdesk.web.util.ViewKeys;

/**
 * Web controller for help desk tickets.
 * 
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Controller
public class TicketController implements InitializingBean {
	private static final Logger log = LoggerFactory.getLogger(TicketController.class);
	
	@Inject private TicketRepository ticketRepo;
	@Inject private TicketCategoryRepository ticketCategoryRepo;
	@Inject private TicketStatusRepository ticketStatusRepo;
	@Inject private CustomerRepository customerRepo;
	
	private TicketStatus openStatus;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		this.openStatus = ticketStatusRepo.findByKey(TicketStatusKeys.OPEN);
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	
	// =================================================================================================================
	// Tickets home
	// =================================================================================================================
	
	@RequestMapping(value = "/tickets", method = RequestMethod.GET)
	public String getTicketsHome(Model model) {
		List<Ticket> tickets = ticketRepo.findAll();
		model.addAttribute(tickets);
		model.addAttribute(ModelKeys.CUSTOMER_MAP, buildCustomerMap(tickets));
		return ViewKeys.TICKETS_HOME;
	}
	
	private Map<String, Customer> buildCustomerMap(List<Ticket> tickets) {
		Map<String, Customer> customerMap = new HashMap<String, Customer>();
		
		List<String> usernames = new ArrayList<String>();
		for (Ticket ticket : tickets) { usernames.add(ticket.getCustomerUsername()); }
		
		List<Customer> customers = customerRepo.findByUsernameIn(usernames);
		for (Customer customer : customers) { customerMap.put(customer.getUsername(), customer); }
		
		return customerMap;
	}
	
	
	// =================================================================================================================
	// New ticket
	// =================================================================================================================
	
	@RequestMapping(value = "/tickets/new", method = RequestMethod.GET)
	public String getNewTicketForm(Model model) {
		model.addAttribute(new Ticket());
		return prepareNewTicketForm(model);
	}
	
	@RequestMapping(value = "/tickets", method = RequestMethod.POST)
	public String postTicket(@ModelAttribute @Valid Ticket ticket, BindingResult result, Model model) {
		log.debug("Creating ticket: {}", ticket);
		
		// Additional customer username validation, if needed
		if (!(result.hasFieldErrors("customerUsername"))) {
			String username = ticket.getCustomerUsername();
			Customer customer = customerRepo.findByUsername(username);
			if (customer == null) {
				result.rejectValue("customerUsername", "error.noSuchCustomer");
			}
		}
		
		if (result.hasErrors()) { return prepareNewTicketForm(model); }
		
		ticket.setStatus(openStatus);
		ticket.setDateCreated(new Date());
		ticketRepo.save(ticket);
		
		return ViewKeys.REDIRECT_TO_TICKET_CREATED;
	}
	
	private String prepareNewTicketForm(Model model) {
		model.addAttribute(ticketCategoryRepo.findAll());
		return ViewKeys.NEW_TICKET;
	}
	
	
	// =================================================================================================================
	// Ticket details
	// =================================================================================================================
	
	@RequestMapping(value = "/tickets/{id}", method = RequestMethod.GET)
	public String getTicketDetails(@PathVariable Long id, Model model) {
		Ticket ticket = ticketRepo.findOne(id);
		model.addAttribute(ticket);
		model.addAttribute(customerRepo.findByUsername(ticket.getCustomerUsername()));
		return ViewKeys.TICKET_DETAILS;
	}
}
