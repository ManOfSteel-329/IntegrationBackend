package com.funnelsensai.core.web;

import java.io.IOException;
import java.util.List;

import com.funnelsensai.core.dto.appointmentsForContact.AppointmentsForContactResponse;
import com.funnelsensai.core.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.funnelsensai.core.domain.User;
import com.funnelsensai.core.dto.CalendarEvent.CalendarEvent;
import com.funnelsensai.core.dto.contacts.SearchContactsResponse;
import com.funnelsensai.core.dto.opportunities.GetOpportunityResponse;
import com.funnelsensai.core.dto.opportunities.GetSearchOpportunityResponse;

@RestController
public class GoHighLevelApiController {

    private final OpportunityService opportunityService;
    private final SearchOpportunityService searchOpportunityService;
    private final CalendarEventService calendarEventService;
    private final GetAppointmentsForContactService getAppointmentsForContactService;
    public SearchContactsService searchContactsService;

    public GoHighLevelApiController(OpportunityService opportunityService,
                                    SearchOpportunityService searchOpportunityService,
                                    SearchContactsService searchContactsService, CalendarEventService calendarEventService, GetAppointmentsForContactService getAppointmentsForContactService) {
        super();
        this.opportunityService = opportunityService;
        this.searchOpportunityService = searchOpportunityService;
        this.searchContactsService = searchContactsService;
        this.calendarEventService = calendarEventService;
        this.getAppointmentsForContactService = getAppointmentsForContactService;
    }

    @GetMapping("/highlevel/api/getopportunity")
    public GetOpportunityResponse getOpportunity() throws IOException {
        GetOpportunityResponse response = opportunityService.getOpportunityFromApi();
        System.out.println("Response: " + response);
        return response;
    }

    @GetMapping("/highlevel/api/searchopportunity")
    public GetSearchOpportunityResponse getSearchOpportunity() throws IOException {
        GetSearchOpportunityResponse response = searchOpportunityService.getSearchOpportunityFromApi();
        System.out.println("Response: " + response);
        return response;
    }

    @PostMapping("/highlevel/api/searchcontacts")
    public SearchContactsResponse postSearchContactsResponse() {
        return searchContactsService.postSearchContactsFromApi();
    }

    @GetMapping("/highlevel/api/getcalendarevents")
    public ResponseEntity<List<CalendarEvent>> getCalendarEvents(
            @AuthenticationPrincipal User user,
            @RequestHeader(value = "Authorization", required = true) String bearerToken,
            @RequestHeader(value = "Version", required = true) String apiVersion,
            @RequestParam(required = true) String locationId,
            @RequestParam(required = true) String startTime,
            @RequestParam(required = true) String endTime,
            @RequestParam(required = false) String calendarId,
            @RequestParam(required = false) String groupId,
            @RequestParam(required = false) String userId) {
        bearerToken = bearerToken.replace("Bearer ", "");

        try {
            List<CalendarEvent> events = calendarEventService.fetchCalendarEvents(
                    bearerToken,
                    apiVersion,
                    locationId,
                    startTime,
                    endTime,
                    calendarId,
                    groupId,
                    userId);
            return ResponseEntity.ok(events);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/highlevel/api/getappointmentsforcontact/{id}")
    public AppointmentsForContactResponse getAppointmentsForContact(@PathVariable String id) throws IOException {
        return getAppointmentsForContactService.getAppointmentsForContactFromApi(id);
    }
}
