package com.funnelsensai.core.service;

import com.funnelsensai.core.domain.User;
import com.funnelsensai.core.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User createUser (String username, String password) {

        String encryptedPassword = bCryptPasswordEncoder.encode(password);
        User user = new User(username, encryptedPassword);

        return userRepository.save(user);
    }
}
