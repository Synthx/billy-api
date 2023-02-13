package org.yezebi.billy.spring.admin.dto;

import lombok.Data;
import org.yezebi.billy.spring.user.model.UserRole;

@Data
public class CreateUserDto {
    private String email;
    private String password;
    private String nickname;
    private UserRole role;
}
