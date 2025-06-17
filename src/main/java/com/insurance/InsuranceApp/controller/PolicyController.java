package com.insurance.InsuranceApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.insurance.InsuranceApp.model.Policy;
import com.insurance.InsuranceApp.model.Customer;
import com.insurance.InsuranceApp.repository.CustomerRepository;
import com.insurance.InsuranceApp.services.PolicyService;

@RestController
@RequestMapping("/api/policies")
public class PolicyController {

    @Autowired
    private PolicyService policyService;

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/customer/{customerId}")
    public List<Policy> getPoliciesByCustomerId(@PathVariable Long customerId) {
        // Optional: check if customer exists
        Customer customer = customerRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        return policyService.getPoliciesByUserId(customer.getCustomerId());
    }
}
