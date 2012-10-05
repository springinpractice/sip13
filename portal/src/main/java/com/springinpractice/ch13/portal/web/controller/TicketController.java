package com.springinpractice.ch13.portal.web.controller;

import java.util.Date;

import javax.inject.Inject;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springinpractice.ch13.portal.model.Ticket;
import com.springinpractice.ch13.portal.model.User;
import com.springinpractice.ch13.portal.repo.TicketCategoryRepository;
import com.springinpractice.ch13.portal.repo.TicketRepository;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Controller
@RequestMapping("/tickets")
public class TicketController {
	private static final Logger log = LoggerFactory.getLogger(TicketController.class);
	
	private static final String VN_NEW_TICKET = "newTicket";
	private static final String VN_REDIRECT_TO_HOME = "redirect:/";
	
	private static final Sort CATEGORY_SORT = new Sort("name");
	
	@Inject private TicketRepository ticketRepo;
	@Inject private TicketCategoryRepository ticketCategoryRepo;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String getNewTicketForm(Model model) {
		model.addAttribute(new Ticket());
		return prepareNewTicketForm(model);
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String postTicket(@ModelAttribute @Valid Ticket ticket, BindingResult result, Model model) {
		log.debug("ticket.category={}", ticket.getCategory());
		if (result.hasErrors()) { return prepareNewTicketForm(model); }
		
		ticket.setUser((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		ticket.setDateCreated(new Date());
		ticketRepo.save(ticket);
		
		return VN_REDIRECT_TO_HOME;
	}
	
	private String prepareNewTicketForm(Model model) {
		model.addAttribute(ticketCategoryRepo.findAll(CATEGORY_SORT));
		return VN_NEW_TICKET;
	}
}
