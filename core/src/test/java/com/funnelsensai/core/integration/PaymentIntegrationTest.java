package com.funnelsensai.core.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.security.test.context.support.WithMockUser;
import com.funnelsensai.core.config.TestSecurityConfig;
import com.funnelsensai.core.dto.subscription.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = {TestSecurityConfig.class}
)
public class PaymentIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @WithMockUser
    void shouldCreateSubscriptionSuccessfully() {
        // Arrange
        CreateSubscriptionRequest request = new CreateSubscriptionRequest();
        request.setPriceId("price_123");
        request.setCustomerId("cus_123");
        PaymentMethodDetails paymentMethod = new PaymentMethodDetails();
        CardDetails cardDetails = new CardDetails();
        cardDetails.setNumber("4242424242424242");
        cardDetails.setExpMonth(12);
        cardDetails.setExpYear(2025);
        cardDetails.setCvc("123");
        paymentMethod.setCard(cardDetails);
        request.setPaymentMethod(paymentMethod);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<CreateSubscriptionRequest> entity = new HttpEntity<>(request, headers);

        // Act
        ResponseEntity<String> response = restTemplate.exchange(
            "http://localhost:" + port + "/auth/subscription/create",
            HttpMethod.POST,
            entity,
            String.class
        );

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void shouldFailWithInvalidCardDetails() {
        // Arrange
        CreateSubscriptionRequest request = new CreateSubscriptionRequest();
        request.setPriceId("price_123");
        request.setCustomerId("cus_123");
        PaymentMethodDetails paymentMethod = new PaymentMethodDetails();
        CardDetails cardDetails = new CardDetails();
        cardDetails.setNumber("4000000000000002"); // Card that will be declined
        cardDetails.setExpMonth(12);
        cardDetails.setExpYear(2025);
        cardDetails.setCvc("123");
        paymentMethod.setCard(cardDetails);
        request.setPaymentMethod(paymentMethod);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CreateSubscriptionRequest> entity = new HttpEntity<>(request, headers);

        // Act
        ResponseEntity<String> response = restTemplate.exchange(
            "http://localhost:" + port + "/auth/subscription/create",
            HttpMethod.POST,
            entity,
            String.class
        );

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).contains("Your card has been declined");
    }

    @Test
    void shouldDenyAccessWithoutAuthentication() {
        // Act
        ResponseEntity<String> response = restTemplate.getForEntity(
            "http://localhost:" + port + "/auth/subscription/details",
            String.class
        );

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }
} 