/* 
 * Copyright (c) 2013 Manning Publications Co.
 * 
 * Book: http://manning.com/wheeler/
 * Blog: http://springinpractice.com/
 * Code: https://github.com/springinpractice
 */
package com.springinpractice.ch13.portal.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.Formatter;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Component
public class LinkFormatter implements Formatter<Link> {
	private static final Logger log = LoggerFactory.getLogger(LinkFormatter.class);
	
	@Override
	public String print(Link link, Locale locale) {
		log.debug("Printing Link: {}", link);
		return link.getHref();
	}

	@Override
	public Link parse(String href, Locale locale) throws ParseException {
		log.debug("Parsing Link: href={}", href);
		return new Link(href);
	}
}
