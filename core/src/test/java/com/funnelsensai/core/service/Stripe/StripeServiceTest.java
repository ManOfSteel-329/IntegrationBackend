package com.funnelsensai.core.service.Stripe;

import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.PaymentMethod;
import com.stripe.model.SetupIntent;
import com.stripe.model.Subscription;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.CustomerUpdateParams;
import com.stripe.param.PaymentMethodAttachParams;
import com.stripe.param.SetupIntentCreateParams;
import com.stripe.param.SubscriptionCreateParams;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StripeServiceTest {

    @Mock
    private Customer mockCustomer;

    @Mock
    private PaymentMethod mockPaymentMethod;

    @Mock
    private Subscription mockSubscription;

    @Mock
    private SetupIntent mockSetupIntent;

    private StripeService stripeService;

    @BeforeEach
    void setUp() {
        stripeService = new StripeService();
        ReflectionTestUtils.setField(stripeService, "stripeSecretKey", "test_secret_key");
        ReflectionTestUtils.setField(stripeService, "priceIdPro", "price_pro");
        ReflectionTestUtils.setField(stripeService, "priceIdBasic", "price_basic");
    }

    @Test
    void createSubscription_WithProPlan_ShouldCreateSubscription() throws StripeException {
        // Arrange
        String customerId = "cus_123";
        String planName = "Pro";

        try (MockedStatic<Subscription> mockedSubscription = mockStatic(Subscription.class)) {
            mockedSubscription.when(() -> Subscription.create(any(SubscriptionCreateParams.class)))
                    .thenReturn(mockSubscription);

            // Act
            Subscription result = stripeService.createSubscription(customerId, planName);

            // Assert
            assertNotNull(result);
            assertEquals(mockSubscription, result);
            mockedSubscription.verify(() -> Subscription.create(any(SubscriptionCreateParams.class)));
        }
    }

    @Test
    void createSubscription_WithBasicPlan_ShouldCreateSubscription() throws StripeException {
        // Arrange
        String customerId = "cus_123";
        String planName = "Basic";

        try (MockedStatic<Subscription> mockedSubscription = mockStatic(Subscription.class)) {
            mockedSubscription.when(() -> Subscription.create(any(SubscriptionCreateParams.class)))
                    .thenReturn(mockSubscription);

            // Act
            Subscription result = stripeService.createSubscription(customerId, planName);

            // Assert
            assertNotNull(result);
            assertEquals(mockSubscription, result);
            mockedSubscription.verify(() -> Subscription.create(any(SubscriptionCreateParams.class)));
        }
    }

    @Test
    void createSubscription_WithInvalidPlan_ShouldThrowException() {
        // Arrange
        String customerId = "cus_123";
        String planName = "InvalidPlan";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> 
            stripeService.createSubscription(customerId, planName)
        );
    }

    @Test
    void attachPaymentMethodToCustomer_ShouldAttachPaymentMethod() throws StripeException {
        // Arrange
        String customerId = "cus_123";
        String paymentMethodId = "pm_123";

        try (MockedStatic<PaymentMethod> mockedPaymentMethod = mockStatic(PaymentMethod.class)) {
            mockedPaymentMethod.when(() -> PaymentMethod.retrieve(paymentMethodId))
                    .thenReturn(mockPaymentMethod);

            // Act
            stripeService.attachPaymentMethodToCustomer(customerId, paymentMethodId);

            // Assert
            verify(mockPaymentMethod).attach(any(PaymentMethodAttachParams.class));
        }
    }

    @Test
    void setDefaultPaymentMethodForCustomer_ShouldSetDefaultPaymentMethod() throws StripeException {
        // Arrange
        String customerId = "cus_123";
        String paymentMethodId = "pm_123";

        try (MockedStatic<Customer> mockedCustomer = mockStatic(Customer.class)) {
            mockedCustomer.when(() -> Customer.retrieve(customerId))
                    .thenReturn(mockCustomer);

            // Act
            stripeService.setDefaultPaymentMethodForCustomer(customerId, paymentMethodId);

            // Assert
            verify(mockCustomer).update(any(CustomerUpdateParams.class));
        }
    }

    @Test
    void createCustomer_ShouldCreateCustomer() throws StripeException {
        // Arrange
        String email = "test@example.com";
        String name = "Test User";

        try (MockedStatic<Customer> mockedCustomer = mockStatic(Customer.class)) {
            mockedCustomer.when(() -> Customer.create(any(CustomerCreateParams.class)))
                    .thenReturn(mockCustomer);

            // Act
            Customer result = stripeService.createCustomer(email, name);

            // Assert
            assertNotNull(result);
            assertEquals(mockCustomer, result);
            mockedCustomer.verify(() -> Customer.create(any(CustomerCreateParams.class)));
        }
    }

    @Test
    void createSetupIntent_ShouldCreateSetupIntent() throws StripeException {
        // Arrange
        String paymentMethodType = "card";

        try (MockedStatic<SetupIntent> mockedSetupIntent = mockStatic(SetupIntent.class)) {
            mockedSetupIntent.when(() -> SetupIntent.create(any(SetupIntentCreateParams.class)))
                    .thenReturn(mockSetupIntent);

            // Act
            SetupIntent result = stripeService.createSetupIntent(paymentMethodType);

            // Assert
            assertNotNull(result);
            assertEquals(mockSetupIntent, result);
            mockedSetupIntent.verify(() -> SetupIntent.create(any(SetupIntentCreateParams.class)));
        }
    }
} 