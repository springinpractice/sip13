/* 
 * Copyright (c) 2013 Manning Publications Co.
 * 
 * Book: http://manning.com/wheeler/
 * Blog: http://springinpractice.com/
 * Code: https://github.com/springinpractice
 */
package com.springinpractice.ch13.portal.web.controller;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
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

import com.springinpractice.ch13.cdm.Customer;
import com.springinpractice.ch13.cdm.Ticket;
import com.springinpractice.ch13.cdm.TicketCategory;
import com.springinpractice.ch13.cdm.TicketStatus;
import com.springinpractice.ch13.portal.integration.gateway.TicketGateway;
import com.springinpractice.ch13.portal.model.User;
import com.springinpractice.ch13.portal.web.util.ModelKeys;
import com.springinpractice.ch13.portal.web.util.ViewKeys;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Controller
@RequestMapping("/tickets")
public class TicketController implements InitializingBean {
	private static final Logger log = LoggerFactory.getLogger(TicketController.class);
	
	@Inject private TicketGateway ticketGateway;
	
	private TicketStatus status;

	@Override
	public void afterPropertiesSet() throws Exception {
		log.info("Getting open ticket status from ticket gateway");
		this.status = ticketGateway.findOpenTicketStatus();
		if (this.status == null) {
			throw new IllegalStateException("Failed to load open ticket status");
		}
		log.debug("ticketStatus={}", status.getName());
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String getNewTicketForm(Model model) {
		log.debug("Getting new ticket form");
		model.addAttribute(ModelKeys.TICKET, new Ticket());
		return prepareNewTicketForm(model);
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String postTicket(
			@ModelAttribute(ModelKeys.TICKET) @Valid Ticket ticket,
			BindingResult result,
			Model model) {
		
		log.debug("ticket={}", ticket);
		log.debug("ticket.category={}", ticket.getCategory());
		
		if (result.hasErrors()) {
			log.debug("Ticket validation error");
			return prepareNewTicketForm(model);
		}
		
		log.debug("Ticket is valid");
		ticket.setStatus(status);
		
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Customer customer = new Customer();
		customer.setUsername(user.getUsername());
		ticket.setCreatedBy(customer);
		ticket.setDateCreated(new Date());
		
		ticketGateway.createTicket(ticket);
		return ViewKeys.REDIRECT_TO_HOME;
	}
	
	private String prepareNewTicketForm(Model model) {
		List<TicketCategory> categories = ticketGateway.findTicketCategories().getElements();
		model.addAttribute(ModelKeys.TICKET_CATEGORY_LIST, categories);
		return ViewKeys.NEW_TICKET;
	}
}
