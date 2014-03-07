package org.dispatch.faithfarm.hibernate.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.dispatch.faithfarm.domain.DailyLimit;
import org.dispatch.faithfarm.hibernate.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class DailyLimitDao {
	private static SessionFactory factory;
	private final static Logger LOGGER = Logger.getLogger(DailyLimitDao.class
			.getName());
	private static Session session;

	public DailyLimitDao() {
		LOGGER.setLevel(Level.INFO);

	}

	public DailyLimit findById(Long id) {
		Session session = null;
		DailyLimit DailyLimit = null; 
		try {
			session = HibernateUtil.currentSession();
			session.beginTransaction();
			DailyLimit = (DailyLimit) session.get(DailyLimit.class, id);
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
		return DailyLimit;
	}

	public DailyLimit authenticate(String username, String password) {

		Session session = null;
		DailyLimit user = null;

		try {
			session = HibernateUtil.currentSession();
			session.beginTransaction();

			Query q = session
					.createQuery("from DailyLimit where username = :username and password = :password");
			q.setString("username", username);
			q.setString("password", password);

			List result = q.list();
			if (result.size() > 0)
				user = (DailyLimit) result.get(0);
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

	public List listDailyLimits() {
		LOGGER.setLevel(Level.INFO);
		List<DailyLimit> list = new ArrayList<DailyLimit>();
		Session session = null;
		DailyLimit user = null;

		try {
			session = HibernateUtil.currentSession();
			session.beginTransaction();

			list = session.createQuery("FROM DailyLimit").list();
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

	/* Method to INSERT DailyLimit */
	public Long addDailyLimit(DailyLimit obj) {
		Long key = null;
		Session session = null;
		
		try {
			session = HibernateUtil.currentSession();
			session.beginTransaction();
			session.save(obj);
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

	/* Method to UPDATE DailyLimit */
	public void updateDailyLimit(DailyLimit obj) {
		Session session = null;
		DailyLimit user = null;

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

	/* Method to DELETE DailyLimit */
	public void deleteDailyLimit(Long key) {
		Session session = null;
		DailyLimit user = null;

		try {
			session = HibernateUtil.currentSession();
			session.beginTransaction();
			DailyLimit obj = (DailyLimit) session.get(DailyLimit.class, key);
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
	
	public int search(String dispatchDate, String farmBase ) {

		StringBuffer query = new StringBuffer("from DailyLimit where 1=1 ");
		long dailyLimitId=0;
		if (dispatchDate != null && dispatchDate.length() > 0)
			query.append(" and dispatchDate = :dispatchDate ");
		if (farmBase != null && farmBase.length() > 0)
			query.append(" and farmBase = :farmBase ");
		query.append(" Order by updatedDate DESC");
		
		DailyLimit limit = new DailyLimit();
		int dailyLimit = 0;
		Transaction tx = null;
		List list = null;
		try {
			session = HibernateUtil.currentSession();
			session.beginTransaction();
			Query q = session.createQuery(query.toString());

			if (dispatchDate != null && dispatchDate.length() > 0)
				q.setString("dispatchDate", dispatchDate);
			if (farmBase != null && farmBase.length() > 0)
				q.setString("farmBase", farmBase);
			
			list = q.list();
			
			if (list.size()>0) {
				limit = (DailyLimit)list.get(0);
				dailyLimit=limit.getDailyLimit();
			}
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
		
		
		return dailyLimit;
	}


}
