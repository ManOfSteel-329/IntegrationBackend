package com.funnelsensai.core.dto.contacts;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Contact {
	
	private String id;
	private String name;
	private String locationId;
	private String firstName;
	private String email;
	private String emailLowerCase;
	@JsonProperty("timezone")
	private String timeZone;
	private String companyName;
	private String phone;
	private Boolean dnd;
	private DndSettings dndSettings;
	private String type;
	private String source;
	private String assignedTo;
	private String address1;
	private String city;
	private String state;
	private String country;
	private String postalCode;
	private String website;
	private Tags tags;
	private String dateOfBirth;
	private String dateAdded;
	private String dateUpdated;
	private String attachments;
	private String ssn;
	private String keyword;
	private String firstNameLowerCase;
	private String fullNameLowerCase;
	private String lastNameLowerCase;
	private String lastActivity;
	private List<CustomFields> customFields;
	private String businessId;
	private AttributionSource attributionSource;
	private LastAttributionSource lastAttributionSource;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmailLowerCase() {
		return emailLowerCase;
	}
	public void setEmailLowerCase(String emailLowerCase) {
		this.emailLowerCase = emailLowerCase;
	}
	public String getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Boolean getDnd() {
		return dnd;
	}
	public void setDnd(Boolean dnd) {
		this.dnd = dnd;
	}
	public DndSettings getDndSettings() {
		return dndSettings;
	}
	public void setDndSettings(DndSettings dndSettings) {
		this.dndSettings = dndSettings;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getAssignedTo() {
		return assignedTo;
	}
	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public Tags getTags() {
		return tags;
	}
	public void setTags(Tags tags) {
		this.tags = tags;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getDateAdded() {
		return dateAdded;
	}
	public void setDateAdded(String dateAdded) {
		this.dateAdded = dateAdded;
	}
	public String getDateUpdated() {
		return dateUpdated;
	}
	public void setDateUpdated(String dateUpdated) {
		this.dateUpdated = dateUpdated;
	}
	public String getAttachments() {
		return attachments;
	}
	public void setAttachments(String attachments) {
		this.attachments = attachments;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getFirstNameLowerCase() {
		return firstNameLowerCase;
	}
	public void setFirstNameLowerCase(String firstNameLowerCase) {
		this.firstNameLowerCase = firstNameLowerCase;
	}
	public String getFullNameLowerCase() {
		return fullNameLowerCase;
	}
	public void setFullNameLowerCase(String fullNameLowerCase) {
		this.fullNameLowerCase = fullNameLowerCase;
	}
	public String getLastNameLowerCase() {
		return lastNameLowerCase;
	}
	public void setLastNameLowerCase(String lastNameLowerCase) {
		this.lastNameLowerCase = lastNameLowerCase;
	}
	public String getLastActivity() {
		return lastActivity;
	}
	public void setLastActivity(String lastActivity) {
		this.lastActivity = lastActivity;
	}
	public List<CustomFields> getCustomFields() {
		return customFields;
	}
	public void setCustomFields(List<CustomFields> customFields) {
		this.customFields = customFields;
	}
	public String getBusinessId() {
		return businessId;
	}
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	public AttributionSource getAttributionSource() {
		return attributionSource;
	}
	public void setAttributionSource(AttributionSource attributionSource) {
		this.attributionSource = attributionSource;
	}
	public LastAttributionSource getLastAttributionSource() {
		return lastAttributionSource;
	}
	public void setLastAttributionSource(LastAttributionSource lastAttributionSource) {
		this.lastAttributionSource = lastAttributionSource;
	}
	
	@Override
	public String toString() {
		return "Contact [id=" + id + ", name=" + name + ", locationId=" + locationId + ", firstName=" + firstName
				+ ", email=" + email + ", emailLowerCase=" + emailLowerCase + ", timeZone=" + timeZone
				+ ", companyName=" + companyName + ", phone=" + phone + ", dnd=" + dnd + ", dndSettings=" + dndSettings
				+ ", type=" + type + ", source=" + source + ", assignedTo=" + assignedTo + ", address1=" + address1
				+ ", city=" + city + ", state=" + state + ", country=" + country + ", postalCode=" + postalCode
				+ ", website=" + website + ", tags=" + tags + ", dateOfBirth=" + dateOfBirth + ", dateAdded="
				+ dateAdded + ", dateUpdated=" + dateUpdated + ", attachments=" + attachments + ", ssn=" + ssn
				+ ", keyword=" + keyword + ", firstNameLowerCase=" + firstNameLowerCase + ", fullNameLowerCase="
				+ fullNameLowerCase + ", lastNameLowerCase=" + lastNameLowerCase + ", lastActivity=" + lastActivity
				+ ", customFields=" + customFields + ", businessId=" + businessId + ", attributionSource="
				+ attributionSource + ", lastAttributionSource=" + lastAttributionSource + "]";
	}

}
