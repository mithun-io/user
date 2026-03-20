package com;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/")
    public String hello() {
        return "Hello World from Dockerized Spring Boot!";
    }

    @PostMapping("/user")
    public User save(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping("/users")
    public List<User> all() {
        return userRepository.findAll();
    }
}