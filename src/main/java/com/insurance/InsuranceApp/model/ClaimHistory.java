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
@Table(name = "ClaimHistory")
public class ClaimHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer historyId;

    @ManyToOne
    @JoinColumn(name = "claim_id")
    private Claim claim;

    private Timestamp changeDate;
    private String descriptionOfChange;

    @ManyToOne
    @JoinColumn(name = "changed_by_user_id")
    private User changedBy;

    @ManyToOne
    @JoinColumn(name = "old_status_id")
    private ClaimStatus oldStatus;

    @ManyToOne
    @JoinColumn(name = "new_status_id")
    private ClaimStatus newStatus;

	public Integer getHistoryId() {
		return historyId;
	}

	public void setHistoryId(Integer historyId) {
		this.historyId = historyId;
	}

	public Claim getClaim() {
		return claim;
	}

	public void setClaim(Claim claim) {
		this.claim = claim;
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

	public ClaimStatus getOldStatus() {
		return oldStatus;
	}

	public void setOldStatus(ClaimStatus oldStatus) {
		this.oldStatus = oldStatus;
	}

	public ClaimStatus getNewStatus() {
		return newStatus;
	}

	public void setNewStatus(ClaimStatus newStatus) {
		this.newStatus = newStatus;
	}
}
