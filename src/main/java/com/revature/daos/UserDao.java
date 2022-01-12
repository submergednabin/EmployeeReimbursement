package com.revature.daos;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.revature.models.User;
import com.revature.utils.HibernateUtil;
import com.revature.utils.PasswordEncryption;

public class UserDao implements UserInterfaceDao {
	
	PasswordEncryption pwdEncrypt = new PasswordEncryption();
	Logger log = (Logger) LogManager.getLogger();
	@Override
	public List<User> listAllUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findUserById() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User isLoggedIn(String username, String password) {
		Session ses = HibernateUtil.getSession();
			try {
				
				String hql = "FROM User where username = :uname";
				User user = (User) ses.createQuery(hql)
						.setParameter("uname", username)
						.uniqueResult();
				log.info("User loggedIn successfully....");
				return user;
				
			}catch(HibernateException e) {
				
				String message = "User/Password Mismatch";
				System.out.println(message);
				e.printStackTrace();
				log.info("User cannot be added..Failed"+ message);
			}
			
			return null;
			
			
			
			
	}

	@Override
	public void insertUser(User user) {
		
		Session ses = HibernateUtil.getSession();
		
		ses.save(user);
		HibernateUtil.closeSession();
		log.info("User ADDED successfully");
	}

}
