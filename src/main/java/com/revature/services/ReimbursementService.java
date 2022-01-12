package com.revature.services;

import java.util.List;

import com.revature.daos.ReimbursementDao;
import com.revature.models.Reimbursement;

public class ReimbursementService {
	
	ReimbursementDao rdao = new ReimbursementDao();
	
	public boolean insertAllReimbursementDetails(String username, int reimbtypeId, int statusId, Reimbursement reimbursement) {
			
		if(rdao.insertReimbursement(username, reimbtypeId, statusId, reimbursement)) {
			
			return true;
			
		}
		return false; 
		
	}
	
	
	
	public List<Reimbursement> findAllReimbursementByUser(String username){
		
		return rdao.findReimbursementByUserId(username);
		
	}
	
	
	public List<Reimbursement> findAllByStatusId(int status_id, String userName){
		
		return rdao.findReimbursementByStatusAndUserId(status_id, userName);

	}
	
	public boolean deleteReimbursementByUserAndId(String username, int reimb_id) {
	
		if(rdao.deleteReimbursementByUserAndId(username, reimb_id)){
			
			return true;
		}
		return false;
		
	}
	
	public List<Reimbursement> listAllReimbursement(){
		
		return rdao.listAllReimbursement();
	}



	public List<Reimbursement> findReimbursementByStatus(int id) {
		
		return rdao.findReimbursementByStatusId(id);
		
	}



	public boolean updateReimbursementByResolver(int reimbId, int statusId, String username) {
		if(rdao.updateReimbursementByResolver(reimbId, statusId, username)) {
			return true;
		}
		return false;
	}



	public Reimbursement getReimbursementIndividualDetails(int reimbId, String username) {
		
		return rdao.getReimbursementIndividualDetails(reimbId, username);
		
	}

}
