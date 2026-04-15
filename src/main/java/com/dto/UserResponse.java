package com.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserResponse {

    private Long id;

    private String name;

    private String email;

    private Long mobile;

    private int dateOfBirth;
}
