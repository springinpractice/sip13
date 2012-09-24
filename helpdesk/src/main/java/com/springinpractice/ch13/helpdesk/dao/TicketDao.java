package com.springinpractice.ch13.helpdesk.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springinpractice.ch13.helpdesk.model.Ticket;

/**
 * TicketDto data access object.
 * 
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public interface TicketDao extends JpaRepository<Ticket, Long> {
}
