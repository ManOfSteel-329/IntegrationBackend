package com.funnelsensai.core.dto.contacts;

import java.util.List;

public class Tags {
	
	private List<String> tags;

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "Tags [tags=" + tags + "]";
	}

}
