package com.funnelsensai.core.dto.users;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Permissions {
    @JsonProperty("campaignsEnabled")
    private boolean campaignsEnabled;

    @JsonProperty("campaignsReadOnly")
    private boolean campaignsReadOnly;

    @JsonProperty("contactsEnabled")
    private boolean contactsEnabled;

    @JsonProperty("workflowsEnabled")
    private boolean workflowsEnabled;

    @JsonProperty("workflowsReadOnly")
    private boolean workflowsReadOnly;

    @JsonProperty("triggersEnabled")
    private boolean triggersEnabled;

    @JsonProperty("funnelsEnabled")
    private boolean funnelsEnabled;

    @JsonProperty("websitesEnabled")
    private boolean websitesEnabled;

    @JsonProperty("opportunitiesEnabled")
    private boolean opportunitiesEnabled;

    @JsonProperty("dashboardStatsEnabled")
    private boolean dashboardStatsEnabled;

    @JsonProperty("bulkRequestsEnabled")
    private boolean bulkRequestsEnabled;

    @JsonProperty("appointmentsEnabled")
    private boolean appointmentsEnabled;

    @JsonProperty("reviewsEnabled")
    private boolean reviewsEnabled;

    @JsonProperty("onlineListingsEnabled")
    private boolean onlineListingsEnabled;

    @JsonProperty("phoneCallEnabled")
    private boolean phoneCallEnabled;

    @JsonProperty("conversationsEnabled")
    private boolean conversationsEnabled;

    @JsonProperty("assignedDataOnly")
    private boolean assignedDataOnly;

    @JsonProperty("adwordsReportingEnabled")
    private boolean adwordsReportingEnabled;

    @JsonProperty("membershipEnabled")
    private boolean membershipEnabled;

    @JsonProperty("facebookAdsReportingEnabled")
    private boolean facebookAdsReportingEnabled;

    @JsonProperty("attributionsReportingEnabled")
    private boolean attributionsReportingEnabled;

    @JsonProperty("settingsEnabled")
    private boolean settingsEnabled;

    @JsonProperty("tagsEnabled")
    private boolean tagsEnabled;

    @JsonProperty("leadValueEnabled")
    private boolean leadValueEnabled;

    @JsonProperty("marketingEnabled")
    private boolean marketingEnabled;

    @JsonProperty("agentReportingEnabled")
    private boolean agentReportingEnabled;

    @JsonProperty("botService")
    private boolean botService;

    @JsonProperty("socialPlanner")
    private boolean socialPlanner;

    @JsonProperty("bloggingEnabled")
    private boolean bloggingEnabled;

    @JsonProperty("invoiceEnabled")
    private boolean invoiceEnabled;

    @JsonProperty("affiliateManagerEnabled")
    private boolean affiliateManagerEnabled;

    @JsonProperty("contentAiEnabled")
    private boolean contentAiEnabled;

    @JsonProperty("refundsEnabled")
    private boolean refundsEnabled;

    @JsonProperty("recordPaymentEnabled")
    private boolean recordPaymentEnabled;

    @JsonProperty("cancelSubscriptionEnabled")
    private boolean cancelSubscriptionEnabled;

    @JsonProperty("paymentsEnabled")
    private boolean paymentsEnabled;

    @JsonProperty("communitiesEnabled")
    private boolean communitiesEnabled;

    @JsonProperty("exportPaymentsEnabled")
    private boolean exportPaymentsEnabled;

    public Permissions() {
    }

    public Permissions(boolean campaignsEnabled, boolean campaignsReadOnly, boolean contactsEnabled, boolean workflowsEnabled, boolean workflowsReadOnly, boolean triggersEnabled, boolean funnelsEnabled, boolean websitesEnabled, boolean opportunitiesEnabled, boolean dashboardStatsEnabled, boolean bulkRequestsEnabled, boolean appointmentsEnabled, boolean reviewsEnabled, boolean onlineListingsEnabled, boolean phoneCallEnabled, boolean conversationsEnabled, boolean assignedDataOnly, boolean adwordsReportingEnabled, boolean membershipEnabled, boolean facebookAdsReportingEnabled, boolean attributionsReportingEnabled, boolean settingsEnabled, boolean tagsEnabled, boolean leadValueEnabled, boolean marketingEnabled, boolean agentReportingEnabled, boolean botService, boolean socialPlanner, boolean bloggingEnabled, boolean invoiceEnabled, boolean affiliateManagerEnabled, boolean contentAiEnabled, boolean refundsEnabled, boolean recordPaymentEnabled, boolean cancelSubscriptionEnabled, boolean paymentsEnabled, boolean communitiesEnabled, boolean exportPaymentsEnabled) {
        this.campaignsEnabled = campaignsEnabled;
        this.campaignsReadOnly = campaignsReadOnly;
        this.contactsEnabled = contactsEnabled;
        this.workflowsEnabled = workflowsEnabled;
        this.workflowsReadOnly = workflowsReadOnly;
        this.triggersEnabled = triggersEnabled;
        this.funnelsEnabled = funnelsEnabled;
        this.websitesEnabled = websitesEnabled;
        this.opportunitiesEnabled = opportunitiesEnabled;
        this.dashboardStatsEnabled = dashboardStatsEnabled;
        this.bulkRequestsEnabled = bulkRequestsEnabled;
        this.appointmentsEnabled = appointmentsEnabled;
        this.reviewsEnabled = reviewsEnabled;
        this.onlineListingsEnabled = onlineListingsEnabled;
        this.phoneCallEnabled = phoneCallEnabled;
        this.conversationsEnabled = conversationsEnabled;
        this.assignedDataOnly = assignedDataOnly;
        this.adwordsReportingEnabled = adwordsReportingEnabled;
        this.membershipEnabled = membershipEnabled;
        this.facebookAdsReportingEnabled = facebookAdsReportingEnabled;
        this.attributionsReportingEnabled = attributionsReportingEnabled;
        this.settingsEnabled = settingsEnabled;
        this.tagsEnabled = tagsEnabled;
        this.leadValueEnabled = leadValueEnabled;
        this.marketingEnabled = marketingEnabled;
        this.agentReportingEnabled = agentReportingEnabled;
        this.botService = botService;
        this.socialPlanner = socialPlanner;
        this.bloggingEnabled = bloggingEnabled;
        this.invoiceEnabled = invoiceEnabled;
        this.affiliateManagerEnabled = affiliateManagerEnabled;
        this.contentAiEnabled = contentAiEnabled;
        this.refundsEnabled = refundsEnabled;
        this.recordPaymentEnabled = recordPaymentEnabled;
        this.cancelSubscriptionEnabled = cancelSubscriptionEnabled;
        this.paymentsEnabled = paymentsEnabled;
        this.communitiesEnabled = communitiesEnabled;
        this.exportPaymentsEnabled = exportPaymentsEnabled;
    }

    public boolean isCampaignsEnabled() {
        return campaignsEnabled;
    }

    public void setCampaignsEnabled(boolean campaignsEnabled) {
        this.campaignsEnabled = campaignsEnabled;
    }

    public boolean isCampaignsReadOnly() {
        return campaignsReadOnly;
    }

    public void setCampaignsReadOnly(boolean campaignsReadOnly) {
        this.campaignsReadOnly = campaignsReadOnly;
    }

    public boolean isContactsEnabled() {
        return contactsEnabled;
    }

    public void setContactsEnabled(boolean contactsEnabled) {
        this.contactsEnabled = contactsEnabled;
    }

    public boolean isWorkflowsEnabled() {
        return workflowsEnabled;
    }

    public void setWorkflowsEnabled(boolean workflowsEnabled) {
        this.workflowsEnabled = workflowsEnabled;
    }

    public boolean isWorkflowsReadOnly() {
        return workflowsReadOnly;
    }

    public void setWorkflowsReadOnly(boolean workflowsReadOnly) {
        this.workflowsReadOnly = workflowsReadOnly;
    }

    public boolean isTriggersEnabled() {
        return triggersEnabled;
    }

    public void setTriggersEnabled(boolean triggersEnabled) {
        this.triggersEnabled = triggersEnabled;
    }

    public boolean isFunnelsEnabled() {
        return funnelsEnabled;
    }

    public void setFunnelsEnabled(boolean funnelsEnabled) {
        this.funnelsEnabled = funnelsEnabled;
    }

    public boolean isWebsitesEnabled() {
        return websitesEnabled;
    }

    public void setWebsitesEnabled(boolean websitesEnabled) {
        this.websitesEnabled = websitesEnabled;
    }

    public boolean isOpportunitiesEnabled() {
        return opportunitiesEnabled;
    }

    public void setOpportunitiesEnabled(boolean opportunitiesEnabled) {
        this.opportunitiesEnabled = opportunitiesEnabled;
    }

    public boolean isDashboardStatsEnabled() {
        return dashboardStatsEnabled;
    }

    public void setDashboardStatsEnabled(boolean dashboardStatsEnabled) {
        this.dashboardStatsEnabled = dashboardStatsEnabled;
    }

    public boolean isBulkRequestsEnabled() {
        return bulkRequestsEnabled;
    }

    public void setBulkRequestsEnabled(boolean bulkRequestsEnabled) {
        this.bulkRequestsEnabled = bulkRequestsEnabled;
    }

    public boolean isAppointmentsEnabled() {
        return appointmentsEnabled;
    }

    public void setAppointmentsEnabled(boolean appointmentsEnabled) {
        this.appointmentsEnabled = appointmentsEnabled;
    }

    public boolean isReviewsEnabled() {
        return reviewsEnabled;
    }

    public void setReviewsEnabled(boolean reviewsEnabled) {
        this.reviewsEnabled = reviewsEnabled;
    }

    public boolean isOnlineListingsEnabled() {
        return onlineListingsEnabled;
    }

    public void setOnlineListingsEnabled(boolean onlineListingsEnabled) {
        this.onlineListingsEnabled = onlineListingsEnabled;
    }

    public boolean isPhoneCallEnabled() {
        return phoneCallEnabled;
    }

    public void setPhoneCallEnabled(boolean phoneCallEnabled) {
        this.phoneCallEnabled = phoneCallEnabled;
    }

    public boolean isConversationsEnabled() {
        return conversationsEnabled;
    }

    public void setConversationsEnabled(boolean conversationsEnabled) {
        this.conversationsEnabled = conversationsEnabled;
    }

    public boolean isAssignedDataOnly() {
        return assignedDataOnly;
    }

    public void setAssignedDataOnly(boolean assignedDataOnly) {
        this.assignedDataOnly = assignedDataOnly;
    }

    public boolean isAdwordsReportingEnabled() {
        return adwordsReportingEnabled;
    }

    public void setAdwordsReportingEnabled(boolean adwordsReportingEnabled) {
        this.adwordsReportingEnabled = adwordsReportingEnabled;
    }

    public boolean isMembershipEnabled() {
        return membershipEnabled;
    }

    public void setMembershipEnabled(boolean membershipEnabled) {
        this.membershipEnabled = membershipEnabled;
    }

    public boolean isFacebookAdsReportingEnabled() {
        return facebookAdsReportingEnabled;
    }

    public void setFacebookAdsReportingEnabled(boolean facebookAdsReportingEnabled) {
        this.facebookAdsReportingEnabled = facebookAdsReportingEnabled;
    }

    public boolean isAttributionsReportingEnabled() {
        return attributionsReportingEnabled;
    }

    public void setAttributionsReportingEnabled(boolean attributionsReportingEnabled) {
        this.attributionsReportingEnabled = attributionsReportingEnabled;
    }

    public boolean isSettingsEnabled() {
        return settingsEnabled;
    }

    public void setSettingsEnabled(boolean settingsEnabled) {
        this.settingsEnabled = settingsEnabled;
    }

    public boolean isTagsEnabled() {
        return tagsEnabled;
    }

    public void setTagsEnabled(boolean tagsEnabled) {
        this.tagsEnabled = tagsEnabled;
    }

    public boolean isLeadValueEnabled() {
        return leadValueEnabled;
    }

    public void setLeadValueEnabled(boolean leadValueEnabled) {
        this.leadValueEnabled = leadValueEnabled;
    }

    public boolean isMarketingEnabled() {
        return marketingEnabled;
    }

    public void setMarketingEnabled(boolean marketingEnabled) {
        this.marketingEnabled = marketingEnabled;
    }

    public boolean isAgentReportingEnabled() {
        return agentReportingEnabled;
    }

    public void setAgentReportingEnabled(boolean agentReportingEnabled) {
        this.agentReportingEnabled = agentReportingEnabled;
    }

    public boolean isBotService() {
        return botService;
    }

    public void setBotService(boolean botService) {
        this.botService = botService;
    }

    public boolean isSocialPlanner() {
        return socialPlanner;
    }

    public void setSocialPlanner(boolean socialPlanner) {
        this.socialPlanner = socialPlanner;
    }

    public boolean isBloggingEnabled() {
        return bloggingEnabled;
    }

    public void setBloggingEnabled(boolean bloggingEnabled) {
        this.bloggingEnabled = bloggingEnabled;
    }

    public boolean isInvoiceEnabled() {
        return invoiceEnabled;
    }

    public void setInvoiceEnabled(boolean invoiceEnabled) {
        this.invoiceEnabled = invoiceEnabled;
    }

    public boolean isAffiliateManagerEnabled() {
        return affiliateManagerEnabled;
    }

    public void setAffiliateManagerEnabled(boolean affiliateManagerEnabled) {
        this.affiliateManagerEnabled = affiliateManagerEnabled;
    }

    public boolean isContentAiEnabled() {
        return contentAiEnabled;
    }

    public void setContentAiEnabled(boolean contentAiEnabled) {
        this.contentAiEnabled = contentAiEnabled;
    }

    public boolean isRefundsEnabled() {
        return refundsEnabled;
    }

    public void setRefundsEnabled(boolean refundsEnabled) {
        this.refundsEnabled = refundsEnabled;
    }

    public boolean isRecordPaymentEnabled() {
        return recordPaymentEnabled;
    }

    public void setRecordPaymentEnabled(boolean recordPaymentEnabled) {
        this.recordPaymentEnabled = recordPaymentEnabled;
    }

    public boolean isCancelSubscriptionEnabled() {
        return cancelSubscriptionEnabled;
    }

    public void setCancelSubscriptionEnabled(boolean cancelSubscriptionEnabled) {
        this.cancelSubscriptionEnabled = cancelSubscriptionEnabled;
    }

    public boolean isPaymentsEnabled() {
        return paymentsEnabled;
    }

    public void setPaymentsEnabled(boolean paymentsEnabled) {
        this.paymentsEnabled = paymentsEnabled;
    }

    public boolean isCommunitiesEnabled() {
        return communitiesEnabled;
    }

    public void setCommunitiesEnabled(boolean communitiesEnabled) {
        this.communitiesEnabled = communitiesEnabled;
    }

    public boolean isExportPaymentsEnabled() {
        return exportPaymentsEnabled;
    }

    public void setExportPaymentsEnabled(boolean exportPaymentsEnabled) {
        this.exportPaymentsEnabled = exportPaymentsEnabled;
    }

    @Override
    public String toString() {
        return "Permissions{" +
                "campaignsEnabled=" + campaignsEnabled +
                ", campaignsReadOnly=" + campaignsReadOnly +
                ", contactsEnabled=" + contactsEnabled +
                ", workflowsEnabled=" + workflowsEnabled +
                ", workflowsReadOnly=" + workflowsReadOnly +
                ", triggersEnabled=" + triggersEnabled +
                ", funnelsEnabled=" + funnelsEnabled +
                ", websitesEnabled=" + websitesEnabled +
                ", opportunitiesEnabled=" + opportunitiesEnabled +
                ", dashboardStatsEnabled=" + dashboardStatsEnabled +
                ", bulkRequestsEnabled=" + bulkRequestsEnabled +
                ", appointmentsEnabled=" + appointmentsEnabled +
                ", reviewsEnabled=" + reviewsEnabled +
                ", onlineListingsEnabled=" + onlineListingsEnabled +
                ", phoneCallEnabled=" + phoneCallEnabled +
                ", conversationsEnabled=" + conversationsEnabled +
                ", assignedDataOnly=" + assignedDataOnly +
                ", adwordsReportingEnabled=" + adwordsReportingEnabled +
                ", membershipEnabled=" + membershipEnabled +
                ", facebookAdsReportingEnabled=" + facebookAdsReportingEnabled +
                ", attributionsReportingEnabled=" + attributionsReportingEnabled +
                ", settingsEnabled=" + settingsEnabled +
                ", tagsEnabled=" + tagsEnabled +
                ", leadValueEnabled=" + leadValueEnabled +
                ", marketingEnabled=" + marketingEnabled +
                ", agentReportingEnabled=" + agentReportingEnabled +
                ", botService=" + botService +
                ", socialPlanner=" + socialPlanner +
                ", bloggingEnabled=" + bloggingEnabled +
                ", invoiceEnabled=" + invoiceEnabled +
                ", affiliateManagerEnabled=" + affiliateManagerEnabled +
                ", contentAiEnabled=" + contentAiEnabled +
                ", refundsEnabled=" + refundsEnabled +
                ", recordPaymentEnabled=" + recordPaymentEnabled +
                ", cancelSubscriptionEnabled=" + cancelSubscriptionEnabled +
                ", paymentsEnabled=" + paymentsEnabled +
                ", communitiesEnabled=" + communitiesEnabled +
                ", exportPaymentsEnabled=" + exportPaymentsEnabled +
                '}';
    }
}

