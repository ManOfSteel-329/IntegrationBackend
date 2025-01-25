package com.funnelsensai.core.dto.contacts;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DndSettings {
	
	@JsonProperty("Call")
	private Call call;
	@JsonProperty("Email")
	private Email email;
	@JsonProperty("SMS")
	private Sms sms;
	@JsonProperty("WhatsApp")
	private WhatsApp whatsApp;
	@JsonProperty("GMB")
	private Gmb gmb;
	@JsonProperty("FB")
	private Fb fb;
	
	public Call getCall() {
		return call;
	}
	public void setCall(Call call) {
		this.call = call;
	}
	public Email getEmail() {
		return email;
	}
	public void setEmail(Email email) {
		this.email = email;
	}
	public Sms getSms() {
		return sms;
	}
	public void setSms(Sms sms) {
		this.sms = sms;
	}
	public WhatsApp getWhatsApp() {
		return whatsApp;
	}
	public void setWhatsApp(WhatsApp whatsApp) {
		this.whatsApp = whatsApp;
	}
	public Gmb getGmb() {
		return gmb;
	}
	public void setGmb(Gmb gmb) {
		this.gmb = gmb;
	}
	public Fb getFb() {
		return fb;
	}
	public void setFb(Fb fb) {
		this.fb = fb;
	}
	
	@Override
	public String toString() {
		return "DndSettings [call=" + call + ", email=" + email + ", sms=" + sms + ", whatsApp=" + whatsApp + ", gmb="
				+ gmb + ", fb=" + fb + "]";
	}
	
}
