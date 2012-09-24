package com.springinpractice.ch13.helpdesk.service.impl;

import java.util.Date;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springinpractice.ch13.helpdesk.dao.TicketDao;
import com.springinpractice.ch13.helpdesk.model.Ticket;
import com.springinpractice.ch13.helpdesk.service.TicketService;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Service
@Transactional
public class TicketServiceImpl implements TicketService {
	private static final Logger log = LoggerFactory.getLogger(TicketServiceImpl.class);
	
	@Inject private TicketDao ticketDao;
	
	@Override
	public void createTicket(Ticket ticket) {
		log.info("ticket={}", ticket);
		ticket.setDateCreated(new Date());
		ticketDao.save(ticket);
	}
}
