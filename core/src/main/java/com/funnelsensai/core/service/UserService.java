package com.funnelsensai.core.service;

import com.funnelsensai.core.domain.Company;
import com.funnelsensai.core.domain.RoleEntity;
import com.funnelsensai.core.domain.User;
import com.funnelsensai.core.repository.CompanyRepository;
import com.funnelsensai.core.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

    public UserService(BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository, CompanyRepository companyRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
    }

    public User createUser(String email, String password, String firstName, String lastName, Company company) {
        String encryptedPassword = bCryptPasswordEncoder.encode(password);
        User user = new User(email, encryptedPassword, firstName, lastName, company, true, true, RoleEntity.ADMIN);
        return userRepository.save(user);
    }


    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public Company saveCompany(Company company) {
        return companyRepository.save(company);
    }

    public User findById(Long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
    }

}
