package com.springinpractice.ch13.helpdesk.web.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springinpractice.ch13.helpdesk.dto.TicketDto;
import com.springinpractice.ch13.helpdesk.model.Ticket;
import com.springinpractice.ch13.helpdesk.model.TicketStatus;
import com.springinpractice.ch13.helpdesk.repo.TicketRepository;
import com.springinpractice.ch13.helpdesk.repo.TicketStatusRepository;
import com.springinpractice.ch13.helpdesk.web.navigation.Paths;

/**
 * Web controller for help desk tickets.
 * 
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Controller
public class TicketController implements InitializingBean {
	private static final String VN_TICKETS_HOME = "ticketsHome";
	private static final String VN_NEW_TICKET = "newTicket";
	private static final String VN_REDIRECT_TO_TICKET_CREATED = "redirect:/tickets?status=created";
	
	private static final Logger log = LoggerFactory.getLogger(TicketController.class);
	
	@Inject private TicketRepository ticketRepo;
	@Inject private TicketStatusRepository ticketStatusRepo;
	
	private TicketStatus openStatus;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		this.openStatus = ticketStatusRepo.findByKey("open");
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	@RequestMapping(value = Paths.TICKETS, method = RequestMethod.GET)
	public String getTicketsHome(Model model) {
		model.addAttribute(ticketRepo.findAll());
		return VN_TICKETS_HOME;
	}
	
	@RequestMapping(value = Paths.NEW_TICKET, method = RequestMethod.GET)
	public String getNewTicketForm(Model model) {
		model.addAttribute(new Ticket());
		return VN_NEW_TICKET;
	}
	
	@RequestMapping(value = Paths.TICKETS, method = RequestMethod.POST)
	public String postTicket(@ModelAttribute("ticket") @Valid TicketDto ticketDto, BindingResult result) {
		log.debug("Creating ticket: {}", ticketDto);
		
		if (result.hasErrors()) { return VN_NEW_TICKET; }
		
		Ticket ticket = new Ticket();
		ticket.setStatus(openStatus);
		ticket.setUserName(ticketDto.getUserName());
		ticket.setUserEmail(ticketDto.getUserEmail());
		ticket.setDescription(ticketDto.getDescription());
		ticketRepo.save(ticket);
		
		return VN_REDIRECT_TO_TICKET_CREATED;
	}
}
