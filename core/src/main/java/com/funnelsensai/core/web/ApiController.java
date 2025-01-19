package com.funnelsensai.core.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.funnelsensai.core.dto.contacts.SearchContactsResponse;
import com.funnelsensai.core.service.SearchContactsService;

@RestController
public class ApiController {
	
	public SearchContactsService searchContactsService;

	public ApiController(SearchContactsService searchContactsService) {
		super();
		this.searchContactsService = searchContactsService;
	}
	
	@PostMapping("GoHighLevel/Api/SearchContacts")
	public SearchContactsResponse postSearchContactsResponse() {
		return searchContactsService.postSearchContactsFromApi();
	}

}
