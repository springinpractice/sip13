/* 
 * Copyright (c) 2013 Manning Publications Co.
 * 
 * Book: http://manning.com/wheeler/
 * Blog: http://springinpractice.com/
 * Code: https://github.com/springinpractice
 */
package com.springinpractice.ch13.portal.integration.gateway;

import java.util.List;

import com.springinpractice.ch13.portal.integration.resource.TicketCategoryResource;
import com.springinpractice.ch13.portal.integration.resource.TicketResource;
import com.springinpractice.ch13.portal.integration.resource.TicketStatusResource;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public interface TicketGateway {
	
	void createTicket(TicketResource ticket);
	
	TicketStatusResource findOpenTicketStatus();
	
	List<TicketCategoryResource> findTicketCategories();
	
	TicketCategoryResource findTicketCategory(String uri);
}
