package com.funnelsensai.core.dto.contacts;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Contacts {
	
	private String id;
	private String phoneLabel;
	private String country;
	private String address;
	private String source;
	private String type;
	private String locationId;
	private Boolean dnd;
	private String state;
	private String businessName;
	private List<CustomFields> customFields;
	private List<String> tags;
	private Date dateAdded;
	private List<String> additionalEmails;
	private String phone;
	private String companyName;
	private List<String> additionalPhones;
	private Date dateUpdated;
	private String city;
	private Date dateOfBirth;
	private String firstNameLowerCase;
	private String lastNameLowerCase;
	private String email;
	private String assignedTo;
	private List<String> followers;
	private Boolean validEmail;
	private DndSettings dndSettings;
	private List<Opportunities> opportunities;
	private String postalCode;
	private String businessId;
	private List<String> searchAfter;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPhoneLabel() {
		return phoneLabel;
	}
	public void setPhoneLabel(String phoneLabel) {
		this.phoneLabel = phoneLabel;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public Boolean getDnd() {
		return dnd;
	}
	public void setDnd(Boolean dnd) {
		this.dnd = dnd;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	public List<CustomFields> getCustomFields() {
		return customFields;
	}
	public void setCustomFields(List<CustomFields> customFields) {
		this.customFields = customFields;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	public Date getDateAdded() {
		return dateAdded;
	}
	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}
	public List<String> getAdditionalEmails() {
		return additionalEmails;
	}
	public void setAdditionalEmails(List<String> additionalEmails) {
		this.additionalEmails = additionalEmails;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public List<String> getAdditionalPhones() {
		return additionalPhones;
	}
	public void setAdditionalPhones(List<String> additionalPhones) {
		this.additionalPhones = additionalPhones;
	}
	public Date getDateUpdated() {
		return dateUpdated;
	}
	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getFirstNameLowerCase() {
		return firstNameLowerCase;
	}
	public void setFirstNameLowerCase(String firstNameLowerCase) {
		this.firstNameLowerCase = firstNameLowerCase;
	}
	public String getLastNameLowerCase() {
		return lastNameLowerCase;
	}
	public void setLastNameLowerCase(String lastNameLowerCase) {
		this.lastNameLowerCase = lastNameLowerCase;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAssignedTo() {
		return assignedTo;
	}
	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}
	public List<String> getFollowers() {
		return followers;
	}
	public void setFollowers(List<String> followers) {
		this.followers = followers;
	}
	public Boolean getValidEmail() {
		return validEmail;
	}
	public void setValidEmail(Boolean validEmail) {
		this.validEmail = validEmail;
	}
	public DndSettings getDndSettings() {
		return dndSettings;
	}
	public void setDndSettings(DndSettings dndSettings) {
		this.dndSettings = dndSettings;
	}
	public List<Opportunities> getOpportunities() {
		return opportunities;
	}
	public void setOpportunities(List<Opportunities> opportunities) {
		this.opportunities = opportunities;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getBusinessId() {
		return businessId;
	}
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	public List<String> getSearchAfter() {
		return searchAfter;
	}
	public void setSearchAfter(List<String> searchAfter) {
		this.searchAfter = searchAfter;
	}
	
	@Override
	public String toString() {
		return "Contact [id=" + id + ", phoneLabel=" + phoneLabel + ", country=" + country + ", address=" + address
				+ ", source=" + source + ", type=" + type + ", locationId=" + locationId + ", dnd=" + dnd + ", state="
				+ state + ", businessName=" + businessName + ", customFields=" + customFields + ", tags=" + tags
				+ ", dateAdded=" + dateAdded + ", additionalEmails=" + additionalEmails + ", phone=" + phone
				+ ", companyName=" + companyName + ", additionalPhones=" + additionalPhones + ", dateUpdated="
				+ dateUpdated + ", city=" + city + ", dateOfBirth=" + dateOfBirth + ", firstNameLowerCase="
				+ firstNameLowerCase + ", lastNameLowerCase=" + lastNameLowerCase + ", email=" + email + ", assignedTo="
				+ assignedTo + ", followers=" + followers + ", validEmail=" + validEmail + ", dndSettings="
				+ dndSettings + ", opportunities=" + opportunities + ", postalCode=" + postalCode + ", businessId="
				+ businessId + ", searchAfter=" + searchAfter + "]";
	}
}
