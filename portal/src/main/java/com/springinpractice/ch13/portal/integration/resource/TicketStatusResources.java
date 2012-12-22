package com.springinpractice.ch13.portal.integration.resource;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.hateoas.Resources;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TicketStatusResources extends Resources<TicketStatusResource> { }
