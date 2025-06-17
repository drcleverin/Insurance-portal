package com.insurance.InsuranceApp.model;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "PolicyHistory")
public class PolicyHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer historyId;

    @ManyToOne
    @JoinColumn(name = "policy_id")
    private Policy policy;

    private Timestamp changeDate;
    private String descriptionOfChange;

    @ManyToOne
    @JoinColumn(name = "changed_by_user_id")
    private User changedBy;

	public Integer getHistoryId() {
		return historyId;
	}

	public void setHistoryId(Integer historyId) {
		this.historyId = historyId;
	}

	public Policy getPolicy() {
		return policy;
	}

	public void setPolicy(Policy policy) {
		this.policy = policy;
	}

	public Timestamp getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(Timestamp changeDate) {
		this.changeDate = changeDate;
	}

	public String getDescriptionOfChange() {
		return descriptionOfChange;
	}

	public void setDescriptionOfChange(String descriptionOfChange) {
		this.descriptionOfChange = descriptionOfChange;
	}

	public User getChangedBy() {
		return changedBy;
	}

	public void setChangedBy(User changedBy) {
		this.changedBy = changedBy;
	}
}
