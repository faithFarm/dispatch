package org.dispatch.faithfarm.hibernate.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.dispatch.faithfarm.domain.CallLog;
import org.dispatch.faithfarm.hibernate.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class CallLogDao {
	private static SessionFactory factory;
	private final static Logger LOGGER = Logger.getLogger(CallLogDao.class
			.getName());
	private static Session session;

	public CallLogDao() {
		LOGGER.setLevel(Level.INFO);

	}

	public CallLog findById(Long id) {
		Session session = null;
		CallLog CallLog = null; 
		try {
			session = HibernateUtil.currentSession();
			session.beginTransaction();
			CallLog = (CallLog) session.get(CallLog.class, id);
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
		return CallLog;
	}

	public CallLog authenticate(String username, String password) {

		Session session = null;
		CallLog user = null;

		try {
			session = HibernateUtil.currentSession();
			session.beginTransaction();

			Query q = session
					.createQuery("from CallLog where username = :username and password = :password");
			q.setString("username", username);
			q.setString("password", password);

			List result = q.list();
			if (result.size() > 0)
				user = (CallLog) result.get(0);
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

	public List listCallLogs() {
		LOGGER.setLevel(Level.INFO);
		List<CallLog> list = new ArrayList<CallLog>();
		Session session = null;
		CallLog user = null;

		try {
			session = HibernateUtil.currentSession();
			session.beginTransaction();

			list = session.createQuery("FROM CallLog").list();
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
	
	public List search(String farmBase) {
		LOGGER.setLevel(Level.INFO);
		List<CallLog> list = new ArrayList<CallLog>();
		Session session = null;
		CallLog user = null;
		StringBuffer query = new StringBuffer("from CallLog where 1=1 ");
		if (farmBase != null && farmBase.length() > 0)
			query.append(" and farmBase = :farmBase or callAgent =:farmBase");
		query.append(" Order by callDate ");
		
		try {
			session = HibernateUtil.currentSession();
			session.beginTransaction();
			Query q = session.createQuery(query.toString());
			if (farmBase != null && farmBase.length() > 0)
				q.setString("farmBase", farmBase);
			list = q.list();
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


	/* Method to INSERT CallLog */
	public Long addCallLog(CallLog obj) {
		
		Long key = null;
		Session session = null;
		CallLog user = null;

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

	/* Method to UPDATE CallLog */
	public void updateCallLog(CallLog obj) {
		Session session = null;
		CallLog user = null;

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

	/* Method to DELETE CallLog */
	public void deleteCallLog(Long key) {
		Session session = null;
		CallLog user = null;

		try {
			session = HibernateUtil.currentSession();
			session.beginTransaction();
			CallLog obj = (CallLog) session.get(CallLog.class, key);
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
	
	


}
