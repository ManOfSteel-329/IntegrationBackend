package com.funnelsensai.core.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.funnelsensai.core.service.PaymentService;
import com.funnelsensai.core.service.UserService;
import com.stripe.model.PaymentIntent;

@WebMvcTest(PaymentController.class)
class PaymentControllerTest {
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private PaymentService paymentService;
    
    @MockBean
    private UserService userService;
    
    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    void testCreatePaymentIntent() throws Exception {
        String requestJson = """
            {
                "amount": 1000,
                "currency": "usd",
                "description": "Test payment"
            }""";
            
        mockMvc.perform(post("/auth/payment/create-payment-intent")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clientSecret").exists());
    }

    @Test
    void testCreateSubscription_Success() throws Exception {
        // Given
        String requestJson = """
            {
                "registration": {
                    "firstName": "John",
                    "lastName": "Doe",
                    "email": "john@example.com",
                    "companyName": "Test Co",
                    "password": "password123",
                    "line1": "123 Test St",
                    "city": "Test City",
                    "state": "TS",
                    "postalCode": "12345",
                    "country": "US"
                },
                "payment": {
                    "amount": 1000,
                    "currency": "usd",
                    "description": "Test subscription",
                    "selectedPlan": "Basic"
                }
            }""";

        // Mock PaymentIntent response
        PaymentIntent mockPaymentIntent = mock(PaymentIntent.class);
        when(mockPaymentIntent.getClientSecret()).thenReturn("test_secret");
        when(paymentService.createSubscription(any(), any(), any(), any(), any()))
            .thenReturn(mockPaymentIntent);
        
        // When & Then
        mockMvc.perform(post("/auth/payment/create-subscription")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clientSecret").value("test_secret"));
    }

    @Test
    void testCreateSubscription_ValidationFailure() throws Exception {
        // Given - Missing required fields
        String requestJson = """
            {
                "registration": {
                    "firstName": "",
                    "email": "invalid-email"
                },
                "payment": {
                    "amount": -1
                }
            }""";

        // When & Then
        mockMvc.perform(post("/auth/payment/create-subscription")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isBadRequest());
    }
} 