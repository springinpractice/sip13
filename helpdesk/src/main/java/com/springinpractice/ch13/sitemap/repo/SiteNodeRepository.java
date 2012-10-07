package com.springinpractice.ch13.sitemap.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springinpractice.ch13.sitemap.model.SiteNode;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public interface SiteNodeRepository extends JpaRepository<SiteNode, Long> {
	
	SiteNode findByKey(String key);
	
	SiteNode findByPath(String path);
}
