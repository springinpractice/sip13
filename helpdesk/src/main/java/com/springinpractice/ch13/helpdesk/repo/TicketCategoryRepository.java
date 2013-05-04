/* 
 * Copyright (c) 2013 Manning Publications Co.
 * 
 * Book: http://manning.com/wheeler/
 * Blog: http://springinpractice.com/
 * Code: https://github.com/springinpractice
 */
package com.springinpractice.ch13.helpdesk.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springinpractice.ch13.helpdesk.model.TicketCategoryEntity;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public interface TicketCategoryRepository extends JpaRepository<TicketCategoryEntity, Long> {
}
