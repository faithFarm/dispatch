package org.dispatch.faithfarm.hibernate.data;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.dispatch.faithfarm.domain.DailyLimit;
import org.dispatch.faithfarm.domain.SystemUser;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class GenericDao {

	private static SessionFactory factory;
	private final static Logger LOGGER = Logger.getLogger(SessionFactory.class
			.getName());
	
	public GenericDao() {
		LOGGER.setLevel(Level.SEVERE);		
	}
	
	public List listAll(Class c) {
		Object obj = null;
		Session session = null;
		List list = new ArrayList();
		try {
			StringBuffer q = new StringBuffer("");
			q.append("from " + c.getName());
			session = HibernateFactory.openSession();
			session.beginTransaction();
			Query query = session.createQuery(q.toString());
			list = query.list();
			if (session.isOpen()) {
				session.flush();
				session.getTransaction().commit();
			}
		} catch (Exception e) {
			if (session.isOpen())
				session.getTransaction().rollback();
			e.printStackTrace();
			throw new HibernateException(e);
		} finally {
			if (session.isOpen())
				session.close();
		}
		return list;
	}

	public Object findById(Class c,Long id) {
		Object obj=null;
		Session session=null;
		try {
			session = HibernateFactory.openSession();
			session.beginTransaction();
			obj = session.get(c, id);
			session.flush();
			session.getTransaction().commit();
		} catch (Exception e) {		
			session.getTransaction().rollback();
			e.printStackTrace();
			throw new HibernateException(e);
		} finally {
			session.close();
		}
		return obj;
	}
	
	
	public Long save(Object obj) {
		Long key = null;
		Session session=null;
		try {
			session = HibernateFactory.openSession();
			session.beginTransaction();
			key = (Long) session.save(obj);
			session.flush();
			session.getTransaction().commit();
			
		} catch (HibernateException e) {			
			session.getTransaction().rollback();
			e.printStackTrace();
			throw new HibernateException(e);
		} finally {			
			session.close();
		}
		return key;
	}
	
	public void update(Object obj) {
		Session session=null;

		try {
			session = HibernateFactory.openSession();
			session.beginTransaction();
			session.update(obj);
			session.flush();
			session.getTransaction().commit();
		} catch (HibernateException e) {			
			session.getTransaction().rollback();
			e.printStackTrace();
			throw new HibernateException(e);
		} finally {			
			session.close();
		}
	}
	
	public void delete(Object obj) {
		Session session=null;

		try {
			session = HibernateFactory.openSession();
			session.beginTransaction();
			session.delete(obj);
			session.flush();
			session.getTransaction().commit();
		} catch (HibernateException e) {			
			session.getTransaction().rollback();
			e.printStackTrace();
			throw new HibernateException(e);
		} finally {			
			session.close();
		}
	}
	
	 protected List findAll(Class clazz) {
			Session session=null;

	        List objects = null;
	        try {
	        	session = HibernateFactory.openSession();
				session.beginTransaction();
	            Query query = session.createQuery("from " + clazz.getName());
	            objects = query.list();
	        	session.flush();
	        	session.getTransaction().commit();
	        } catch (HibernateException e) {	        	
	        	session.getTransaction().rollback();
				e.printStackTrace();
				throw new HibernateException(e);
			} finally {				
				session.close();
			}
	        return objects;
	    }
	 
	 protected List findAllByFarm(Class clazz, String farm) {
			Session session=null;
	        List objects = null;
	        try {
	        	session = HibernateFactory.openSession();
				session.beginTransaction();
	            Query query = session.createQuery("from " + clazz.getName() + " where farmBase = :farmBase ");
	            query.setString("farmBase", farm);
	            objects = query.list();
	        	session.flush();
	        	session.getTransaction().commit();
	        } catch (HibernateException e) {	        	
	        	session.getTransaction().rollback();
				e.printStackTrace();
				throw new HibernateException(e);
			} finally {				
				session.close();
			}
	        return objects;
	    }
	 
		public DailyLimit search(String dispatchDate, String farmBase ) {

			Session session=null;
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
				session = HibernateFactory.openSession();
				session.beginTransaction();
				Query q = session.createQuery(query.toString());

				if (dispatchDate != null && dispatchDate.length() > 0)
					q.setString("dispatchDate", dispatchDate);
				if (farmBase != null && farmBase.length() > 0)
					q.setString("farmBase", farmBase);
				
				list = q.list();
				
				if (list.size()>0) {
					limit = (DailyLimit)list.get(0);
					//dailyLimit=limit.getDailyLimit();
				}
				session.flush();
				session.getTransaction().commit();
			} catch (HibernateException e) {				
				session.getTransaction().rollback();
				e.printStackTrace();
				throw new HibernateException(e);
			} finally {				
				session.close();			
			}
			return limit;
		}
	 
	 public List search(String firstname,String lastname, String confirmation, String dispatchDate, String zipcode, String status, String special, String farmBase ) {

			Session session=null;
			StringBuffer query = new StringBuffer("from DonationTicket where 1=1 ");
			long donationId=0;
			List list = null;
			
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
			
			try {
				session = HibernateFactory.openSession();
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
				session.flush();
				session.getTransaction().commit();
			} catch (HibernateException e) {				
				session.getTransaction().rollback();
				e.printStackTrace();
				throw new HibernateException(e);
			} finally {				
				session.close();			
			}
			return list;
	 }
	



		public List findByObjectId(Class c, String objectIdName, Long id) {

			LOGGER.setLevel(Level.INFO);
			Session session=null;
			List<Object> list = new ArrayList<Object>();
			try {
				session = HibernateFactory.openSession();
				session.beginTransaction();
				StringBuffer query = new StringBuffer(
						"from "+c.getName()+" where "+objectIdName+" = :"+objectIdName);
				Query q = session.createQuery(query.toString());
				q.setLong(objectIdName, id);
				list = q.list();
				session.flush();
				session.getTransaction().commit();
			} catch (Exception e) {				
				session.getTransaction().rollback();
				e.printStackTrace();
				throw new HibernateException(e);
			} finally {				
				session.close();
			}
			return list;
		}
		
		public List findByIntakeId(Class c, Long id) {

			LOGGER.setLevel(Level.INFO);
			Session session=null;
			List<Object> list = new ArrayList<Object>();
			try {
				session = HibernateFactory.openSession();
				session.beginTransaction();
				StringBuffer query = new StringBuffer(
						"from "+c.getName()+" where intakeId = :intakeId ");
				Query q = session.createQuery(query.toString());
				q.setLong("intakeId", id);
				list = q.list();
				session.flush();
				session.getTransaction().commit();
			} catch (Exception e) {				
				session.getTransaction().rollback();
				e.printStackTrace();
				throw new HibernateException(e);
			} finally {				
				session.close();
			}
			return list;
		}
		
		public SystemUser authenticate(String username, String password) {
			SystemUser user=null;
			Session session=null;
			try {
				session = HibernateFactory.openSession();
				session.beginTransaction();
				Query q = session
						.createQuery("from SystemUser where username = :username and password = :password");
				q.setString("username", username);
				q.setString("password", password);

				List result = q.list();
				if (result.size() > 0)
					user = (SystemUser) result.get(0);

				session.flush();
				session.getTransaction().commit();
			} catch (Exception e) {				
				session.getTransaction().rollback();
				e.printStackTrace();
				throw new HibernateException(e);
			} finally {				
				session.close();
			}

			return user;
		}
}
