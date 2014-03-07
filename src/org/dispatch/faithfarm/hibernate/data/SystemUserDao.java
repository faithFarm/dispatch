package org.dispatch.faithfarm.hibernate.data;

import java.util.List;

import org.dispatch.faithfarm.domain.SystemUser;
import org.hibernate.HibernateException;

public class SystemUserDao extends GenericDao {
	
	public SystemUserDao() {
        super();
    }
	
    public SystemUser find(Long id) throws HibernateException {
    	return (SystemUser) super.findById(SystemUser.class, id);
    }
    public Long save(SystemUser intake) throws HibernateException {
    	return (Long) super.save(intake);
    }
    public void update(SystemUser intake) throws HibernateException {
    	super.update(intake);
    }
    public void delete(SystemUser intake) throws HibernateException {
    	super.delete(intake);
    }
    public List list() throws HibernateException {
    	return super.findAll(SystemUser.class);
    }
    public SystemUser authenticate(String username, String password) throws HibernateException {
    	return (SystemUser) super.authenticate(username, password);
    }
}
