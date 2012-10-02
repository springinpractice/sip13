package com.springinpractice.ch13.helpdesk.web.navigation;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class NodeTemplate {
	private String key;
	private String name;
	private String path;
	private NodeTemplate parent;
	
	public String getKey() { return key; }
	
	public void setKey(String key) { this.key = key; }
	
	public String getName() { return name; }
	
	public void setName(String name) { this.name = name; }
	
	public String getPath() { return path; }
	
	public void setPath(String path) { this.path = path; }
	
	public NodeTemplate getParent() { return parent; }
	
	public void setParent(NodeTemplate parent) { this.parent = parent; }
	
	public List<NodeTemplate> getBreadcrumbs() {
		List<NodeTemplate> breadcrumbs = (parent == null ? new ArrayList<NodeTemplate>() : parent.getBreadcrumbs());
		breadcrumbs.add(this);
		return breadcrumbs;
	}
}
