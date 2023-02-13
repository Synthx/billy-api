package org.yezebi.billy.spring.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yezebi.billy.spring.admin.service.AdminUserService;
import org.yezebi.billy.spring.admin.dto.CreateUserDto;
import org.yezebi.billy.spring.user.entity.User;

@RestController
@RequestMapping("api/admin/users")
@RequiredArgsConstructor
public class AdminUserController {
    private final AdminUserService adminUserService;

    @PostMapping
    public User create(@RequestBody CreateUserDto dto) {
        return this.adminUserService.create(dto);
    }
}
