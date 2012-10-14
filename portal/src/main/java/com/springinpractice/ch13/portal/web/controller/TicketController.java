package com.springinpractice.ch13.portal.web.controller;

import java.util.Date;

import javax.inject.Inject;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springinpractice.ch13.portal.integration.gateway.TicketGateway;
import com.springinpractice.ch13.portal.integration.resource.TicketResource;
import com.springinpractice.ch13.portal.model.User;
import com.springinpractice.ch13.portal.web.util.ModelKeys;
import com.springinpractice.ch13.portal.web.util.ViewKeys;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Controller
@RequestMapping("/tickets")
public class TicketController {
	private static final Logger log = LoggerFactory.getLogger(TicketController.class);
	
	@Inject private TicketGateway ticketGateway;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String getNewTicketForm(Model model) {
		log.debug("Getting new ticket form");
		model.addAttribute(ModelKeys.TICKET, new TicketResource());
		return prepareNewTicketForm(model);
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String postTicket(
			@ModelAttribute(ModelKeys.TICKET) @Valid TicketResource ticket,
			BindingResult result,
			Model model) {
		
		log.debug("ticket.category={}", ticket.getCategory());
		
		if (result.hasErrors()) { return prepareNewTicketForm(model); }
		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ticket.setUsername(user.getUsername());
		ticket.setDateCreated(new Date());
		ticketGateway.createTicket(ticket);
		
		return ViewKeys.REDIRECT_TO_HOME;
	}
	
	private String prepareNewTicketForm(Model model) {
		model.addAttribute(ModelKeys.TICKET_CATEGORY_LIST, ticketGateway.findTicketCategories());
		return ViewKeys.NEW_TICKET;
	}
}
