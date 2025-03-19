package com.funnelsensai.core.service;

import com.funnelsensai.core.domain.Company;
import com.funnelsensai.core.domain.Role;
import com.funnelsensai.core.domain.User;
import com.funnelsensai.core.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Mock
    private UserRepository userRepository;

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService(bCryptPasswordEncoder, userRepository);
    }

    @Test
    void emailExists_WhenEmailExists_ShouldReturnTrue() {
        // Arrange
        String email = "test@example.com";
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(new User()));

        // Act
        boolean result = userService.emailExists(email);

        // Assert
        assertTrue(result);
        verify(userRepository).findByEmail(email);
    }

    @Test
    void emailExists_WhenEmailDoesNotExist_ShouldReturnFalse() {
        // Arrange
        String email = "test@example.com";
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        // Act
        boolean result = userService.emailExists(email);

        // Assert
        assertFalse(result);
        verify(userRepository).findByEmail(email);
    }

    @Test
    void createUser_ShouldCreateUserWithEncryptedPassword() {
        // Arrange
        String email = "test@example.com";
        String password = "password123";
        String firstName = "John";
        String lastName = "Doe";
        Company company = new Company("Test Company", "cus_123", "sub_123");
        Role role = Role.ADMIN;
        String encryptedPassword = "encryptedPassword123";

        when(bCryptPasswordEncoder.encode(password)).thenReturn(encryptedPassword);
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        User result = userService.createUser(email, password, firstName, lastName, company, role);

        // Assert
        assertNotNull(result);
        assertEquals(email, result.getEmail());
        assertEquals(encryptedPassword, result.getPassword());
        assertEquals(firstName, result.getFirstName());
        assertEquals(lastName, result.getLastName());
        assertEquals(company, result.getCompany());
        assertEquals(role, result.getRole());
        assertTrue(result.isEnabled());
        assertTrue(result.isAccountNonExpired());
        verify(bCryptPasswordEncoder).encode(password);
        verify(userRepository).save(any(User.class));
    }

    @Test
    void saveUser_ShouldSaveUser() {
        // Arrange
        User user = new User();
        when(userRepository.save(user)).thenReturn(user);

        // Act
        User result = userService.saveUser(user);

        // Assert
        assertNotNull(result);
        assertEquals(user, result);
        verify(userRepository).save(user);
    }

    @Test
    void findById_WhenUserExists_ShouldReturnUser() {
        // Arrange
        Long id = 1L;
        User expectedUser = new User();
        when(userRepository.findById(id)).thenReturn(Optional.of(expectedUser));

        // Act
        User result = userService.findById(id);

        // Assert
        assertNotNull(result);
        assertEquals(expectedUser, result);
        verify(userRepository).findById(id);
    }

    @Test
    void findById_WhenUserDoesNotExist_ShouldThrowException() {
        // Arrange
        Long id = 1L;
        when(userRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> 
            userService.findById(id)
        );
        assertEquals("User not found with id: " + id, exception.getMessage());
        verify(userRepository).findById(id);
    }
} 