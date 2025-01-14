package com.funnelsensai.core.dto.contacts;

public class SearchContactsResponse {
	
	private Contact contact;

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	@Override
	public String toString() {
		return "SearchContactsResponse [contact=" + contact + "]";
	}

}
