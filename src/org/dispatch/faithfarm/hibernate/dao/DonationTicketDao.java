package org.dispatch.faithfarm.hibernate.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.dispatch.faithfarm.domain.DonationTicket;
import org.dispatch.faithfarm.hibernate.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class DonationTicketDao {
	private static SessionFactory factory;
	private final static Logger LOGGER = Logger.getLogger(DonationTicketDao.class
			.getName());
	private static Session session;

	public DonationTicketDao() {
		LOGGER.setLevel(Level.INFO);

	}

	public DonationTicket findById(Long id) {
		Session session = null;
		DonationTicket DonationTicket = null; 
		try {
			session = HibernateUtil.currentSession();
			session.beginTransaction();
			DonationTicket = (DonationTicket) session.get(DonationTicket.class, id);
			session.getTransaction().commit();
		} catch (Exception e) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			e.printStackTrace();
		}
		finally {
			//session.flush();
			session.clear();
		}
		return DonationTicket;
	}

	public DonationTicket authenticate(String username, String password) {

		Session session = null;
		DonationTicket user = null;

		try {
			session = HibernateUtil.currentSession();
			session.beginTransaction();

			Query q = session
					.createQuery("from DonationTicket where username = :username and password = :password");
			q.setString("username", username);
			q.setString("password", password);

			List result = q.list();
			if (result.size() > 0)
				user = (DonationTicket) result.get(0);
			session.getTransaction().commit();
		}  catch (Exception e) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			e.printStackTrace();
		}
		finally {
			//session.flush();
			session.clear();
		}

		return user;
	}

	public List listDonationTickets() {
		LOGGER.setLevel(Level.INFO);
		List<DonationTicket> list = new ArrayList<DonationTicket>();
		Session session = null;
		DonationTicket user = null;

		try {
			session = HibernateUtil.currentSession();
			session.beginTransaction();

			list = session.createQuery("FROM DonationTicket").list();
			session.getTransaction().commit();
		}  catch (Exception e) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			e.printStackTrace();
		}
		finally {
			//session.flush();
			session.clear();
		}
		return list;
	}

	/* Method to INSERT DonationTicket */
	public Long addDonationTicket(DonationTicket obj) {
		
		Long key = null;
		Session session = null;
		DonationTicket user = null;

		try {
			session = HibernateUtil.currentSession();
			session.beginTransaction();
			key = (Long) session.save(obj);
			session.getTransaction().commit();
		} catch (Exception e) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			e.printStackTrace();
		}
		finally {
			//session.flush();
			session.clear();
		}
		return key;
	}

	/* Method to UPDATE DonationTicket */
	public void updateDonationTicket(DonationTicket obj) {
		Session session = null;
		DonationTicket user = null;

		try {
			session = HibernateUtil.currentSession();
			session.beginTransaction();
			session.update(obj);
			session.getTransaction().commit();
		} catch (Exception e) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			e.printStackTrace();
		}
		finally {
			//session.flush();
			session.clear();
		}
	}

	/* Method to DELETE DonationTicket */
	public void deleteDonationTicket(Long key) {
		Session session = null;
		DonationTicket user = null;

		try {
			session = HibernateUtil.currentSession();
			session.beginTransaction();
			DonationTicket obj = (DonationTicket) session.get(DonationTicket.class, key);
			session.delete(obj);
			session.getTransaction().commit();
		} catch (Exception e) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			e.printStackTrace();
		}
		finally {
			//session.flush();
			session.clear();
		}
	}
	
	public List search(String firstname,String lastname, String confirmation, String dispatchDate, String zipcode, String status, String special, String farmBase ) {

		StringBuffer query = new StringBuffer("from DonationTicket where 1=1 ");
		long donationId=0;
		if (confirmation!=null&&confirmation.length()>0)
			donationId=new Long(confirmation);
		
		
		if (lastname != null && lastname.length() > 0)
			query.append(" and lastname = :lastname ");
		if (firstname != null && firstname.length() > 0)
			query.append(" and firstname = :firstname ");
		if (confirmation != null && confirmation.length() > 0)
			query.append(" and donationId = :donationId ");
		if (dispatchDate != null && dispatchDate.length() > 0)
			query.append(" and dispatchDate = :dispatchDate ");
		if (zipcode != null && zipcode.length() > 0)
			query.append(" and zipcode = :zipcode ");
		if (status != null && status.length() > 0)
			query.append(" and status = :status ");
		if (special != null && special.length() > 0)
			query.append(" and specialFlag = :specialFlag ");
		if (farmBase != null && farmBase.length() > 0)
			query.append(" and farmBase = :farmBase ");
		query.append(" Order by zipcode ");
		
	
		
		Transaction tx = null;
		List list = null;
		try {
			session = HibernateUtil.currentSession();
			session.beginTransaction();
		
			Query q = session.createQuery(query.toString());
			q.setMaxResults(200);
			if (lastname != null && lastname.length() > 0)
				q.setString("lastname", lastname);
			if (firstname != null && firstname.length() > 0)
				q.setString("firstname", firstname);
			if (dispatchDate != null && dispatchDate.length() > 0)
				q.setString("dispatchDate", dispatchDate);
			if (confirmation != null && confirmation.length() > 0)
				q.setLong("donationId", donationId);
			if (zipcode != null && zipcode.length() > 0 )
				q.setString("zipcode", zipcode);
			if (status != null && status.length() > 0)
				q.setString("status", status);
			if (special != null && special.length() > 0)
				q.setString("specialFlag", special);
			if (farmBase != null && farmBase.length() > 0)
				q.setString("farmBase", farmBase);
			list = q.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			e.printStackTrace();
		}
		finally {
			//session.flush();
			session.clear();
		}
		return list;
	}
	
	
	public List reportSearch(String farmBase ) {

		StringBuffer query = new StringBuffer("from DonationTicket where 1=1 ");
		long donationId=0;
		
		if (farmBase != null && farmBase.length() > 0)
			query.append(" and farmBase = :farmBase ");
		query.append(" Order by callDate ");
		
	
		
		Transaction tx = null;
		List list = null;
		try {
			session = HibernateUtil.currentSession();
			session.beginTransaction();
		
			Query q = session.createQuery(query.toString());
			q.setMaxResults(200);
			
			if (farmBase != null && farmBase.length() > 0)
				q.setString("farmBase", farmBase);
			list = q.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			if (session != null) {
				session.getTransaction().rollback();
			}
			e.printStackTrace();
		}
		finally {
			//session.flush();
			session.clear();
		}
		return list;
	}


}
