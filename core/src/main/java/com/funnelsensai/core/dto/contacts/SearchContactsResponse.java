package com.funnelsensai.core.dto.contacts;

import java.util.List;

public class SearchContactsResponse {
	
	private List<Contacts> contacts;
	private String total;
	
	public List<Contacts> getContacts() {
		return contacts;
	}
	public void setContacts(List<Contacts> contacts) {
		this.contacts = contacts;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	
	@Override
	public String toString() {
		return "SearchContactsResponse [contacts=" + contacts + ", total=" + total + "]";
	}
	
}
