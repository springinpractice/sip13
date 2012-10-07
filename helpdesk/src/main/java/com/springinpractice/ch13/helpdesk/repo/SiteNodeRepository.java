package com.springinpractice.ch13.helpdesk.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springinpractice.ch13.helpdesk.model.SiteNodeTemplate;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public interface SiteNodeRepository extends JpaRepository<SiteNodeTemplate, Long> {
	
	SiteNodeTemplate findByKey(String key);
	
	SiteNodeTemplate findByPath(String path);
}
