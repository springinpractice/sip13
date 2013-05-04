/* 
 * Copyright (c) 2013 Manning Publications Co.
 * 
 * Book: http://manning.com/wheeler/
 * Blog: http://springinpractice.com/
 * Code: https://github.com/springinpractice
 */
package com.springinpractice.ch13.helpdesk.web.util;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class ModelKeys {
	
	/** Map [key -&gt; node], containing all nodes. */
	public static final String NODES = "nodes";
	
	/** Nodes in the top-level nav bar */
	public static final String TOP_LEVEL_NODES = "topLevelNodes";
	
	/** Current node */
	public static final String NODE = "node";
	
	/** Current node's key */
	public static final String NODE_KEY = "nodeKey";
	
	public static final String TICKET = "ticket";
	
	public static final String TICKETS = "ticketList";
	
	public static final String TICKET_CATEGORIES = "ticketCategoryList";
	
	public static final String CUSTOMER = "customer";
	
	/** Map [username -&gt; customer] */
	public static final String CUSTOMER_MAP = "customerMap";
}
