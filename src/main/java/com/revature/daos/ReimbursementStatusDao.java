package com.revature.daos;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;

import com.revature.models.ReimbursementStatus;
import com.revature.utils.HibernateUtil;

public class ReimbursementStatusDao implements ReimbursementStatusInterfaceDao {
	Logger log = (Logger) LogManager.getLogger();
	@Override
	public List<ReimbursementStatus> listAllStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(ReimbursementStatus status) {
		
		Session ses = HibernateUtil.getSession();
		
		ses.save(status);
		HibernateUtil.closeSession();
		log.info("Reimbursement Status ADDED Successfully");
	

	}

}
