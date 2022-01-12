package com.revature.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "reimbursements")
public class Reimbursement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reimb_id")
	private int id;
	
	private int amount;
	
	private String submitted_date;
	
	private String description;
	
	private String reimb_receipt;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "resolver_id") //resolve_id is also a user id b
	private User resolver;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "status_id")
	private ReimbursementStatus status; // change from status model
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "reimb_type_id")
	private ReimbursementType reimbType;
	
	

	public Reimbursement() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Reimbursement(int id, int amount, String submitted_date, String description, String reimb_receipt, User user,
			User resolver, ReimbursementStatus status, ReimbursementType reimbType) {
		super();
		this.id = id;
		this.amount = amount;
		this.submitted_date = submitted_date;
		this.description = description;
		this.reimb_receipt = reimb_receipt;
		this.user = user;
		this.resolver = resolver;
		this.status = status;
		this.reimbType = reimbType;
	}
	
	



	public Reimbursement(int amount, String submitted_date, String description, String reimb_receipt, User user,
			User resolver, ReimbursementStatus status, ReimbursementType reimbType) {
		super();
		this.amount = amount;
		this.submitted_date = submitted_date;
		this.description = description;
		this.reimb_receipt = reimb_receipt;
		this.user = user;
		this.resolver = resolver;
		this.status = status;
		this.reimbType = reimbType;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public int getAmount() {
		return amount;
	}



	public void setAmount(int amount) {
		this.amount = amount;
	}



	public String getSubmitted_date() {
		return submitted_date;
	}



	public void setSubmitted_date(String submitted_date) {
		this.submitted_date = submitted_date;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getReimb_receipt() {
		return reimb_receipt;
	}



	public void setReimb_receipt(String reimb_receipt) {
		this.reimb_receipt = reimb_receipt;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public User getResolver() {
		return resolver;
	}



	public void setResolver(User resolver) {
		this.resolver = resolver;
	}



	public ReimbursementStatus getStatus() {
		return status;
	}



	public void setStatus(ReimbursementStatus status) {
		this.status = status;
	}



	public ReimbursementType getReimbType() {
		return reimbType;
	}



	public void setReimbType(ReimbursementType reimbType) {
		this.reimbType = reimbType;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amount;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + ((reimbType == null) ? 0 : reimbType.hashCode());
		result = prime * result + ((reimb_receipt == null) ? 0 : reimb_receipt.hashCode());
		result = prime * result + ((resolver == null) ? 0 : resolver.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((submitted_date == null) ? 0 : submitted_date.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		if (amount != other.amount)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (reimbType == null) {
			if (other.reimbType != null)
				return false;
		} else if (!reimbType.equals(other.reimbType))
			return false;
		if (reimb_receipt == null) {
			if (other.reimb_receipt != null)
				return false;
		} else if (!reimb_receipt.equals(other.reimb_receipt))
			return false;
		if (resolver == null) {
			if (other.resolver != null)
				return false;
		} else if (!resolver.equals(other.resolver))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (submitted_date == null) {
			if (other.submitted_date != null)
				return false;
		} else if (!submitted_date.equals(other.submitted_date))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", amount=" + amount + ", submitted_date=" + submitted_date
				+ ", description=" + description + ", reimb_receipt=" + reimb_receipt + ", user=" + user + ", resolver="
				+ resolver + ", status=" + status + ", reimbType=" + reimbType + "]";
	}



	
	


	
	
	
	
	
	
}
