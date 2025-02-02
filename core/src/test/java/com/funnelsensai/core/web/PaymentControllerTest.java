package com.funnelsensai.core.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.funnelsensai.core.service.PaymentService;
import com.funnelsensai.core.service.UserService;
import com.funnelsensai.core.repository.UserRepository;
import com.stripe.model.PaymentIntent;
import org.springframework.security.test.context.support.WithMockUser;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PaymentController.class)
@AutoConfigureMockMvc(addFilters = false)
class PaymentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PaymentService paymentService;
    
    @MockBean
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    @WithMockUser
    void shouldCreatePaymentIntent() throws Exception {
        // Setup
        PaymentIntent mockPaymentIntent = mock(PaymentIntent.class);
        when(mockPaymentIntent.getClientSecret()).thenReturn("test_secret");
        when(paymentService.createPaymentIntent(anyLong(), anyString(), anyString()))
            .thenReturn(mockPaymentIntent);

        // Test
        mockMvc.perform(post("/auth/payment/create-payment-intent")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                        "amount": 1000,
                        "currency": "usd",
                        "description": "Test payment"
                    }"""))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clientSecret").value("test_secret"));
    }
}