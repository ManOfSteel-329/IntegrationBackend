package mockitoTests;
import com.funnelsensai.core.domain.Company;
import com.funnelsensai.core.domain.User;
import com.funnelsensai.core.dto.ResponseDto.UserResponseDTO;
import com.funnelsensai.core.repository.UserRepository;
import com.funnelsensai.core.service.CompanyService;
import com.funnelsensai.core.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private CompanyService companyService;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    private User user;
    private Company company;

    @BeforeEach
    void setUp() {
        company = new Company();
        company.setName("TestCompany");

        user = new User("test@example.com", "password123", "John", "Doe");
        user.setCompany(company);
    }

    @Test
    void createUser_shouldCreateNewUser_whenEmailNotExists() {
        String email = "test@example.com";
        String password = "password123";
        String companyName = "TestCompany";
        String firstName = "John";
        String lastName = "Doe";

        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());
        when(passwordEncoder.encode(password)).thenReturn("encodedPassword");
        when(companyService.findCompanyByName(companyName)).thenReturn(Optional.empty());
        when(companyService.saveCompany(any(Company.class))).thenReturn(company);
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserResponseDTO response = userService.createUser(email, password, companyName, firstName, lastName);

        assertNotNull(response);
        assertEquals(email, response.getEmail());
        assertEquals(companyName, response.getCompanyName());
        assertEquals(firstName, response.getFirstName());
        assertEquals(lastName, response.getLastName());

        verify(userRepository, times(1)).save(any(User.class));
        verify(companyService, times(1)).saveCompany(any(Company.class));
    }

    @Test
    void createUser_shouldThrowException_whenEmailAlreadyExists() {
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));

        Exception exception = assertThrows(RuntimeException.class, () ->
                userService.createUser("test@example.com", "password123", "TestCompany", "John", "Doe")
        );

        assertEquals("email 'test@example.com' is already taken.", exception.getMessage());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void createUser_shouldUseExistingCompany_whenCompanyAlreadyExists() {
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("password123")).thenReturn("encodedPassword");
        when(companyService.findCompanyByName("TestCompany")).thenReturn(Optional.of(company));
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserResponseDTO response = userService.createUser("test@example.com", "password123", "TestCompany", "John", "Doe");

        assertNotNull(response);
        assertEquals("TestCompany", response.getCompanyName());
        verify(companyService, never()).saveCompany(any(Company.class));
    }

    @Test
    void createUser_shouldCreateNewCompany_whenCompanyDoesNotExist() {
        String email = "test@example.com";
        String password = "password123";
        String companyName = "NewCompany";
        String firstName = "John";
        String lastName = "Doe";

        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());
        when(passwordEncoder.encode(password)).thenReturn("encodedPassword");
        when(companyService.findCompanyByName(companyName)).thenReturn(Optional.empty());

        // Instead of returning the "TestCompany" object, create a new Company with the correct name.
        Company newCompany = new Company();
        newCompany.setName(companyName);

        when(companyService.saveCompany(any(Company.class))).thenReturn(newCompany);
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User u = invocation.getArgument(0);
            u.setCompany(newCompany); // Ensure user gets the new company
            return u;
        });

        UserResponseDTO response = userService.createUser(email, password, companyName, firstName, lastName);

        assertNotNull(response);
        assertEquals("NewCompany", response.getCompanyName()); // Ensure it matches the expected value

        verify(companyService, times(1)).saveCompany(any(Company.class));
    }
}
