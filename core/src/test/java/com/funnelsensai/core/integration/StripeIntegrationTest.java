package com.funnelsensai.core.integration;

import com.funnelsensai.core.dto.subscription.CreateSubscriptionRequest;
import com.funnelsensai.core.dto.subscription.PaymentMethodDetails;
import com.funnelsensai.core.service.PaymentService;
import com.stripe.exception.StripeException;
import com.stripe.model.Subscription;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class StripeIntegrationTest {

    @Autowired
    private PaymentService paymentService;

    /* @Test
    void shouldCreateSubscriptionSuccessfully() throws StripeException {
        // Arrange
        CreateSubscriptionRequest request = new CreateSubscriptionRequest();
        request.setPriceId("price_1QngiQDVAigQtw1EOTyUC8TX");
        request.setCustomerId("cus_Rh4qMcPSju6zk4");
        request.setPlanName("FunnelSensai Pro");
        PaymentMethodDetails paymentMethod = new PaymentMethodDetails();
        paymentMethod.setPaymentToken("pm_1Qo5ZxDVAigQtw1EsnTp5C1M");
        request.setPaymentMethod(paymentMethod);

        // Act
        Subscription subscription = paymentService.createSubscription(
            request.getCustomerId(),
            request.getPriceId(),
            request.getPaymentMethod().getPaymentToken()
        );

        // Assert
        assertThat(subscription).isNotNull();
        assertThat(subscription.getStatus()).isEqualTo("active");
    } */

   /*  @Test
    void shouldThrowExceptionForInvalidPlanName() {
        // Arrange
        CreateSubscriptionRequest request = new CreateSubscriptionRequest();
        request.setPriceId("invalid_price_id");
        request.setCustomerId("cus_Rh4qMcPSju6zk4");
        request.setPlanName("Invalid Plan");
        PaymentMethodDetails paymentMethod = new PaymentMethodDetails();
        paymentMethod.setPaymentToken("pm_1Qo5ZxDVAigQtw1EsnTp5C1M");
        request.setPaymentMethod(paymentMethod);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            paymentService.createSubscription(request.getCustomerId(), request.getPriceId(), request.getPaymentMethod().getPaymentToken());
        });
    } */

    /* @Test
    void shouldHandlePaymentMethodRetrievalError() {
        // Arrange
        CreateSubscriptionRequest request = new CreateSubscriptionRequest();
        request.setPriceId("price_1QngiQDVAigQtw1EOTyUC8TX");
        request.setCustomerId("cus_Rh4qMcPSju6zk4");
        request.setPlanName("FunnelSensai Pro");
        PaymentMethodDetails paymentMethod = new PaymentMethodDetails();
        paymentMethod.setPaymentToken("invalid_token");
        request.setPaymentMethod(paymentMethod);

        // Act & Assert
        assertThrows(StripeException.class, () -> {
            paymentService.createSubscription(request.getCustomerId(), request.getPriceId(), request.getPaymentMethod().getPaymentToken());
        });
    } */

    /* @Test
    void shouldCreateBasicSubscriptionSuccessfully() throws StripeException {
        // Arrange
        CreateSubscriptionRequest request = new CreateSubscriptionRequest();
        request.setPriceId("price_1QngiQDVAigQtw1EOTyUC8TY"); // Assuming this is the price ID for the basic plan
        request.setCustomerId("cus_Rh4qMcPSju6zk4");
        request.setPlanName("FunnelSensai Basic");
        PaymentMethodDetails paymentMethod = new PaymentMethodDetails();
        paymentMethod.setPaymentToken("pm_1Qo5ZxDVAigQtw1EsnTp5C1M");
        request.setPaymentMethod(paymentMethod);

        // Act
        Subscription subscription = paymentService.createSubscription(request.getCustomerId(), request.getPriceId(), request.getPaymentMethod().getPaymentToken());

        // Assert
        assertThat(subscription).isNotNull();
        assertThat(subscription.getStatus()).isEqualTo("active");
    } */
} 