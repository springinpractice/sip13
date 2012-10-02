package com.springinpractice.ch13.helpdesk.web.navigation;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Component
public class SitemapFilter implements Filter {
	private static final Logger log = LoggerFactory.getLogger(SitemapFilter.class);
	
	@Inject private Sitemap sitemap;

	@Override
	public void init(FilterConfig conf) throws ServletException { }

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		
		String path = httpRequest.getServletPath();
		log.debug("Filtering request: {}", path);
		
		NodeTemplate node = sitemap.findNodeByRequest(httpRequest);
		if (node != null) {
			
			// Global nodes
			httpRequest.setAttribute("nodes", sitemap.getNodeMap());
			httpRequest.setAttribute("topLevelNodes", sitemap.getTopLevelNodes());
			
			// Current node
			httpRequest.setAttribute("node", node);
			httpRequest.setAttribute("nodeKey", node.getKey());
		}
		
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() { }
}
