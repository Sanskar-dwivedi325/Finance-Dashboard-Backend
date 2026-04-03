package com.sanskar.finance_dashboard.controller;

import com.sanskar.finance_dashboard.entity.User;
import com.sanskar.finance_dashboard.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public User createUser(@RequestBody User user) {
        return userService.create(user);
    }

    @GetMapping("/search")
    @PreAuthorize("hasRole('ADMIN')")
    public User getUser(@RequestBody Map<String,String> map) {

        return userService.search(map.get("email"));
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Page<User> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        return userService.find(page, size);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public User updateUser(@PathVariable Long id, @RequestBody User updated) {
        return userService.update(id,updated);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return "User deleted";
    }



}
