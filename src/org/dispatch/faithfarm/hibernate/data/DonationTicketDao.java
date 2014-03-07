package org.dispatch.faithfarm.hibernate.data;

import java.util.List;

import org.dispatch.faithfarm.domain.DonationTicket;
import org.hibernate.HibernateException;

public class DonationTicketDao extends GenericDao {
	public DonationTicketDao() {
        super();
    }
	
    public DonationTicket find(Long id) throws HibernateException {
    	return (DonationTicket) super.findById(DonationTicket.class, id);
    }
    public Long save(DonationTicket intake) throws HibernateException {
    	return (Long) super.save(intake);
    }
    public void update(DonationTicket intake) throws HibernateException {
    	super.update(intake);
    }
    public void delete(DonationTicket intake) throws HibernateException {
    	super.delete(intake);
    }
    public List list() throws HibernateException {
    	return super.findAll(DonationTicket.class);
    }
}
