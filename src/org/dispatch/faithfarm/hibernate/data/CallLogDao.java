package org.dispatch.faithfarm.hibernate.data;

import java.util.List;

import org.dispatch.faithfarm.domain.CallLog;
import org.hibernate.HibernateException;

public class CallLogDao extends GenericDao {
	public CallLogDao() {
        super();
    }
	
    public CallLog find(Long id) throws HibernateException {
    	return (CallLog) super.findById(CallLog.class, id);
    }
    public Long save(CallLog intake) throws HibernateException {
    	return (Long) super.save(intake);
    }
    public void update(CallLog intake) throws HibernateException {
    	super.update(intake);
    }
    public void delete(CallLog intake) throws HibernateException {
    	super.delete(intake);
    }
    public List list() throws HibernateException {
    	return super.findAll(CallLog.class);
    }
    
}
