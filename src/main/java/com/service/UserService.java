package com.service;

import com.dto.UserRequest;
import com.dto.UserResponse;
import com.entity.User;
import com.exception.UserNotFoundException;
import com.mapper.UserMapper;
import com.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponse insert(UserRequest userRequest) {
        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new IllegalArgumentException("already exists");
        }

        User user = userMapper.toUser(userRequest);
        User save = userRepository.save(user);

        return userMapper.toUserResponse(save);
    }

    public List<UserResponse> read(int page, int size, String sort, boolean desc) {
        Sort sortBy = desc ? Sort.by(sort).descending() : Sort.by(sort).ascending();
        PageRequest pageRequest = PageRequest.of(page - 1, size, sortBy);
        Page<User> pageResult = userRepository.findAll(pageRequest);
        return userMapper.toUserResponses(pageResult.getContent());
    }

    public UserResponse update(Long id, UserRequest userRequest) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("user not found"));

        user.setUsername(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setMobile(userRequest.getMobile());
        user.setPassword(userRequest.getPassword());
        user.setDateOfBirth(userRequest.getDateOfBirth());
        userRepository.save(user);
        return userMapper.toUserResponse(user);
    }

    public UserResponse patch(Long id, UserRequest userRequest) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("user not found"));

        if (userRequest.getName() != null) {
            user.setUsername(userRequest.getName());
        }
        if (userRequest.getEmail() != null) {
            user.setEmail(userRequest.getEmail());
        }
        if (userRequest.getMobile() != null) {
            user.setMobile(userRequest.getMobile());
        }
        if (userRequest.getPassword() != null) {
            user.setPassword(userRequest.getPassword());
        }
        if (userRequest.getDateOfBirth() != null) {
            user.setDateOfBirth(userRequest.getDateOfBirth());
        }
        userRepository.save(user);
        return userMapper.toUserResponse(user);
    }

    public UserResponse delete(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("user not found"));
        userRepository.delete(user);
        return userMapper.toUserResponse(user);
    }
}
