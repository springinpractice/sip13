/* 
 * Copyright (c) 2013 Manning Publications Co.
 * 
 * Book: http://manning.com/wheeler/
 * Blog: http://springinpractice.com/
 * Code: https://github.com/springinpractice
 */
package com.springinpractice.ch13.helpdesk.integration.transformer;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.springinpractice.ch13.cdm.TicketCategory;
import com.springinpractice.ch13.cdm.TicketCategory.TicketCategoryList;
import com.springinpractice.ch13.helpdesk.model.TicketCategoryEntity;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Component
public class TicketCategoryListTransformer {
	@Inject private TicketCategoryTransformer categoryTransformer;
	
	public TicketCategoryList toDto(List<TicketCategoryEntity> categoryEntities) {
		List<TicketCategory> categoryDtoList = new ArrayList<TicketCategory>();
		for (TicketCategoryEntity categoryEntity : categoryEntities) {
			categoryDtoList.add(categoryTransformer.toDto(categoryEntity));
		}
		return new TicketCategoryList(categoryDtoList);
	}
}
