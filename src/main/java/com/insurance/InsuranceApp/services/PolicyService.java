package com.insurance.InsuranceApp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.InsuranceApp.model.Customer;
import com.insurance.InsuranceApp.model.Policy;
import com.insurance.InsuranceApp.repository.CustomerRepository;
import com.insurance.InsuranceApp.repository.PolicyRepository;

@Service
public class PolicyService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PolicyRepository policyRepository;

    public List<Policy> getPoliciesByUserId(Long userId) {
        Customer customer = customerRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Customer not found with user_id: " + userId));

        return policyRepository.findByCustomerCustomerId(customer.getCustomerId());
    }
}
