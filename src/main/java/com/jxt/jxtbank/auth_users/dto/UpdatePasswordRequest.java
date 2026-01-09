package com.jxt.jxtbank.auth_users.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdatePasswordRequest {

    @NotBlank(message = "New password is required")
    private String oldPassword;

    @NotBlank(message = "New password is required")
    private String newPassword;

}
