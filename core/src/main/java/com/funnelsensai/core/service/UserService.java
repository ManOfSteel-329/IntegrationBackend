package com.funnelsensai.core.service;

import com.funnelsensai.core.domain.Company;
import com.funnelsensai.core.domain.User;
import com.funnelsensai.core.dto.ResponseDto.UserResponseDTO;
import com.funnelsensai.core.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final CompanyService companyService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, CompanyService companyService) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.companyService = companyService;
    }

    public UserResponseDTO createUser(String email, String password, String companyName, String firstName, String lastName) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("email '" + email + "' is already taken.");
        }
        String encryptedPassword = bCryptPasswordEncoder.encode(password);

        //Creating company if Company doesn't exist, else set company name
        Company company;
        if (companyService.findCompanyByName(companyName).isEmpty()) {
            company = new Company();
            company.setName(companyName);
            companyService.saveCompany(company);
        } else {
            company = companyService.findCompanyByName(companyName).get();
        }

        // Using the updated constructor which sets both username, email, firstname and lastname.
        User user = new User(email, encryptedPassword, firstName, lastName);
        user.setCompany(company);
        User savedUser = userRepository.save(user);
        return new UserResponseDTO(savedUser.getId(), savedUser.getEmail(), savedUser.getCompany().getName(),
                savedUser.getFirstName(), savedUser.getLastName());
    }
}
