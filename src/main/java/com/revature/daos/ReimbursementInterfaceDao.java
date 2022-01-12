package com.revature.daos;

import java.util.List;

import com.revature.models.Reimbursement;

public interface ReimbursementInterfaceDao {
	
	public boolean insertReimbursement(String user_name, int reimbtypeId, int statusId, Reimbursement reimbursement);
	
	List<Reimbursement> listAllReimbursement();
	
	List<Reimbursement> findReimbursementByUserId(String username); //User is employee here
	
	//list all the reimbursements of individual employee by status and id
	List<Reimbursement> findReimbursementByStatusAndUserId(int status_id, String username);
	
	List<Reimbursement> findReimbursementByStatusId(int statusId);
	
	public int updateReimbursementById(int reimb_id);
	
	public boolean deleteReimbursementByUserAndId(String username, int reimb_id);
	
	public Reimbursement getReimbursementIndividualDetails(int reimbId, String username);
	
	
	
	
}
