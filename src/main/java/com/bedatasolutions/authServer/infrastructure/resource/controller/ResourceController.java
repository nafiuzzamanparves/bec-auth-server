package com.bedatasolutions.authServer.infrastructure.resource.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getName());
        return ResponseEntity.ok("This is a secured resource.");
    }

    @GetMapping("/check")
    @PreAuthorize("hasRole('user:read') or hasAuthority('USER') or hasAuthority('ADMIN') or hasAnyAuthority('SCOPE_profile')")
    // @PreAuthorize("hasRole('user:read') or hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN') or hasAnyAuthority('SCOPE_profile')")
    public ResponseEntity<String> securedResourceTwo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getName());
        return ResponseEntity.ok("This is second secured resource.");
    }


}