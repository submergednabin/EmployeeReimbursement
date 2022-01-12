package com.revature.daos;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;

import com.revature.models.ReimbursementType;
import com.revature.utils.HibernateUtil;

public class ReimbursementTypeDao implements ReimbursementTypeInterfaceDao {
	Logger log = (Logger) LogManager.getLogger();
	@Override
	public List<ReimbursementType> findAllReimbType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertReimbursementType(ReimbursementType type) {
		
		Session ses = HibernateUtil.getSession();
		
		ses.save(type);
		log.info("Reimbursement Type Added");
		HibernateUtil.closeSession();

	}

}
