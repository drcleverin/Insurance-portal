package com.insurance.InsuranceApp.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Policies")
public class Policy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer policyId;

    private String policyNumber;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonIgnore
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "policy_type_id")
    private PolicyType policyType;

    @ManyToOne
    @JoinColumn(name = "description_id")
    private PolicyDescription description;

    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal premiumAmount;
    private BigDecimal sumInsured;

    @Enumerated(EnumType.STRING)
    private PolicyStatus policyStatus;

    private Timestamp issueDate;

    public enum PolicyStatus {
        Active, Lapsed, Cancelled, Expired, Pending
    }

    @OneToOne(mappedBy = "policy")
    private MotorPolicyDetails motorDetails;

    @OneToOne(mappedBy = "policy")
    private HealthPolicyDetails healthDetails;

    @OneToOne(mappedBy = "policy")
    private ProductPolicyDetails productDetails;

    @OneToMany(mappedBy = "policy")
    private List<Claim> claims;

    @OneToMany(mappedBy = "policy")
    private List<PolicyHistory> history;

	public Integer getPolicyId() {
		return policyId;
	}

	public void setPolicyId(Integer policyId) {
		this.policyId = policyId;
	}

	public String getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public PolicyType getPolicyType() {
		return policyType;
	}

	public void setPolicyType(PolicyType policyType) {
		this.policyType = policyType;
	}

	public PolicyDescription getDescription() {
		return description;
	}

	public void setDescription(PolicyDescription description) {
		this.description = description;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public BigDecimal getPremiumAmount() {
		return premiumAmount;
	}

	public void setPremiumAmount(BigDecimal premiumAmount) {
		this.premiumAmount = premiumAmount;
	}

	public BigDecimal getSumInsured() {
		return sumInsured;
	}

	public void setSumInsured(BigDecimal sumInsured) {
		this.sumInsured = sumInsured;
	}

	public PolicyStatus getPolicyStatus() {
		return policyStatus;
	}

	public void setPolicyStatus(PolicyStatus policyStatus) {
		this.policyStatus = policyStatus;
	}

	public Timestamp getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Timestamp issueDate) {
		this.issueDate = issueDate;
	}

	public MotorPolicyDetails getMotorDetails() {
		return motorDetails;
	}

	public void setMotorDetails(MotorPolicyDetails motorDetails) {
		this.motorDetails = motorDetails;
	}

	public HealthPolicyDetails getHealthDetails() {
		return healthDetails;
	}

	public void setHealthDetails(HealthPolicyDetails healthDetails) {
		this.healthDetails = healthDetails;
	}

	public ProductPolicyDetails getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(ProductPolicyDetails productDetails) {
		this.productDetails = productDetails;
	}

	public List<Claim> getClaims() {
		return claims;
	}

	public void setClaims(List<Claim> claims) {
		this.claims = claims;
	}

	public List<PolicyHistory> getHistory() {
		return history;
	}

	public void setHistory(List<PolicyHistory> history) {
		this.history = history;
	}
}
