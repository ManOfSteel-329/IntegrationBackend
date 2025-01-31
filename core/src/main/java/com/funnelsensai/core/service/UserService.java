package com.funnelsensai.core.service;

import com.funnelsensai.core.domain.User;
import com.funnelsensai.core.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.funnelsensai.core.domain.Address;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User createUser(String username, String password, String firstName, String lastName, 
                          String email, String companyName, String subscriptionPlan, 
                          Address address, String stripeCustId) {
        String encryptedPassword = bCryptPasswordEncoder.encode(password);
        User user = new User(username, encryptedPassword, firstName, lastName, 
                           email, companyName, subscriptionPlan, 
                           address, stripeCustId);
        return userRepository.save(user);
    }

    public User save(User user) {
        return userRepository.save(user);
    }
}
