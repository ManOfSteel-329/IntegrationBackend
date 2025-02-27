package com.funnelsensai.core.integrationTests;

import com.funnelsensai.core.domain.User;
import com.funnelsensai.core.repository.UserRepository;
import com.funnelsensai.core.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD) // Ensures a fresh database state
class UserServiceIntegrationTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    void createUser_shouldPersistUserToDatabase() {
        String email = "integration@example.com";
        String password = "securePassword";
        String companyName = "IntegrationCompany";
        String firstName = "John";
        String lastName = "Doe";

        userService.createUser(email, password, companyName, firstName, lastName);

        // Verify user is actually saved in the DB
        User savedUser = userRepository.findByEmail(email).orElse(null);
        assertNotNull(savedUser);
        assertEquals(email, savedUser.getEmail());
    }
}
