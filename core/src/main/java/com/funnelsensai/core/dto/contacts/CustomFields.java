package com.funnelsensai.core.dto.contacts;

public class CustomFields {
	
	private String id;
	private String value;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	} 
	
	@Override
	public String toString() {
		return "CustomFields [id=" + id + ", value=" + value + "]";
	}

}
