package com.springinpractice.ch13.helpdesk.web;

import javax.inject.Inject;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springinpractice.ch13.helpdesk.dao.TicketDao;
import com.springinpractice.ch13.helpdesk.dto.TicketDto;
import com.springinpractice.ch13.helpdesk.model.Ticket;
import com.springinpractice.ch13.helpdesk.service.TicketService;

/**
 * Web controller for help desk tickets.
 * 
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Controller
@RequestMapping("/tickets")
public class TicketController {
	private static final String VN_TICKETS = "tickets/ticketList";
	private static final String VN_TICKET_CREATOR = "tickets/ticketCreator";
	private static final String VN_REDIRECT_TO_TICKET_CREATED = "redirect:/tickets/ticketcreated.html";
	private static final String VN_TICKET_CREATED = "tickets/ticketCreated";
	
	private static final Logger log = LoggerFactory.getLogger(TicketController.class);
	
	@Inject private TicketDao ticketDao;
	@Inject private TicketService ticketService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	/**
	 * Displays the new ticket entry form.
	 * 
	 * @param model model
	 * @return logical view name
	 */
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String getTicketCreator(Model model) {
		model.addAttribute(new Ticket());
		return VN_TICKET_CREATOR;
	}
	
	/**
	 * Processes new ticket submissions.
	 * 
	 * @param ticketDto ticket
	 * @param result result
	 * @return logical view name
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String postTicket(@ModelAttribute("ticket") @Valid TicketDto ticketDto, BindingResult result) {
		log.debug("Creating ticket: {}", ticketDto);
		
		if (result.hasErrors()) { return VN_TICKET_CREATOR; }
		
		Ticket ticket = new Ticket();
		ticket.setUserName(ticketDto.getUserName());
		ticket.setUserEmail(ticketDto.getUserEmail());
		ticket.setDescription(ticketDto.getDescription());
		
		ticketService.createTicket(ticket);
		return VN_REDIRECT_TO_TICKET_CREATED;
	}
	
	/**
	 * Displays the ticket list page.
	 * 
	 * @param model model
	 * @return logical view name
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String getTickets(Model model) {
		model.addAttribute(ticketDao.findAll());
		return VN_TICKETS;
	}
	
	/**
	 * Displays a success page following the creation of a new ticket.
	 * 
	 * @return logical view name
	 */
	@RequestMapping(value = "/ticketcreated", method = RequestMethod.GET)
	public String getTicketCreated() {
		return VN_TICKET_CREATED;
	}
}
