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

    public UserResponseDTO createUser(String email, String password, String companyName) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("email '" + email + "' is already taken.");
        }
        String encryptedPassword = bCryptPasswordEncoder.encode(password);
        Company company = companyService.findCompanyByName(companyName)
                .orElseThrow(() -> new RuntimeException("Company '" + companyName + "' does not exist."));
        // Using the updated constructor which sets both username and email.
        User user = new User(email, encryptedPassword);
        user.setCompany(company);
        User savedUser = userRepository.save(user);
        return new UserResponseDTO(savedUser.getId(), savedUser.getEmail(), savedUser.getCompany().getName());
    }
}
