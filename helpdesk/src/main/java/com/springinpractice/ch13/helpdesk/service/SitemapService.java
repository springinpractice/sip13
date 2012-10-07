package com.springinpractice.ch13.helpdesk.service;

import java.util.List;
import java.util.Map;

import com.springinpractice.ch13.helpdesk.model.SiteNodeTemplate;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public interface SitemapService {
	
	Map<String, SiteNodeTemplate> getNodeMap();
	
	List<SiteNodeTemplate> getTopLevelNodes();
	
	SiteNodeTemplate findNodeByKey(String key);
	
	SiteNodeTemplate findNodeByMethodAndPath(String method, String path);
}
