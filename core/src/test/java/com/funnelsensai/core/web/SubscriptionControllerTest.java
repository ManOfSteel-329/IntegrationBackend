package com.funnelsensai.core.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.funnelsensai.core.dto.subscription.*;
import com.funnelsensai.core.service.PaymentService;
import com.funnelsensai.core.service.UserService;
import com.stripe.exception.StripeException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentMethod;
import com.stripe.model.Subscription;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SubscriptionController.class)
class SubscriptionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PaymentService paymentService;

    @MockBean
    private UserService userService;

    /* @Test
    void createSubscription_ValidRequest_ReturnsSuccess() throws Exception {
        // Arrange
        String customerId = "cus_Rh4qMcPSju6zk4";
        String priceId = "price_1QngiQDVAigQtw1EOTyUC8TX";
        String paymentToken = "pm_1Qo5ZxDVAigQtw1EsnTp5C1M";

        CreateSubscriptionRequest request = new CreateSubscriptionRequest();
        request.setPriceId(priceId);
        request.setCustomerId(customerId);
        PaymentMethodDetails paymentMethod = new PaymentMethodDetails();
        paymentMethod.setPaymentToken(paymentToken);
        request.setPaymentMethod(paymentMethod);

        PaymentMethod mockPaymentMethod = mock(PaymentMethod.class);
        Subscription mockSubscription = mock(Subscription.class);

        when(PaymentMethod.retrieve(paymentToken)).thenReturn(mockPaymentMethod);
        when(paymentService.createSubscription(customerId, priceId, paymentToken)).thenReturn(mockSubscription);

        // Act & Assert
        mockMvc.perform(post("/auth/subscription/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.subscriptionId").value("sub_123"))
                .andExpect(jsonPath("$.status").value("active"));
    } */

    @Test
    void createSubscription_MissingToken_ReturnsBadRequest() throws Exception {
        // Arrange
        CreateSubscriptionRequest request = new CreateSubscriptionRequest();
        request.setPriceId("price_123");
        PaymentMethodDetails paymentMethod = new PaymentMethodDetails();
        request.setPaymentMethod(paymentMethod);

        // Act & Assert
        mockMvc.perform(post("/auth/subscription/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.error").value("Invalid request: Missing payment token"));
    }

    /* @Test
    void createSubscription_StripeError_ReturnsBadRequest() throws Exception {
        // Arrange
        CreateSubscriptionRequest request = new CreateSubscriptionRequest();
        request.setPriceId("price_123");
        PaymentMethodDetails paymentMethod = new PaymentMethodDetails();
        paymentMethod.setPaymentToken("pm_test_123");
        request.setPaymentMethod(paymentMethod);

        InvalidRequestException mockException = mock(InvalidRequestException.class);
        when(PaymentMethod.retrieve(any())).thenThrow(mockException);

        // Act & Assert
        mockMvc.perform(post("/auth/subscription/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.error").exists());
    } */
} 