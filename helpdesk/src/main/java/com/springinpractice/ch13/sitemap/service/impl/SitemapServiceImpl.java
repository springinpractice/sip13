/* 
 * Copyright (c) 2013 Manning Publications Co.
 * 
 * Book: http://manning.com/wheeler/
 * Blog: http://springinpractice.com/
 * Code: https://github.com/springinpractice
 */
package com.springinpractice.ch13.sitemap.service.impl;

import static org.springframework.util.Assert.notNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springinpractice.ch13.sitemap.model.SiteNode;
import com.springinpractice.ch13.sitemap.model.SiteNodeKeys;
import com.springinpractice.ch13.sitemap.repo.SiteNodeRepository;
import com.springinpractice.ch13.sitemap.service.SitemapService;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Service
@Transactional
public class SitemapServiceImpl implements SitemapService, InitializingBean {
	@Inject private SiteNodeRepository siteNodeRepo;
	
	private Map<String, SiteNode> nodeMap;
	private List<SiteNode> topLevelNodes;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		this.nodeMap = new HashMap<String, SiteNode>();
		List<SiteNode> nodes = siteNodeRepo.findAll();
		for (SiteNode node : nodes) { nodeMap.put(node.getKey(), node); }
		
		// TODO Move these to the database too?
		this.topLevelNodes = new ArrayList<SiteNode>();
		topLevelNodes.add(nodeMap.get(SiteNodeKeys.HOME));
		topLevelNodes.add(nodeMap.get(SiteNodeKeys.TICKETS));
		topLevelNodes.add(nodeMap.get(SiteNodeKeys.KNOWLEDGE_BASE));
		topLevelNodes.add(nodeMap.get(SiteNodeKeys.APPROVALS));
		topLevelNodes.add(nodeMap.get(SiteNodeKeys.TASKS));
		topLevelNodes.add(nodeMap.get(SiteNodeKeys.SURVEYS));
		topLevelNodes.add(nodeMap.get(SiteNodeKeys.REPORTING));
	}
	
	@Override
	public Map<String, SiteNode> getNodeMap() { return nodeMap; }
	
	@Override
	public List<SiteNode> getTopLevelNodes() { return topLevelNodes; }
	
	@Override
	public SiteNode findNodeByKey(String key) {
		notNull(key, "key can't be null");
		return nodeMap.get(key);
	}
	
	@Override
	public SiteNode findNodeByMethodAndPath(String method, String path) {
		notNull(method, "method can't be null");
		notNull(path, "path can't be null");
		
		if ("get".equalsIgnoreCase(method)) {
			// FIXME Need to be able to handle path variables more generally
			// Check out o.s.s.web.util.AntPathRequestMatcher? Don't think that's it, but it might point the direction.
			// See also RequestMappingHandlerMapping
			if (path.matches("/tickets/\\d+")) {
				return siteNodeRepo.findByKey("ticketDetails");
			} else {
				return siteNodeRepo.findByPath(path);
			}
		} else if ("post".equalsIgnoreCase(method)) {
			// Assume that the user submitted an invalid form
			return siteNodeRepo.findByPath(path + "/new");
		} else {
			throw new IllegalArgumentException("Bad request: method=" + method + ", path=" + path);
		}
	}
}
