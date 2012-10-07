package com.springinpractice.ch13.helpdesk.service.impl;

import static org.springframework.util.Assert.notNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springinpractice.ch13.helpdesk.model.SiteNodeTemplate;
import com.springinpractice.ch13.helpdesk.model.SiteNodeTemplateKeys;
import com.springinpractice.ch13.helpdesk.repo.SiteNodeRepository;
import com.springinpractice.ch13.helpdesk.service.SitemapService;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Service
@Transactional
public class SitemapServiceImpl implements SitemapService, InitializingBean {
	@Inject private SiteNodeRepository siteNodeRepo;
	
	private Map<String, SiteNodeTemplate> nodeMap;
	private List<SiteNodeTemplate> topLevelNodes;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		this.nodeMap = new HashMap<String, SiteNodeTemplate>();
		List<SiteNodeTemplate> nodes = siteNodeRepo.findAll();
		for (SiteNodeTemplate node : nodes) { nodeMap.put(node.getKey(), node); }
		
		// TODO Move these to the database too?
		this.topLevelNodes = new ArrayList<SiteNodeTemplate>();
		topLevelNodes.add(nodeMap.get(SiteNodeTemplateKeys.HOME));
		topLevelNodes.add(nodeMap.get(SiteNodeTemplateKeys.TICKETS));
		topLevelNodes.add(nodeMap.get(SiteNodeTemplateKeys.KNOWLEDGE_BASE));
		topLevelNodes.add(nodeMap.get(SiteNodeTemplateKeys.APPROVALS));
		topLevelNodes.add(nodeMap.get(SiteNodeTemplateKeys.TASKS));
		topLevelNodes.add(nodeMap.get(SiteNodeTemplateKeys.SURVEYS));
		topLevelNodes.add(nodeMap.get(SiteNodeTemplateKeys.REPORTING));
	}
	
	@Override
	public Map<String, SiteNodeTemplate> getNodeMap() { return nodeMap; }
	
	@Override
	public List<SiteNodeTemplate> getTopLevelNodes() { return topLevelNodes; }
	
	@Override
	public SiteNodeTemplate findNodeByKey(String key) {
		notNull(key, "key can't be null");
		return nodeMap.get(key);
	}
	
	@Override
	public SiteNodeTemplate findNodeByMethodAndPath(String method, String path) {
		notNull(method, "method can't be null");
		notNull(path, "path can't be null");
		
		if ("get".equalsIgnoreCase(method)) {
			// FIXME Need to be able to handle path variables more generally
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
