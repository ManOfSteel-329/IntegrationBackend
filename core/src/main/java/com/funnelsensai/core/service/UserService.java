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

    //Method modified from the original to include company when creating a user and return Dto. todo: delete comment
    public UserResponseDTO createUser(String username, String password, String companyName) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Username '" + username + "' is already taken.");
        }

        String encryptedPassword = bCryptPasswordEncoder.encode(password);

        // Extract the Company object from Optional or throw an exception if not found
        Company company = companyService.findCompanyByName(companyName)
                .orElseThrow(() -> new RuntimeException("Company '" + companyName + "' does not exist."));

        // Creating and saving the user entity
        User user = new User(username, encryptedPassword);
        user.setCompany(company);
        User savedUser = userRepository.save(user);

        // Convert entity to DTO before returning
        return new UserResponseDTO(savedUser.getId(), savedUser.getUsername(), savedUser.getCompany().getName());
    }
}
