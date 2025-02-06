package com.funnelsensai.core.service;

import com.funnelsensai.core.domain.Company;
import com.funnelsensai.core.domain.Role;
import com.funnelsensai.core.domain.User;
import com.funnelsensai.core.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserRepository userRepository;


    public UserService(BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
    }

    public User createUser(String email, String password, String firstName, String lastName, Company company, Role role) {
        String encryptedPassword = bCryptPasswordEncoder.encode(password);
        User user = new User(email, encryptedPassword, firstName, lastName, company, true, true, role);
        return userRepository.save(user);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User findById(Long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
    }

}
