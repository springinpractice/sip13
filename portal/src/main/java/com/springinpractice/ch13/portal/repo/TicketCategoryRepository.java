package com.springinpractice.ch13.portal.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springinpractice.ch13.portal.model.TicketCategory;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public interface TicketCategoryRepository extends JpaRepository<TicketCategory, Long> {
}
