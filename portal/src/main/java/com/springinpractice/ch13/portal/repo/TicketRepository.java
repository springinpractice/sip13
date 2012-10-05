package com.springinpractice.ch13.portal.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springinpractice.ch13.portal.model.Ticket;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
