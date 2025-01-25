package com.funnelsensai.core.dto.contacts;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Opportunities {
	
	private String id;
	@JsonProperty("pipeline_id")
	private String pipelineId;
	@JsonProperty("pipeline_stage_id")
	private String pipelineStageId;
	@JsonProperty("monetary_value")
	private String monetaryValue;
	private String status;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPipelineId() {
		return pipelineId;
	}
	public void setPipelineId(String pipelineId) {
		this.pipelineId = pipelineId;
	}
	public String getPipelineStageId() {
		return pipelineStageId;
	}
	public void setPipelineStageId(String pipelineStageId) {
		this.pipelineStageId = pipelineStageId;
	}
	public String getMonetaryValue() {
		return monetaryValue;
	}
	public void setMonetaryValue(String monetaryValue) {
		this.monetaryValue = monetaryValue;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "Opportunities [id=" + id + ", pipelineId=" + pipelineId + ", pipelineStageId=" + pipelineStageId
				+ ", monetaryValue=" + monetaryValue + ", status=" + status + "]";
	}

}
