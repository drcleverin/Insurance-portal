package com.insurance.InsuranceApp.controller;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class HomeController {

    @GetMapping("/home")
    @PreAuthorize("hasAnyRole('ADMIN', 'CSR', 'UNDERWRITER', 'CUSTOMER')")
    public String userHome() {
        return "Welcome to the Home Page!";
    }
}
