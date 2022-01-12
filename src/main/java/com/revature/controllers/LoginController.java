package com.revature.controllers;



import javax.servlet.http.Cookie;
import javax.xml.ws.Response;

import org.hibernate.internal.build.AllowSysOut;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.gson.Gson;
import com.revature.models.User;
import com.revature.services.LoginService;
import com.revature.utils.JwtUtil;

import io.javalin.http.Handler;

public class LoginController {
	
	LoginService ls = new LoginService();
	
	
	public Handler loginHandler = (ctx) -> {
		
		String body = ctx.body(); //turn the body of the post request into a java string
		
		Gson gson = new Gson();
		
		User user = gson.fromJson(body, User.class); //will retreive username and password from user model
		
		
		if(ls.login(user.getUsername(), user.getPassword())) {
			
			//generate a JSON Web Token to uniquely identify the user
			String jwt = JwtUtil.generate(user.getUsername(), user.getPassword());
			
			
			String JsonData = gson.toJson(jwt);
			
			//create a user session
			ctx.req.getSession(); //req is a "Request Object", we establish sessions through it
			
			//successful status code
			ctx.status(200);


			ctx.result(jwt);

			
		}else {
			
			ctx.status(401);
			String errorMessage = "Login failed";
			ctx.result(errorMessage);
		}
		
	
	};

}
