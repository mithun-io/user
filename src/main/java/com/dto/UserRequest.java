package com.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotBlank(message = "username is required")
    private String name;

    @NotBlank(message = "email is required")
    @Email(message = "email is not in format")
    private String email;

    @NotNull(message = "number is required")
    private Long mobile;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%&])(?=.*\\d)[A-Za-z0-9:!@#$%&\\d]{8,}$", message = "password should contain uppercase, lowercase, special characters and numbers")
    private String password;

    @NotNull(message = "date of birth is required")
    private LocalDate dateOfBirth;
}
