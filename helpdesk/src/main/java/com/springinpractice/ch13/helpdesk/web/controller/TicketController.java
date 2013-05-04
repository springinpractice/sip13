/* 
 * Copyright (c) 2013 Manning Publications Co.
 * 
 * Book: http://manning.com/wheeler/
 * Blog: http://springinpractice.com/
 * Code: https://github.com/springinpractice
 */
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

import com.springinpractice.ch13.helpdesk.model.TicketEntity;
import com.springinpractice.ch13.helpdesk.model.TicketStatusEntity;
import com.springinpractice.ch13.helpdesk.model.TicketStatusKeys;
import com.springinpractice.ch13.helpdesk.portal.model.CustomerEntity;
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
	
	private TicketStatusEntity openStatus;
	
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
		List<TicketEntity> tickets = ticketRepo.findAll();
		
		// Now we're using the tickets as DTOs for the JSP. So populate them with customer info for rendering.
		Map<String, CustomerEntity> customerMap = buildCustomerMap(tickets);
		for (TicketEntity ticket : tickets) {
			String username = ticket.getCustomerUsername();
			if (username != null) {
				CustomerEntity customer = customerMap.get(username);
				ticket.setCustomerEmail(customer.getEmail());
				ticket.setCustomerFullName(customer.getFirstNameLastName());
			}
		}
		
		model.addAttribute(ModelKeys.TICKETS, tickets);
		return ViewKeys.TICKETS_HOME;
	}
	
	private Map<String, CustomerEntity> buildCustomerMap(List<TicketEntity> tickets) {
		Map<String, CustomerEntity> customerMap = new HashMap<String, CustomerEntity>();
		
		List<String> usernames = new ArrayList<String>();
		for (TicketEntity ticket : tickets) {
			// Check for usernames, because tickets sent via e-mail don't have them. See recipe 13.4.
			String username = ticket.getCustomerUsername();
			if (username != null) { usernames.add(username); }
		}
		
		List<CustomerEntity> customers = customerRepo.findByUsernameIn(usernames);
		for (CustomerEntity customer : customers) { customerMap.put(customer.getUsername(), customer); }
		
		return customerMap;
	}
	
	
	// =================================================================================================================
	// New ticket
	// =================================================================================================================
	
	@RequestMapping(value = "/tickets/new", method = RequestMethod.GET)
	public String getNewTicketForm(Model model) {
		model.addAttribute(ModelKeys.TICKET, new TicketEntity());
		return prepareNewTicketForm(model);
	}
	
	@RequestMapping(value = "/tickets", method = RequestMethod.POST)
	public String postTicket(
			@ModelAttribute(ModelKeys.TICKET) @Valid TicketEntity ticket,
			BindingResult result,
			Model model) {
		
		log.debug("Creating ticket: {}", ticket);
		
		// Additional customer username validation, if needed
		if (!(result.hasFieldErrors("customerUsername"))) {
			String username = ticket.getCustomerUsername();
			CustomerEntity customer = customerRepo.findByUsername(username);
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
		model.addAttribute(ModelKeys.TICKET_CATEGORIES, ticketCategoryRepo.findAll());
		return ViewKeys.NEW_TICKET;
	}
	
	
	// =================================================================================================================
	// TicketEntity details
	// =================================================================================================================
	
	@RequestMapping(value = "/tickets/{id}", method = RequestMethod.GET)
	public String getTicketDetails(@PathVariable Long id, Model model) {
		TicketEntity ticket = ticketRepo.findOne(id);
		
		// We do this because we have to handle the no-username case.
		String username = ticket.getCustomerUsername();
		if (username != null) {
			CustomerEntity customer = customerRepo.findByUsername(username);
			ticket.setCustomerEmail(customer.getEmail());
			ticket.setCustomerFullName(customer.getFirstNameLastName());
		}
		
		model.addAttribute(ModelKeys.TICKET, ticket);
		return ViewKeys.TICKET_DETAILS;
	}
}
