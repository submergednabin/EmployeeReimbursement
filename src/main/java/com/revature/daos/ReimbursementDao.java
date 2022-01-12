package com.revature.daos;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementStatus;
import com.revature.models.ReimbursementType;
import com.revature.models.User;
import com.revature.utils.HibernateUtil;

public class ReimbursementDao implements ReimbursementInterfaceDao {
	Logger log = (Logger) LogManager.getLogger();
	@Override
	public boolean insertReimbursement(String user_name, int reimbtypeId, int statusId, Reimbursement reimbursement) {
		
		Session ses = HibernateUtil.getSession();
		
		//query for finding user with unique username
		String hql = "FROM User where username = :uname";
		User user = (User) ses.createQuery(hql)
				.setParameter("uname", user_name)
				.uniqueResult();
		//query to find status with id
		String hql2 = "FROM ReimbursementStatus where status_id = :sid";
		ReimbursementStatus status = (ReimbursementStatus) ses.createQuery(hql2)
				.setParameter("sid", statusId)
				.uniqueResult();
		
		//Query for for finding Reimbursement Type
		String hql3 = "FROM ReimbursementType where reimb_type_id = :typeId";
		ReimbursementType reimbType = (ReimbursementType) ses.createQuery(hql3)
				.setParameter("typeId", reimbtypeId)
				.uniqueResult();
	
		reimbursement.setUser(user);
		reimbursement.setReimbType(reimbType);
		reimbursement.setStatus(status);
		ses.save(reimbursement);
		
		HibernateUtil.closeSession();
		log.info("Reimbursement Added By: " + user.getUsername());
		return true;
		

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Reimbursement> listAllReimbursement() {
		
		Session ses = HibernateUtil.getSession();
		List<Reimbursement> listAll = ses.createQuery("FROM Reimbursement").list();
		
		HibernateUtil.closeSession();
		log.info("Reimbursement list retrieved " );
		return listAll;
	}



	

	@Override
	@SuppressWarnings("unchecked")
	public List<Reimbursement> findReimbursementByStatusAndUserId(int status_id, String username) {
		
		Session ses = HibernateUtil.getSession();
		
		String hqlUser = "From User where username=:uname";
		User user = (User)ses.createQuery(hqlUser)
				.setParameter("uname", username)
				.uniqueResult();
			
		
		int userId = user.getId();
		
		String hql = "from Reimbursement where user_id=:ID1 and status_id= :ID2";
				
		List<Reimbursement> reimbursementList = (List<Reimbursement>) ses.createQuery(hql)
		.setParameter("ID1", userId)
		.setParameter("ID2", status_id)
		.list();

		
		HibernateUtil.closeSession();
		log.info("Reimbursement list retreived by username"+ user.getUsername() );
		return reimbursementList;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Reimbursement> findReimbursementByUserId(String username) {
		
		Session ses = HibernateUtil.getSession();
		
		String hqlUser = "From User where username=:uname";
		User user = (User)ses.createQuery(hqlUser)
				.setParameter("uname", username)
				.uniqueResult();
		
		
		int userId = user.getId();
//		System.out.println(userId);
		String hql = "from Reimbursement where user_id=:userID";
		List<Reimbursement> listReimbursement = ses.createQuery(hql)
				.setParameter("userID", userId)
				.list();
		
		HibernateUtil.closeSession();
		log.info("Reimbursement list retreived on the basis of UserId BY: "+ user.getUsername() );
		return listReimbursement;
	}

	@Override
	public int updateReimbursementById(int reimb_id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean deleteReimbursementByUserAndId(String username, int reimb_id) {
		
		try(Session ses = HibernateUtil.getSession()){
			
			Transaction tran = ses.beginTransaction();
			String hqlUser = "From User where username=:uname";
			User user = (User)ses.createQuery(hqlUser)
					.setParameter("uname", username)
					.uniqueResult();
			int userId = user.getId();
			
			String hql2 = "DELETE FROM Reimbursement where reimb_id=:rid AND user_id=:uid";
			Query q = ses.createQuery(hql2)
					.setParameter("rid", reimb_id)
					.setParameter("uid", userId);
			q.executeUpdate();
			tran.commit();
			log.info("Reimbursement Deleted  by username"+ user.getUsername() );
		}catch( HibernateException e	) {
			e.printStackTrace();
			System.out.println("Data Not Found in Database..");
			log.info("Reimbursement Deletion failed | User"+ username );
		}
		
		HibernateUtil.closeSession();
		return true;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Reimbursement> findReimbursementByStatusId(int statusId) {
		
		Session ses = HibernateUtil.getSession();

		String hql = "from Reimbursement where status_id=:sid";
		List<Reimbursement> listReimbursement = ses.createQuery(hql)
				.setParameter("sid", statusId)
				.list();
		
		HibernateUtil.closeSession();
		log.info("Reimbursement listed by Status  by username");
		return listReimbursement;

		
	}

	public boolean updateReimbursementByResolver(int reimbId, int statusId, String username) {
		
		try(Session ses = HibernateUtil.getSession()){
			
			Transaction tran = ses.beginTransaction();
			String hqlUser = "From User where username=:uname";
			User user = (User)ses.createQuery(hqlUser)
					.setParameter("uname", username)
					.uniqueResult();
			int resolveId = user.getId();
			
			String hql2 = "UPDATE Reimbursement SET resolver_id=:resid, status_id=:sid WHERE reimb_id=:rid";
			Query q = ses.createQuery(hql2)
					.setParameter("sid", statusId)
					.setParameter("resid", resolveId )
					.setParameter("rid", reimbId);
			q.executeUpdate();
			tran.commit();
			log.info("Reimbursement Updated  by username"+ user.getUsername() );
		}catch( HibernateException e) {
			e.printStackTrace();
			System.out.println("Data Not Found in Database..");
			log.info("Reimbursement update Failed | username"+ username );
		}
		
		HibernateUtil.closeSession();
		return true;
	}

	public Reimbursement getReimbursementIndividualDetails(int reimbId, String username) {
		
		
			Session ses = HibernateUtil.getSession();
			String hqlUser = "From User where username=:uname";
			User user = (User)ses.createQuery(hqlUser)
					.setParameter("uname", username)
					.uniqueResult();
			
			
			int userId = user.getId();
			
			String hql = "FROM Reimbursement where reimb_id=:rid AND user_id=:uid";
			Reimbursement reimbursement = (Reimbursement) ses.createQuery(hql)
					.setParameter("rid", reimbId)
					.setParameter("uid", userId)
					.uniqueResult();
		
		
		
		HibernateUtil.closeSession();
		log.info("Each Reimbursement retrieved  by username"+ user.getUsername() );
		return reimbursement;
	}

}
