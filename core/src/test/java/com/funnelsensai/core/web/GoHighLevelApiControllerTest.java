package com.funnelsensai.core.web;

import com.funnelsensai.core.dto.opportunities.GetOpportunityResponse;
import com.funnelsensai.core.dto.opportunities.GetSearchOpportunityResponse;
import com.funnelsensai.core.service.CalendarEventService;
import com.funnelsensai.core.service.OpportunityService;
import com.funnelsensai.core.service.SearchContactsService;
import com.funnelsensai.core.service.SearchOpportunityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class GoHighLevelApiControllerTest {

    @Mock
    private OpportunityService opportunityService;

    @Mock
    private SearchOpportunityService searchOpportunityService;

    @Mock
    private SearchContactsService searchContactsService;

    @Mock
    private CalendarEventService calendarEventService;

    private GoHighLevelApiController controller;

    @BeforeEach
    void setUp() {
        controller = new GoHighLevelApiController(
                opportunityService,
                searchOpportunityService,
                searchContactsService,
                calendarEventService);
    }

    @Test
    void getOpportunity_ShouldReturnOpportunityResponse() throws IOException {
        // Arrange
        GetOpportunityResponse expectedResponse = new GetOpportunityResponse();
        when(opportunityService.getOpportunityFromApi()).thenReturn(expectedResponse);

        // Act
        GetOpportunityResponse actualResponse = controller.getOpportunity();

        // Assert
        assertThat(actualResponse).isEqualTo(expectedResponse);
        verify(opportunityService).getOpportunityFromApi();
    }

    @Test
    void getSearchOpportunity_ShouldReturnSearchOpportunityResponse() throws IOException {
        // Arrange
        GetSearchOpportunityResponse expectedResponse = new GetSearchOpportunityResponse();
        when(searchOpportunityService.getSearchOpportunityFromApi()).thenReturn(expectedResponse);

        // Act
        GetSearchOpportunityResponse actualResponse = controller.getSearchOpportunity();

        // Assert
        assertThat(actualResponse).isEqualTo(expectedResponse);
        verify(searchOpportunityService).getSearchOpportunityFromApi();
    }
}