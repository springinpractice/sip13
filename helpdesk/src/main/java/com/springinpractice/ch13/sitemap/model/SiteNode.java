/* 
 * Copyright (c) 2013 Manning Publications Co.
 * 
 * Book: http://manning.com/wheeler/
 * Blog: http://springinpractice.com/
 * Code: https://github.com/springinpractice
 */
package com.springinpractice.ch13.sitemap.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Entity
@Table(name = "site_node")
public class SiteNode {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "ukey")
	private String key;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "path")
	private String path;
	
	@ManyToOne
	@JoinColumn(name = "parent_id")
	private SiteNode parent;
	
	public Long getId() { return id; }
	
	public String getKey() { return key; }
	
	public String getName() { return name; }
	
	public String getPath() { return path; }
	
	public SiteNode getParent() { return parent; }
	
	public List<SiteNode> getBreadcrumbs() {
		List<SiteNode> breadcrumbs = (parent == null ? new ArrayList<SiteNode>() : parent.getBreadcrumbs());
		breadcrumbs.add(this);
		return breadcrumbs;
	}
}
