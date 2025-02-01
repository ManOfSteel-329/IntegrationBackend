package com.funnelsensai.core.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.funnelsensai.core.service.PaymentService;
import com.funnelsensai.core.web.PaymentController;
import com.funnelsensai.core.service.UserService;
import com.funnelsensai.core.repository.UserRepository;
import com.funnelsensai.core.service.UserDetailsServiceImpl;
import com.funnelsensai.core.security.JwtAuthenticationFilter;
import com.funnelsensai.core.security.util.JwtUtil;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import com.stripe.model.PaymentIntent;
import com.funnelsensai.core.domain.User;
import com.funnelsensai.core.dto.payment.CreatePaymentIntentResponse;
import org.springframework.http.ResponseEntity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Import;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.context.annotation.ComponentScan;

import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;


// @WebMvcTest(controllers = PaymentController.class)
@SpringBootTest
// @AutoConfigureMockMvc
@AutoConfigureMockMvc(addFilters = false)
// @ComponentScan(basePackages = "com.funnelsensai.core.web")
class PaymentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    
    @Autowired
    private WebApplicationContext webApplicationContext;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @MockBean
    private PaymentService paymentService;
    
    @MockBean
    private UserService userService;
    
    @MockBean
    private UserRepository userRepository;
    
    @MockBean
    private UserDetailsServiceImpl userDetailsService;
    
    @MockBean
    private JwtUtil jwtUtil;
    
    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @BeforeEach
    void setup() {
        // Print all registered handlers
        Map<RequestMappingInfo, HandlerMethod> handlers = webApplicationContext
            .getBean(RequestMappingHandlerMapping.class)
            .getHandlerMethods();
        
        System.out.println("\nRegistered handlers:");
        handlers.forEach((mapping, method) -> 
            System.out.println(mapping + " -> " + method));
    }

    @Configuration
    static class TestConfig {
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/auth/**").permitAll()
                    .anyRequest().authenticated());
            return http.build();
        }
    }

    @Test
    @WithMockUser // Simulates an authenticated user
    void testCreatePaymentIntent() throws Exception {
        String requestBody = """
            {
                "amount": 1000,
                "currency": "usd",
                "description": "Test payment",
                "selectedPlan": "Basic"
            }
        """;

        mockMvc.perform(post("/auth/payment/create-payment-intent")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.clientSecret").exists()); // Assuming clientSecret is returned
    }

    /* @Test
    @WithMockUser
    void testCreatePaymentIntent() throws Exception {
        // Add this debug line before the test
        System.out.println("Controller base path: " + PaymentController.class.getAnnotation(RequestMapping.class).value()[0]);
        
        String requestJson = """
            {
                "amount": 1000,
                "currency": "usd",
                "description": "Test payment",
                "selectedPlan": "Basic"
            }""";
            
        PaymentIntent mockPaymentIntent = mock(PaymentIntent.class);
        when(mockPaymentIntent.getClientSecret()).thenReturn("test_secret");
        when(paymentService.createPaymentIntent(eq(1000L), eq("usd"), eq("Test payment")))
            .thenReturn(mockPaymentIntent);
            
        mockMvc.perform(post("/auth/payment/create-payment-intent")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andDo(result -> {
                    System.out.println("\nRequest path: " + result.getRequest().getRequestURI());
                    System.out.println("Handler found: " + result.getHandler());
                    System.out.println("Status: " + result.getResponse().getStatus());
                })
                .andExpect(status().isOk());
    }
 */
    @Test
    @WithMockUser
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

        // Mock user save
        User mockUser = new User();
        when(userService.save(any(User.class))).thenReturn(mockUser);

        // Mock PaymentIntent response
        PaymentIntent mockPaymentIntent = mock(PaymentIntent.class);
        when(mockPaymentIntent.getClientSecret()).thenReturn("test_secret");
        when(paymentService.createSubscription(anyLong(), anyString(), any(User.class), anyString(), anyString()))
            .thenReturn(mockPaymentIntent);
        
        // When & Then
        mockMvc.perform(post("/auth/payment/create-subscription")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clientSecret").value("test_secret"));
    }

    @Test
    @WithMockUser
    void testCreateSubscription_ValidationFailure() throws Exception {
        // Given - Missing required fields
        String requestJson = """
            {
                "registration": {
                    "firstName": "",
                    "email": "invalid-email"
                },
                "payment": {
                    "amount": -1,
                    "selectedPlan": ""
                }
            }""";

        // When & Then
        mockMvc.perform(post("/auth/payment/create-subscription")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser
    void shouldCreatePaymentIntent() throws Exception {
        // Setup mock
        PaymentIntent mockPaymentIntent = mock(PaymentIntent.class);
        when(mockPaymentIntent.getClientSecret()).thenReturn("test_secret");
        when(paymentService.createPaymentIntent(anyLong(), anyString(), anyString()))
            .thenReturn(mockPaymentIntent);

        // Perform test
        mockMvc.perform(post("/auth/payment/create-payment-intent")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                        "amount": 1000,
                        "currency": "usd",
                        "description": "Test payment"
                    }
                    """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.clientSecret").value("test_secret"));
    }
} 