package com.revature.services;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.hibernate.Session;

import com.revature.daos.UserDao;
import com.revature.models.User;
import com.revature.utils.HibernateUtil;
import com.revature.utils.PasswordEncryption;

public class LoginService {
	
	public boolean login(String username, String password) throws InvalidKeySpecException, NoSuchAlgorithmException {
		
		PasswordEncryption pwdEncrypt = new PasswordEncryption();
		Session ses = HibernateUtil.getSession();
		
		UserDao ud = new UserDao();
		
		if(ud.isLoggedIn(username, password) != null) {
			
			User user = ud.isLoggedIn(username, password);
			
			String storedPassword = user.getPassword();
			boolean validatePassword = pwdEncrypt.validatePassword(password, storedPassword);
			if(validatePassword && username.equals(user.getUsername())) {
				
				return true;
			}
		}
		
		return false;
	}
	
	
//	public UserRole
	
	
	

}
