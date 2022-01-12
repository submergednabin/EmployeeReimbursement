package com.revature;

//import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.apache.logging.log4j.core.util.FileUtils;

import com.revature.controllers.LoginController;
import com.revature.controllers.ReimbursementController;
import com.revature.daos.ReimbursementDao;
import com.revature.daos.ReimbursementStatusDao;
import com.revature.daos.ReimbursementTypeDao;
import com.revature.daos.UserDao;
import com.revature.daos.UserRoleDao;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatus;
import com.revature.models.ReimbursementType;
import com.revature.models.User;
import com.revature.models.UserRole;
import com.revature.utils.PasswordEncryption;

import io.javalin.Javalin;
import io.javalin.core.util.FileUtil;

public class Launcher {
	
	
	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {
		
		PasswordEncryption pasEncrypt = new PasswordEncryption();
		
		LoginController lc = new LoginController();
		
		UserRoleDao urole = new UserRoleDao();
		
		UserDao udao = new UserDao();
		
		ReimbursementTypeDao reimbType = new ReimbursementTypeDao();
		
		ReimbursementStatusDao reimbStatus = new ReimbursementStatusDao();
		
		ReimbursementController rc = new ReimbursementController();
		
		
		ReimbursementDao rdao = new ReimbursementDao();
		
		//check Hibernate implementation
		
		
//		 try(Session ses = HibernateUtil.getSession()){
//		 
//			 System.out.println("Hello You have a Connection to your DB with Hibernate");
//		 
//		 }catch(HibernateException e) 
//		 { 
//			 
//			 System.out.println("DB connection Failed");
//		 }
		 

		
//		String password1 = pasEncrypt.generateStrongHashedPwd("nabin123");
//		String password2 = pasEncrypt.generateStrongHashedPwd("admin");
//		String password3 = pasEncrypt.generateStrongHashedPwd("nabin222");
//		
//		UserRole role1 = new UserRole("Employee");
//		UserRole role2 = new UserRole("Finance Manager");
//		
//		urole.insert(role1);
//		urole.insert(role2);
//		
//		User u1 = new User("helloworld",password1, "Rabi", "ood", "app@gmail.com", role1);
//		User u2 = new User("admin",password2, "Nabin", "K", "app@hotmail.com", role2);
//		User u3 = new User("nk123",password3, "Nabin", "Khatri", "user@hotmail.com", role1);
//	
//		udao.insertUser(u1);
//		udao.insertUser(u2);
//		udao.insertUser(u3);
//		
//
//		ReimbursementStatus statusType1 = new ReimbursementStatus("Pending");
//		ReimbursementStatus statusType2 = new ReimbursementStatus("Approved");
//		ReimbursementStatus statusType3 = new ReimbursementStatus("Denied");
//		
//		reimbStatus.insert(statusType1);
//		reimbStatus.insert(statusType2);
//		reimbStatus.insert(statusType3);
//		
//
//		ReimbursementType type1 = new ReimbursementType("Lodging");
//		ReimbursementType type2 = new ReimbursementType("Travel");
//		ReimbursementType type3 = new ReimbursementType("Food");
//		ReimbursementType type4 = new ReimbursementType("Other");
//		
//		reimbType.insertReimbursementType(type1);
//		reimbType.insertReimbursementType(type2);
//		reimbType.insertReimbursementType(type3);
//		reimbType.insertReimbursementType(type4);
		
		//Insert some dummy data into reimbursement
	
		//int amount, String submitted_date, String description, String reimb_receipt, User user,
			//User resolver, ReimbursementStatus status, ReimbursementType reimbType
		
//		Reimbursement rmb = new Reimbursement(500, "09/21/2020","re receipt", "receipt4.png", u1, null, statusType1, type2 );
//		Reimbursement rmb2 = new Reimbursement(500, "10/01/2020","re receipt", "receipt3.png",u1 , null, statusType2, type3 );
//		Reimbursement rmb3 = new Reimbursement(500, "04/21/2021","re receipt", "receipt5.png", u1, null, statusType1, type4 );
//		
//
//	
//		rdao.insertReimbursement("nk123",1,1,rmb);
//		rdao.insertReimbursement("nk123",2,1,rmb3);
//		rdao.insertReimbursement("nk123",3,1,rmb2);

//		
		
//		System.out.println("Hello");
//		List<Reimbursement> reimbList = rdao.findReimbursementByStatusAndUserId(1, 1);
//		
//		for(Reimbursement r : reimbList) {
//			
//			System.out.println("The amount is " + r);
//		}
//		
//
//		
//		
		  Javalin app = Javalin.create(
		  
		  config -> {
			  config.enableCorsForAllOrigins();
			 
		  //allows the server to process Javascript request from anywhere
		  } 
		  
		).start(8090);
		  
		  
		  app.post("/login", lc.loginHandler);
		  
		  //request from employee
		  app.post("/add-reimbursement", rc.reimbursementHandler);  

		  app.get("/status/:username/:statusid", rc.reimbursementStatusHandler);
		  app.get("/status/:username", rc.reimbursementByUserHandler);
		  app.get("/list-status", rc.listAllReimbursementHandler);
		  app.get("/user-detail/:username/:reimbId", rc.indivualReimbursementHandler);
		  app.post("/delete/:username/:reimbId", rc.deleteReimbursementHandler);
		  
		  //request from manager
		  app.get("/manager/status/:statusId", rc.reimbusementAllHandler); 
		  app.post("/manager/update-reimbursement", rc.reimbursementResolverUpdate);
		  
		 
	}

}
