package org.yezebi.billy.spring.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yezebi.billy.spring.admin.service.AdminPublisherService;

@RestController
@RequestMapping("api/admin/publishers")
@RequiredArgsConstructor
public class AdminPublisherController {
    private final AdminPublisherService adminPublisherService;

    @GetMapping
    public String get() {
        return "Hello";
    }
}
