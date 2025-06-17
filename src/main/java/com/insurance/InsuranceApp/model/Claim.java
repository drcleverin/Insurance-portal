package com.insurance.InsuranceApp.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Claims")
public class Claim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer claimId;

    public Integer getClaimId() {
		return claimId;
	}

	public void setClaimId(Integer claimId) {
		this.claimId = claimId;
	}

	public String getClaimNumber() {
		return claimNumber;
	}

	public void setClaimNumber(String claimNumber) {
		this.claimNumber = claimNumber;
	}

	public Policy getPolicy() {
		return policy;
	}

	public void setPolicy(Policy policy) {
		this.policy = policy;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Timestamp getClaimDate() {
		return claimDate;
	}

	public void setClaimDate(Timestamp claimDate) {
		this.claimDate = claimDate;
	}

	public LocalDate getIncidentDate() {
		return incidentDate;
	}

	public void setIncidentDate(LocalDate incidentDate) {
		this.incidentDate = incidentDate;
	}

	public String getIncidentDescription() {
		return incidentDescription;
	}

	public void setIncidentDescription(String incidentDescription) {
		this.incidentDescription = incidentDescription;
	}

	public BigDecimal getClaimAmountRequested() {
		return claimAmountRequested;
	}

	public void setClaimAmountRequested(BigDecimal claimAmountRequested) {
		this.claimAmountRequested = claimAmountRequested;
	}

	public BigDecimal getClaimAmountApproved() {
		return claimAmountApproved;
	}

	public void setClaimAmountApproved(BigDecimal claimAmountApproved) {
		this.claimAmountApproved = claimAmountApproved;
	}

	public ClaimStatus getStatus() {
		return status;
	}

	public void setStatus(ClaimStatus status) {
		this.status = status;
	}

	public LocalDate getDateResolved() {
		return dateResolved;
	}

	public void setDateResolved(LocalDate dateResolved) {
		this.dateResolved = dateResolved;
	}

	public User getReviewedBy() {
		return reviewedBy;
	}

	public void setReviewedBy(User reviewedBy) {
		this.reviewedBy = reviewedBy;
	}

	private String claimNumber;

    @ManyToOne
    @JoinColumn(name = "policy_id")
    private Policy policy;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private Timestamp claimDate;
    private LocalDate incidentDate;
    private String incidentDescription;
    private BigDecimal claimAmountRequested;
    private BigDecimal claimAmountApproved;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private ClaimStatus status;

    private LocalDate dateResolved;

    @ManyToOne
    @JoinColumn(name = "reviewed_by_user_id")
    private User reviewedBy;
}
