package org.dispatch.faithfarm.hibernate.data;

import java.util.List;

import org.dispatch.faithfarm.domain.DailyLimit;
import org.hibernate.HibernateException;

public class DailyLimitDao extends GenericDao {
	
	public DailyLimitDao() {
        super();
    }
	
    public DailyLimit find(Long id) throws HibernateException {
    	return (DailyLimit) super.findById(DailyLimit.class, id);
    }
    public Long save(DailyLimit intake) throws HibernateException {
    	return (Long) super.save(intake);
    }
    public void update(DailyLimit intake) throws HibernateException {
    	super.update(intake);
    }
    public void delete(DailyLimit intake) throws HibernateException {
    	super.delete(intake);
    }
    public List list() throws HibernateException {
    	return super.findAll(DailyLimit.class);
    }
}
