package com.funnelsensai.core.dto.contacts;

public class Sms {
	
	private String status;
	private String message;
	private String code;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	@Override
	public String toString() {
		return "Sms [status=" + status + ", message=" + message + ", code=" + code + "]";
	}

}
