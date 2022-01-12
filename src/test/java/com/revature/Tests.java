package com.revature;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.revature.daos.ReimbursementDao;
import com.revature.daos.UserDao;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.services.LoginService;
import com.revature.utils.PasswordEncryption;

public class Tests   {
	
	public static ReimbursementDao reimb;
	public static User us;
	public static UserDao u;
	public static LoginService ls;
	public static PasswordEncryption pswd;
	public static Reimbursement r;
	
	public int reimbtypeId = 2;
	public int statusId = 1;
	public int reimb_id = 4;
	public  String username = "nk123";
	public String password = "nabin222";
	
	
	
	@BeforeAll
	public static void initializeClassLogins() {
		u = new UserDao();
		us = new User();
		ls = new LoginService();
		pswd = new PasswordEncryption();
		r= new Reimbursement();
		reimb = new ReimbursementDao();
		System.out.println("In the @beforeall moethod");
	}
	
	
	
	
	
	@Test
	public void testLoggin() throws NoSuchAlgorithmException, InvalidKeySpecException {
		
		System.out.println("Testing Login......");
		boolean result = ls.login(username, password);
		assertTrue(result==true);

	}
	
	
	
	
	@Test
	public void addReimbursement() {
		System.out.println("Testing User ADD");
		boolean result = reimb.insertReimbursement(username, reimbtypeId, statusId, r);
		assertTrue(result==true);
	}
	
	public void getReimbursementIndividualDetails(int reimbId, String username) {
		
		System.out.println("Retreiving Reimbursement Details");
		Reimbursement result = reimb.getReimbursementIndividualDetails(reimbId, username);
		assertNotEquals(null, result);
	}
	
	@Test
	public void findReimbursementByUsername() {
		System.out.println("Finding Reimbursement by User");
		List<Reimbursement> result = reimb.findReimbursementByUserId(username);
		assertNotEquals(null, result);
	}
	
	@Test
	public void findReimbursementByStatus() {
		System.out.println("Finding Reimbursement By Status");
		List<Reimbursement> result = reimb.findReimbursementByStatusId(statusId);
		assertNotEquals(null, result);
	}
	
	@Test
	public void findReimbursementByStatusAndUserid() {
		System.out.println("finding reimbursement by employee");
		List<Reimbursement> result = reimb.findReimbursementByStatusAndUserId(statusId, username);
		assertNotEquals(null, result);
	}
	
	@Test
	public void updateReimbursement() {
		System.out.println("updating");
		boolean result = reimb.updateReimbursementByResolver(reimb_id, statusId, username);
		assertTrue(result==true);
	}
	
	@Test
	public void deleteReimbursement() {
		System.out.println("Deleting Reimbursement.....");
		boolean result = reimb.deleteReimbursementByUserAndId(username, reimb_id);
		assertTrue(result==true);
	}
	
	
	

	
	

	
}
