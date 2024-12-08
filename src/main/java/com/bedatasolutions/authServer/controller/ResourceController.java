package com.bedatasolutions.authServer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/resource")
public class ResourceController {

    @GetMapping("/public")
    public String publicEndpoint() {
        return "This is a public endpoint!";
    }

    @GetMapping("/secured")
    public ResponseEntity<String> securedResource() {
        return ResponseEntity.ok("This is a secured resource.");
    }

    @GetMapping("/check")
    @PreAuthorize("hasRole('user:read')")
    public ResponseEntity<String> securedResourceTwo() {
        return ResponseEntity.ok("This is second secured resource.");
    }


}