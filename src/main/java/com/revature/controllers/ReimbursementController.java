package com.revature.controllers;

import java.util.List;

import org.hibernate.internal.build.AllowSysOut;

import com.google.gson.Gson;

import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.services.ReimbursementService;


import io.javalin.http.Handler;

public class ReimbursementController {
	
	
	ReimbursementService rs = new ReimbursementService();
	

	public Handler reimbursementHandler = (ctx) -> {
		
		/*
		 * String userName = ctx.pathParam("username"); int uId =
		 * Integer.parseInt(userId);
		 */
		String body = ctx.body();
		
		Gson gson = new Gson();
		

		Reimbursement reimbursement = gson.fromJson(body, Reimbursement.class);
		
		String userName = reimbursement.getUser().getUsername();
		int reimbtypeId = reimbursement.getReimbType().getId();
		int statusId = reimbursement.getStatus().getId();
		
		System.out.println(statusId);
		System.out.println(reimbtypeId);
		System.out.println(userName);
		if(rs.insertAllReimbursementDetails(userName, reimbtypeId, statusId, reimbursement)) {
			
			ctx.status(200);
			ctx.result("Reimbursement Submitted Successfully ");
		}else {
			
			ctx.status(401);
			
			ctx.result("Reimbursement cannot be submitted....Error!!!!");
		}
		
		
	};

	public Handler reimbursementStatusHandler = (ctx) -> {
		
		String userName = ctx.pathParam("username");
		String statusId = ctx.pathParam("statusid");
		
//		int uId = Integer.parseInt(userId);
		int sId = Integer.parseInt(statusId);
		
		Gson gson = new Gson();
		if(rs.findAllByStatusId(sId, userName) != null) {
			
			List<Reimbursement> mainStatusList = rs.findAllByStatusId(sId, userName);
			String JsonStatus = gson.toJson(mainStatusList);
			ctx.result(JsonStatus);
			ctx.status(200);
		}
		else {
			ctx.status(403);
			ctx.result("Failed Sir");
		}

				
		
	};
	
	
	public Handler reimbursementByUserHandler = (ctx) -> {
		
		String username = ctx.pathParam("username");
		
//		int user_id = Integer.parseInt(username);
		
		Gson gson = new Gson();
		if(rs.findAllReimbursementByUser(username) != null) {
			
			List<Reimbursement> listAllUserReimbursement = rs.findAllReimbursementByUser(username);
			
			String JsonUserImbursement  = gson.toJson(listAllUserReimbursement);
			ctx.result(JsonUserImbursement);
			ctx.status(200);
			
		}else {
			
			ctx.status(403);
			String errorMessage =  gson.toJson("Sorry, something went wrong!!! Your information Cannot be retrieved.");
			ctx.result(errorMessage);
			
		}
		
	};

	public Handler listAllReimbursementHandler = (ctx) -> {
		
		if(ctx.req.getSession() != null) {
			
			List<Reimbursement> allReimbursement = rs.listAllReimbursement();
			
			Gson gson = new Gson();
			
			String JSONAllReimb = gson.toJson(allReimbursement);
			
			ctx.result(JSONAllReimb);
			ctx.status(200);
		}
		else {
			ctx.status(403);
		}
		
	};


	public Handler deleteReimbursementHandler = (ctx) -> {
		
		String username = ctx.pathParam("username");
		String reimb_id = ctx.pathParam("reimbId");
		
		int reimbId = Integer.parseInt(reimb_id);


		if(ctx.req.getSession() != null) {
			Gson gson = new Gson();
			System.out.println(reimbId);
			
			if(rs.deleteReimbursementByUserAndId(username, reimbId)) {
				
				
				String message = "Converted to Json";
				String information = gson.toJson(message);
				ctx.status(200);
				ctx.result(information);
			}else {
				String message = "Delete Failed1";
				String information = gson.toJson(message);
				ctx.status(403);
				ctx.result(information);
			}
			
		}
		else {
			String message = "Delete Failed2";
		
			ctx.status(403);
			ctx.result(message);
		}
		
	};


	public Handler reimbusementAllHandler = (ctx) -> {
		
		String status = ctx.pathParam("statusId");
		
		
		int id = Integer.parseInt(status);
		
		
		if(rs.findReimbursementByStatus(id) != null) {
			Gson gson = new Gson();
			List<Reimbursement> mainStatusList = rs.findReimbursementByStatus(id);
			String JsonStatus = gson.toJson(mainStatusList);
			ctx.result(JsonStatus);
			ctx.status(200);
		}else {
			
			ctx.status(403);
			ctx.result("error finding all data by status");
		}
	};

	//	updating handler for manager query to approval
	public Handler reimbursementResolverUpdate = (ctx)-> {
		
		String body = ctx.body();
		Gson gson = new Gson();
		Reimbursement reimbursement = gson.fromJson(body, Reimbursement.class);
		
		int reimbId = reimbursement.getId();
		int statusId = reimbursement.getStatus().getId();
		String username = reimbursement.getResolver().getUsername();
		
		if(ctx.req.getSession() != null) {
			
			if( rs.updateReimbursementByResolver(reimbId, statusId, username)) {
			
				ctx.status(200);

			}
			else {
				ctx.status(403);
			}
		}
			else {
				ctx.status(403);
			}
	};


	public Handler indivualReimbursementHandler = (ctx) -> {
		String username = ctx.pathParam("username");
		int reimbId = Integer.parseInt(ctx.pathParam("reimbId"));
		
		if(ctx.req.getSession() != null) {
			
			if( rs.getReimbursementIndividualDetails(reimbId, username)!=null) {
				Reimbursement reimbursement = rs.getReimbursementIndividualDetails(reimbId, username);
				Gson gson = new Gson();
				String JSONList = gson.toJson(reimbursement);
				ctx.result(JSONList);
						
				ctx.status(200);

			}
			else {
				ctx.status(403);
			}
		}
			else {
				ctx.status(403);
			}
		
	} ;


	
	
	
	
	
	
}
