package com.controller;

import com.dto.ApiResponse;
import com.dto.UserRequest;
import com.dto.UserResponse;
import com.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> create(@Valid @RequestBody UserRequest userRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>("inserted successfully", userService.insert(userRequest)));
    }

    @GetMapping("/users")
    public ResponseEntity<ApiResponse<List<UserResponse>>> read(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sort, @RequestParam(defaultValue = "false") boolean desc) {
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("fetched successfully", userService.read(page, size, sort, desc)));
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> update(@PathVariable Long id, @RequestBody UserRequest userRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>("updated successfully", userService.update(id, userRequest)));
    }

    @PatchMapping("/user/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> patch(@PathVariable Long id, @RequestBody UserRequest userRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>("patched successfully", userService.patch(id, userRequest)));
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> delete(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("deleted successfully", userService.delete(id)));
    }
}
