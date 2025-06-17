package com.insurance.InsuranceApp.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "PolicyTypes")
public class PolicyType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer policyTypeId;

    private String typeName;
    private String description;
    private Boolean isActive = true;

    @OneToMany(mappedBy = "policyType")
    private List<Policy> policies;

    @OneToMany(mappedBy = "policyType")
    private List<PolicyDescription> descriptions;

	public Integer getPolicyTypeId() {
		return policyTypeId;
	}

	public void setPolicyTypeId(Integer policyTypeId) {
		this.policyTypeId = policyTypeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public List<Policy> getPolicies() {
		return policies;
	}

	public void setPolicies(List<Policy> policies) {
		this.policies = policies;
	}

	public List<PolicyDescription> getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(List<PolicyDescription> descriptions) {
		this.descriptions = descriptions;
	}
}
	