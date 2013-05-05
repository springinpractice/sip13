/* 
 * Copyright (c) 2013 Manning Publications Co.
 * 
 * Book: http://manning.com/wheeler/
 * Blog: http://springinpractice.com/
 * Code: https://github.com/springinpractice
 */
package com.springinpractice.ch13.portal.integration.resource;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.hateoas.Resources;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TicketCategoryResources extends Resources<TicketCategoryResource> { }
