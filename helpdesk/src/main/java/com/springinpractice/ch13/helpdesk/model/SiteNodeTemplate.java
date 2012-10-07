package com.springinpractice.ch13.helpdesk.model;

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
@Table(name = "site_node_template")
public class SiteNodeTemplate {
	
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
	private SiteNodeTemplate parent;
	
	public Long getId() { return id; }
	
	public String getKey() { return key; }
	
	public String getName() { return name; }
	
	public String getPath() { return path; }
	
	public SiteNodeTemplate getParent() { return parent; }
	
	public List<SiteNodeTemplate> getBreadcrumbs() {
		List<SiteNodeTemplate> breadcrumbs = (parent == null ? new ArrayList<SiteNodeTemplate>() : parent.getBreadcrumbs());
		breadcrumbs.add(this);
		return breadcrumbs;
	}
}
