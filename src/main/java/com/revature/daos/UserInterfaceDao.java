package com.revature.daos;

import java.util.List;

import com.revature.models.User;

public interface UserInterfaceDao {
	
	public List<User> listAllUser();
	
	public List<User> findUserById();
	
	public User isLoggedIn(String username, String password);
	
	public void insertUser(User user);
}
