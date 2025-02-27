package mockitoTests;

import com.funnelsensai.core.domain.Company;
import com.funnelsensai.core.dto.ResponseDto.CompanyResponseDTO;
import com.funnelsensai.core.repository.CompanyRepository;
import com.funnelsensai.core.service.CompanyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CompanyServiceTest {

    @Mock
    private CompanyRepository companyRepository;

    @InjectMocks
    private CompanyService companyService;

    private Company company;

    @BeforeEach
    void setUp() {
        company = new Company();
        company.setName("TestCompany");
    }

    @Test
    void createCompany_shouldCreateNewCompany_whenNameNotExists() {
        when(companyRepository.findByName("NewCompany")).thenReturn(Optional.empty());
        when(companyRepository.save(any(Company.class))).thenReturn(company);

        CompanyResponseDTO response = companyService.createCompany("NewCompany");

        assertNotNull(response);
        assertEquals("NewCompany", response.getName());

        verify(companyRepository, times(1)).save(any(Company.class));
    }

    @Test
    void createCompany_shouldThrowException_whenCompanyAlreadyExists() {
        when(companyRepository.findByName("TestCompany")).thenReturn(Optional.of(company));

        Exception exception = assertThrows(RuntimeException.class, () -> companyService.createCompany("TestCompany"));

        assertEquals("Company 'TestCompany' is already taken.", exception.getMessage());
        verify(companyRepository, never()).save(any(Company.class));
    }

    @Test
    void findCompanyByName_shouldReturnCompany_whenExists() {
        when(companyRepository.findByName("TestCompany")).thenReturn(Optional.of(company));

        Optional<Company> foundCompany = companyService.findCompanyByName("TestCompany");

        assertTrue(foundCompany.isPresent());
        assertEquals("TestCompany", foundCompany.get().getName());
    }

    @Test
    void findCompanyByName_shouldReturnEmpty_whenNotExists() {
        when(companyRepository.findByName("UnknownCompany")).thenReturn(Optional.empty());

        Optional<Company> foundCompany = companyService.findCompanyByName("UnknownCompany");

        assertTrue(foundCompany.isEmpty());
    }

    @Test
    void findAll_shouldReturnListOfCompanies() {
        when(companyRepository.findAll()).thenReturn(List.of(company, new Company("AnotherCompany")));

        List<Company> companies = companyService.findAll();

        assertEquals(2, companies.size());
    }

    @Test
    void saveCompany_shouldReturnSavedCompany() {
        when(companyRepository.save(company)).thenReturn(company);

        Company savedCompany = companyService.saveCompany(company);

        assertNotNull(savedCompany);
        assertEquals("TestCompany", savedCompany.getName());
    }
}
