package com.insurance.InsuranceApp.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "PolicyDescriptions")
public class PolicyDescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer descriptionId;

    @ManyToOne
    @JoinColumn(name = "policy_type_id")
    private PolicyType policyType;

    private String name;
    private BigDecimal sumInsuredRangeMin;
    private BigDecimal sumInsuredRangeMax;
    private String premiumCalculationNotes;
	private String coveragesDescription;
    private String benefitsDescription;
    private String termsAndConditions;
    
    public Integer getDescriptionId() {
		return descriptionId;
	}
	public void setDescriptionId(Integer descriptionId) {
		this.descriptionId = descriptionId;
	}
	public PolicyType getPolicyType() {
		return policyType;
	}
	public void setPolicyType(PolicyType policyType) {
		this.policyType = policyType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getSumInsuredRangeMin() {
		return sumInsuredRangeMin;
	}
	public void setSumInsuredRangeMin(BigDecimal sumInsuredRangeMin) {
		this.sumInsuredRangeMin = sumInsuredRangeMin;
	}
	public BigDecimal getSumInsuredRangeMax() {
		return sumInsuredRangeMax;
	}
	public void setSumInsuredRangeMax(BigDecimal sumInsuredRangeMax) {
		this.sumInsuredRangeMax = sumInsuredRangeMax;
	}
	public String getPremiumCalculationNotes() {
		return premiumCalculationNotes;
	}
	public void setPremiumCalculationNotes(String premiumCalculationNotes) {
		this.premiumCalculationNotes = premiumCalculationNotes;
	}
	public String getCoveragesDescription() {
		return coveragesDescription;
	}
	public void setCoveragesDescription(String coveragesDescription) {
		this.coveragesDescription = coveragesDescription;
	}
	public String getBenefitsDescription() {
		return benefitsDescription;
	}
	public void setBenefitsDescription(String benefitsDescription) {
		this.benefitsDescription = benefitsDescription;
	}
	public String getTermsAndConditions() {
		return termsAndConditions;
	}
	public void setTermsAndConditions(String termsAndConditions) {
		this.termsAndConditions = termsAndConditions;
	}

}
