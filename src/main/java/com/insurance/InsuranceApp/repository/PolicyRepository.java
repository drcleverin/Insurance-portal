package com.insurance.InsuranceApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insurance.InsuranceApp.model.Policy;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Long> {
    List<Policy> findByCustomerCustomerId(Long customerId);
}
