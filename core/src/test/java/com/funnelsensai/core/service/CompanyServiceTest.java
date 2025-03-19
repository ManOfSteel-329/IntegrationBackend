package com.funnelsensai.core.service;

import com.funnelsensai.core.domain.Company;
import com.funnelsensai.core.repository.CompanyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CompanyServiceTest {

    @Mock
    private CompanyRepository companyRepository;

    private CompanyService companyService;

    @BeforeEach
    void setUp() {
        companyService = new CompanyService(companyRepository);
    }

    @Test
    void createCompany_ShouldCreateCompany() {
        // Arrange
        String name = "Test Company";
        String stripeCustomerId = "cus_123";
        String stripeSubscriptionId = "sub_123";
        Company expectedCompany = new Company(name, stripeCustomerId, stripeSubscriptionId);
        when(companyRepository.save(any(Company.class))).thenReturn(expectedCompany);

        // Act
        Company result = companyService.createCompany(name, stripeCustomerId, stripeSubscriptionId);

        // Assert
        assertNotNull(result);
        assertEquals(name, result.getName());
        assertEquals(stripeCustomerId, result.getStripeCustomerId());
        assertEquals(stripeSubscriptionId, result.getStripeSubscriptionId());
        verify(companyRepository).save(any(Company.class));
    }

    @Test
    void saveCompany_ShouldSaveCompany() {
        // Arrange
        Company company = new Company("Test Company", "cus_123", "sub_123");
        when(companyRepository.save(company)).thenReturn(company);

        // Act
        Company result = companyService.saveCompany(company);

        // Assert
        assertNotNull(result);
        assertEquals(company, result);
        verify(companyRepository).save(company);
    }

    @Test
    void findCompanyById_WhenCompanyExists_ShouldReturnCompany() {
        // Arrange
        Long id = 1L;
        Company expectedCompany = new Company("Test Company", "cus_123", "sub_123");
        when(companyRepository.findById(id)).thenReturn(Optional.of(expectedCompany));

        // Act
        Company result = companyService.findCompanyById(id);

        // Assert
        assertNotNull(result);
        assertEquals(expectedCompany, result);
        verify(companyRepository).findById(id);
    }

    @Test
    void findCompanyById_WhenCompanyDoesNotExist_ShouldThrowException() {
        // Arrange
        Long id = 1L;
        when(companyRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> 
            companyService.findCompanyById(id)
        );
        assertEquals("Company not found", exception.getMessage());
        verify(companyRepository).findById(id);
    }
} 