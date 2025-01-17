package com.funnelsensai.core.web;

import org.springframework.web.bind.annotation.GetMapping;

import com.funnelsensai.core.dto.contacts.SearchContactsResponse;
import com.funnelsensai.core.service.SearchContactsService;

public class ApiController {
	
	public SearchContactsService searchContactsService;

	public ApiController(SearchContactsService searchContactsService) {
		super();
		this.searchContactsService = searchContactsService;
	}
	
	@GetMapping("GoHighLevel/Api/SearchContacts")
	public SearchContactsResponse getSearchContactsResponse() {
		return searchContactsService.getSearchContactsFromApi();
	}

}
