package com.springinpractice.ch13.helpdesk.web.navigation;

import static org.springframework.util.Assert.notNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Component
public class Sitemap implements InitializingBean {
	@Inject private MessageSource messageSource;
	
	private Map<String, NodeTemplate> nodesByKey = new HashMap<String, NodeTemplate>();
	private Map<MethodAndPath, NodeTemplate> nodesByMap = new HashMap<MethodAndPath, NodeTemplate>();
	private List<NodeTemplate> topLevelNodes = new ArrayList<NodeTemplate>();
	
	@Override
	public void afterPropertiesSet() throws Exception {
		NodeTemplate home = node(null, "home", Paths.HOME);
		NodeTemplate login = node(home, "login", Paths.LOGIN);
		NodeTemplate accessDenied = node(home, "accessDenied", Paths.ACCESS_DENIED);
		
		NodeTemplate tickets = node(home, "tickets", Paths.TICKETS);
		NodeTemplate newTicket = node(tickets, "newTicket", Paths.NEW_TICKET, new MethodAndPath("post", Paths.TICKETS));
		
		NodeTemplate users = node(home, "users", Paths.USERS);
		
		NodeTemplate knowledgeBase = node(home, "knowledgeBase", Paths.KNOWLEDGE_BASE);
		
		NodeTemplate approvals = node(home, "approvals", Paths.APPROVALS);
		
		NodeTemplate tasks = node(home, "tasks", Paths.TASKS);
		
		NodeTemplate surveys = node(home, "surveys", Paths.SURVEYS);
		
		NodeTemplate reporting = node(home, "reporting", Paths.REPORTING);
		
		// Create top-level nodes
		topLevelNodes.add(tickets);
		topLevelNodes.add(users);
		topLevelNodes.add(knowledgeBase);
		topLevelNodes.add(approvals);
		topLevelNodes.add(tasks);
		topLevelNodes.add(surveys);
		topLevelNodes.add(reporting);
	}
	
	private NodeTemplate node(NodeTemplate parent, String key, String path, MethodAndPath... maps) {
		NodeTemplate node = new NodeTemplate();
		node.setKey(key);
		node.setPath(path);
		node.setParent(parent);
		
		String messageKey = "node." + key + ".name";
		node.setName(messageSource.getMessage(messageKey, null, null));
		
		nodesByKey.put(key, node);
		nodesByMap.put(new MethodAndPath("get", path), node);
		for (MethodAndPath map : maps) {
			nodesByMap.put(map, node);
		}
		
		return node;
	}
	
	public Map<String, NodeTemplate> getNodeMap() {
		return nodesByKey;
	}
	
	public NodeTemplate findNodeByKey(String key) {
		notNull(key, "key can't be null");
		return nodesByKey.get(key);
	}
	
	public NodeTemplate findNodeByRequest(HttpServletRequest request) {
		notNull(request, "request can't be null");
		String method = request.getMethod().toLowerCase();
		String path = request.getServletPath();
		MethodAndPath map = new MethodAndPath(method, path);
		return nodesByMap.get(map);
	}
	
	public List<NodeTemplate> getTopLevelNodes() { return topLevelNodes; }
}
