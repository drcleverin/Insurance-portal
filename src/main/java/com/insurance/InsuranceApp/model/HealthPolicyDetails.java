package com.insurance.InsuranceApp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "HealthPolicyDetails")
public class HealthPolicyDetails {
    @Id
    private Integer policyId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "policy_id")
    private Policy policy;

    private String medicalHistoryNotes;
    private String preExistingConditions;
    private String hospitalNetworkPreference;
	public Integer getPolicyId() {
		return policyId;
	}
	public void setPolicyId(Integer policyId) {
		this.policyId = policyId;
	}
	public Policy getPolicy() {
		return policy;
	}
	public void setPolicy(Policy policy) {
		this.policy = policy;
	}
	public String getMedicalHistoryNotes() {
		return medicalHistoryNotes;
	}
	public void setMedicalHistoryNotes(String medicalHistoryNotes) {
		this.medicalHistoryNotes = medicalHistoryNotes;
	}
	public String getPreExistingConditions() {
		return preExistingConditions;
	}
	public void setPreExistingConditions(String preExistingConditions) {
		this.preExistingConditions = preExistingConditions;
	}
	public String getHospitalNetworkPreference() {
		return hospitalNetworkPreference;
	}
	public void setHospitalNetworkPreference(String hospitalNetworkPreference) {
		this.hospitalNetworkPreference = hospitalNetworkPreference;
	}
}
