package com.talent_acquisition_system.service;

import com.talent_acquisition_system.model.Hr;
import com.talent_acquisition_system.model.User;
import com.talent_acquisition_system.repository.HrRepository;
import com.talent_acquisition_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HrRepository hrRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User registerUser(User user) {
        // Check if the email already exists
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("Email already exists."); // Handle as needed
        }
        // Hash the user's password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Hr registerHr(Hr hr) {
        // Check if the email already exists
        if (hrRepository.findByEmail(hr.getEmail()) != null) {
            throw new RuntimeException("Email already exists."); // Handle as needed
        }
        // Hash the HR's password before saving
        hr.setPassword(passwordEncoder.encode(hr.getPassword()));
        return hrRepository.save(hr);
    }

    public Hr signinHr(String email, String password) {
        Hr hr = hrRepository.findByEmail(email);
        if (hr != null && passwordEncoder.matches(password, hr.getPassword())) {
            return hr;
        }
        return null;
    }

    public User signinUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }
        return null;
    }
}
