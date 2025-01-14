package com.funnelsensai.core.dto.contacts;

public class AttributionSource {
	
	private String url;
	private String campaign;
	private String utmSource;
	private String utmMedium;
	private String utmContent;
	private String referrer;
	private String campaignId;
	private String fbclid;
	private String gclid;
	private String msclikid;
	private String dclid;
	private String fbc;
	private String fbp;
	private String fbEventId;
	private String ip;
	private String meduim;
	private String meduinId;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCampaign() {
		return campaign;
	}
	public void setCampaign(String campaign) {
		this.campaign = campaign;
	}
	public String getUtmSource() {
		return utmSource;
	}
	public void setUtmSource(String utmSource) {
		this.utmSource = utmSource;
	}
	public String getUtmMedium() {
		return utmMedium;
	}
	public void setUtmMedium(String utmMedium) {
		this.utmMedium = utmMedium;
	}
	public String getUtmContent() {
		return utmContent;
	}
	public void setUtmContent(String utmContent) {
		this.utmContent = utmContent;
	}
	public String getReferrer() {
		return referrer;
	}
	public void setReferrer(String referrer) {
		this.referrer = referrer;
	}
	public String getCampaignId() {
		return campaignId;
	}
	public void setCampaignId(String campaignId) {
		this.campaignId = campaignId;
	}
	public String getFbclid() {
		return fbclid;
	}
	public void setFbclid(String fbclid) {
		this.fbclid = fbclid;
	}
	public String getGclid() {
		return gclid;
	}
	public void setGclid(String gclid) {
		this.gclid = gclid;
	}
	public String getMsclikid() {
		return msclikid;
	}
	public void setMsclikid(String msclikid) {
		this.msclikid = msclikid;
	}
	public String getDclid() {
		return dclid;
	}
	public void setDclid(String dclid) {
		this.dclid = dclid;
	}
	public String getFbc() {
		return fbc;
	}
	public void setFbc(String fbc) {
		this.fbc = fbc;
	}
	public String getFbp() {
		return fbp;
	}
	public void setFbp(String fbp) {
		this.fbp = fbp;
	}
	public String getFbEventId() {
		return fbEventId;
	}
	public void setFbEventId(String fbEventId) {
		this.fbEventId = fbEventId;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getMeduim() {
		return meduim;
	}
	public void setMeduim(String meduim) {
		this.meduim = meduim;
	}
	public String getMeduinId() {
		return meduinId;
	}
	public void setMeduinId(String meduinId) {
		this.meduinId = meduinId;
	}
	
	@Override
	public String toString() {
		return "AttributionSource [url=" + url + ", campaign=" + campaign + ", utmSource=" + utmSource + ", utmMedium="
				+ utmMedium + ", utmContent=" + utmContent + ", referrer=" + referrer + ", campaignId=" + campaignId
				+ ", fbclid=" + fbclid + ", gclid=" + gclid + ", msclikid=" + msclikid + ", dclid=" + dclid + ", fbc="
				+ fbc + ", fbp=" + fbp + ", fbEventId=" + fbEventId + ", ip=" + ip + ", meduim=" + meduim
				+ ", meduinId=" + meduinId + "]";
	}
	
	

}
