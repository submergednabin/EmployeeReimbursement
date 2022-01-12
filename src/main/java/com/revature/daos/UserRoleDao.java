package com.revature.daos;

import org.hibernate.Session;

import com.revature.models.UserRole;
import com.revature.utils.HibernateUtil;

public class UserRoleDao implements UserRoleInterface {

	@Override
	public void insert(UserRole userrole) {
		
		Session ses = HibernateUtil.getSession();
		
		ses.save(userrole);
		
		HibernateUtil.closeSession();
		
		
	}

}
