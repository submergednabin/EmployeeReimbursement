package com.revature.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_types")
public class UserRole {
	
	@Id //Making primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // serializing the id
	@Column(name = "role_id")
	private int id;
	
	
	private String role_type;


	public UserRole() {
		super();
		// TODO Auto-generated constructor stub
	}


	public UserRole(int id, String role_type) {
		super();
		this.id = id;
		this.role_type = role_type;
	}


	public UserRole(String role_type) {
		super();
		this.role_type = role_type;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getRole_type() {
		return role_type;
	}


	public void setRole_type(String role_type) {
		this.role_type = role_type;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((role_type == null) ? 0 : role_type.hashCode());
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
		UserRole other = (UserRole) obj;
		if (id != other.id)
			return false;
		if (role_type == null) {
			if (other.role_type != null)
				return false;
		} else if (!role_type.equals(other.role_type))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "UserRole [id=" + id + ", role_type=" + role_type + "]";
	}
	
	
	
	
	
	
}
