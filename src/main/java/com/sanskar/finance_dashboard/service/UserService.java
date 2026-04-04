package com.sanskar.finance_dashboard.service;

import com.sanskar.finance_dashboard.entity.User;
import com.sanskar.finance_dashboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User update(Long id, User updated) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if(updated.getPassword()!=null&&!updated.getPassword().trim().isEmpty()) {
            user.setPassword(passwordEncoder.encode(updated.getPassword()));
        }

        user.setRole(updated.getRole());
        user.setActive(updated.isActive());
        return userRepository.save(user);

    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }


    public Page<User> find(int page, int size) {
        return userRepository.findAll(PageRequest.of(page, size));
    }

    public User search(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
