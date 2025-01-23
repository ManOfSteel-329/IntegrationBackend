package com.funnelsensai.core.web.request;

import com.funnelsensai.core.domain.User;
import com.funnelsensai.core.dto.CalendarEvent.CalendarEvent;
import com.funnelsensai.core.service.CalendarEventService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class GoHighLevelApiController {

    private final CalendarEventService calendarEventService;


    public GoHighLevelApiController(CalendarEventService calendarEventService) {
        this.calendarEventService = calendarEventService;
    }


    @GetMapping("highlevel/api/getcalendarevents")
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

}
