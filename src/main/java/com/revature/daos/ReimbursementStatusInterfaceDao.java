package com.revature.daos;

import java.util.List;

import com.revature.models.ReimbursementStatus;

public interface ReimbursementStatusInterfaceDao {
	
	List<ReimbursementStatus> listAllStatus();
	
	
	
	public void insert(ReimbursementStatus status);
}
