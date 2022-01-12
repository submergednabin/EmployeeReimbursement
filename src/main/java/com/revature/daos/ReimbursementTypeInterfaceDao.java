package com.revature.daos;

import java.util.List;

import com.revature.models.ReimbursementType;

public interface ReimbursementTypeInterfaceDao {
	
	List<ReimbursementType> findAllReimbType();
	
	public void insertReimbursementType(ReimbursementType type);
}
