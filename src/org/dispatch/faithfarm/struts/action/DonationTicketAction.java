package org.dispatch.faithfarm.struts.action;

import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.text.WordUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.dispatch.faithfarm.domain.CallLog;
import org.dispatch.faithfarm.domain.Constants;
import org.dispatch.faithfarm.domain.DailyLimit;
import org.dispatch.faithfarm.domain.DonationTicket;
import org.dispatch.faithfarm.domain.ErrorMessage;
import org.dispatch.faithfarm.domain.SystemUser;
import org.dispatch.faithfarm.hibernate.data.CallLogDao;
import org.dispatch.faithfarm.hibernate.data.DailyLimitDao;
import org.dispatch.faithfarm.hibernate.data.DonationTicketDao;
import org.dispatch.faithfarm.struts.form.DonationTicketForm;
import org.dispatch.faithfarm.utils.HtmlDropDownBuilder;
import org.dispatch.faithfarm.utils.Validator;

public class DonationTicketAction extends Action {

	private final static Logger LOGGER = Logger.getLogger(DonationTicketAction.class
			.getName());
	private final static HtmlDropDownBuilder html = new HtmlDropDownBuilder();

	public ActionForward execute(ActionMapping mapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse response) {

		LOGGER.setLevel(Level.SEVERE);
		LOGGER.log(Level.INFO, "In dispatch action...");
		this.clearMessages(request);

		SystemUser user = (SystemUser) request.getSession().getAttribute(
				"USER_" + request.getSession().getId());
		
		DonationTicketForm form = (DonationTicketForm) actionForm;
		
		if (user==null)
			return mapping.findForward(Constants.LOGIN);
		
		if ("HOME".equals(((DonationTicketForm) actionForm).getAction())) {
			this.loadLimits(request);
			return mapping.findForward(Constants.HOME);
		}
		else if ("Return to Ticket".equals(((DonationTicketForm) actionForm).getAction())) {
			return mapping.findForward(Constants.NEW);
		}
		else if ("Print All".equals(((DonationTicketForm) actionForm).getAction())) {
			
			List<DonationTicket> printList = new ArrayList<DonationTicket>();
			for (java.util.Iterator<DonationTicket> iterator =
						form.getResults().iterator(); iterator.hasNext();) {
					    DonationTicket ticket = (DonationTicket) iterator.next();
					    ticket.setCreationDate(Validator.convertEpoch(ticket.getCreationDate()));
					    printList.add(ticket);
			}
			form.setPrintList(printList);
			return mapping.findForward(Constants.PRINTALL);
		}
		else if ("Print".equals(((DonationTicketForm) actionForm).getAction())) {
			String callDate=form.getTicket().getCreationDate();
			form.getTicket().setCreationDate(Validator.convertEpoch(callDate));
			return mapping.findForward(Constants.PRINT);
		}
		else if ("SaveLevel".equals(((DonationTicketForm) actionForm).getAction())) {
			DailyLimitDao dao = new DailyLimitDao();
			form.getLimit().setFarmBase(user.getFarmBase());
			form.getLimit().setUpdatedBy(user.getUsername());
			form.getLimit().setUpdatedDate(Validator.getEpoch()+"");
			dao.save(form.getLimit());
			return mapping.findForward(Constants.HOME);
		}
		else if ("Level".equals(((DonationTicketForm) actionForm).getAction())) {
			form.setLimit(new DailyLimit());
			
			return mapping.findForward(Constants.LEVEL);
		}
		else if ("New".equals(((DonationTicketForm) actionForm).getAction())) {
			form.setErrors(new ArrayList<ErrorMessage>());
			form.setTicket(new DonationTicket());
			form.getTicket().setStatus("PENDING");
			form.getTicket().setFarmBase(user.getFarmBase());
			return mapping.findForward(Constants.NEW);
		} 
		else if ("Search".equals(((DonationTicketForm) actionForm)
				.getAction())) {
			DonationTicketDao dao = new DonationTicketDao();
			String dispatchDate=((DonationTicketForm) actionForm).getDispatchDate();
			if ("pickup date".equals(dispatchDate)) dispatchDate="";
			if ("".equals(dispatchDate)) {
				GregorianCalendar calendar = new GregorianCalendar();
				Date now = calendar.getTime();
				DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
				dispatchDate=df.format(now);
			}
			List list = dao.search(null, null, null, dispatchDate, null, null, null, user.getFarmBase());
			((DonationTicketForm) actionForm).setResults(list);

			if (list.size()>199) 
				((DonationTicketForm) actionForm).setMessage("More than 200 results were returned. Please narrow your search.");
			
			
			return mapping.findForward(Constants.SEARCH);
		}
		else if ("SearchTickets".equals(((DonationTicketForm) actionForm)
				.getAction())) {
			form.setFarmBase(user.getFarmBase());
			DonationTicketDao dao = new DonationTicketDao();
			
			String lastname=((DonationTicketForm) actionForm).getLastname();
			String firstname=((DonationTicketForm) actionForm).getFirstname();
			String confirmation=((DonationTicketForm) actionForm).getConfirmation();
			String zipcode=((DonationTicketForm) actionForm).getZipcode();
			String dispatchDate=((DonationTicketForm) actionForm).getDispatchDate();
			String special=((DonationTicketForm) actionForm).getSpecialFlag();
			String status=((DonationTicketForm) actionForm).getStatus();
			if ("lastname".equals(lastname)) lastname="";
			if ("firstname".equals(firstname)) firstname="";
			if ("conf#".equals(confirmation)) confirmation="";
			if ("zipcode".equals(zipcode)) zipcode="";
			if ("pickup date".equals(dispatchDate)) dispatchDate="";
			if ("status".equals(status)) status="";
			if ("special".equals(special)) special="";
			  
			List list = dao.search(firstname, lastname, confirmation, dispatchDate, zipcode, status, special,user.getFarmBase());
			((DonationTicketForm) actionForm).setResults(list);
			
			if (list.size()>199) 
				((DonationTicketForm) actionForm).setMessage("More than 200 results were returned. Please narrow your search.");

			return mapping.findForward(Constants.SEARCH);
		} 
		else if ("SearchDonor".equals(((DonationTicketForm) actionForm)
				.getAction())) {
			
			DonationTicketDao dao = new DonationTicketDao();
			
			String lastname=((DonationTicketForm) actionForm).getTicket().getLastname();
			String firstname=((DonationTicketForm) actionForm).getTicket().getFirstname();
			List list = dao.search(firstname, lastname, null, null, null, null, null,null);
			((DonationTicketForm) actionForm).setResults(list);		
			return mapping.findForward(Constants.DONORS);
		} 
		else if ("Log".equals(((DonationTicketForm) actionForm).getAction())) {
			return mapping.findForward(Constants.LOG);
		}
		else if ("Continue".equals(((DonationTicketForm) actionForm)
				.getAction())) {
			boolean ok = false;
			CallLogDao dao = new CallLogDao();
			if (this.saveCallLog(form, user, request)) {
				this.resetForm(form);
				return mapping.findForward(Constants.NEW);
			} else
				return mapping.findForward(Constants.ERROR);

		} 
		else if ("Find".equals(((DonationTicketForm) actionForm).getAction())) {
			return mapping.findForward(Constants.FIND);
		} 
		else if ("LEVEL"
				.equals(((DonationTicketForm) actionForm).getAction())) {
			return mapping.findForward(Constants.LEVEL);
		} 
		else if ("Save Ticket".equals(((DonationTicketForm) actionForm)
				.getAction())) {
			form.setFarmBase(user.getFarmBase());
			boolean ok = this.validate(form, user);

			if (ok)
				if (this.saveTicket(form, user, request)) {
					this.resetForm(form);
					return mapping.findForward(Constants.LOG);
				} else
					return mapping.findForward(Constants.ERROR);
			else
				return mapping.findForward(Constants.FAILURE);
		}
		else if ("Update".equals(((DonationTicketForm) actionForm)
				.getAction())) {
			
			DonationTicketDao dao = new DonationTicketDao();
			DonationTicket ticket = dao.find(new Long(((DonationTicketForm) actionForm).getKey()));
			this.resetForm((DonationTicketForm) actionForm);
			((DonationTicketForm) actionForm).setTicket(ticket);
			((DonationTicketForm) actionForm).setCallDate( Validator.convertEpoch(((DonationTicketForm) actionForm).getTicket().getCreationDate()));
			return mapping.findForward(Constants.NEW);

		}
		else if ("Delete".equals(((DonationTicketForm) actionForm)
				.getAction())) {
			
			
			//delete and reload
			DonationTicketDao dao = new DonationTicketDao();
			DonationTicket ticket = dao.find(new Long(form.getKey()));
			dao.delete(ticket);
			
			String lastname=((DonationTicketForm) actionForm).getLastname();
			String firstname=((DonationTicketForm) actionForm).getFirstname();
			String confirmation=((DonationTicketForm) actionForm).getConfirmation();
			String zipcode=((DonationTicketForm) actionForm).getZipcode();
			String dispatchDate=((DonationTicketForm) actionForm).getDispatchDate();
			String special=((DonationTicketForm) actionForm).getSpecialFlag();
			String status=((DonationTicketForm) actionForm).getStatus();
			
			if ("lastname".equals(lastname)) lastname="";
			if ("firstname".equals(firstname)) firstname="";
			if ("conf#".equals(confirmation)) confirmation="";
			if ("zipcode".equals(zipcode)) zipcode="";
			if ("pickup date".equals(dispatchDate)) dispatchDate="";
			if ("status".equals(status)) status="";
			if ("special".equals(special)) special="";
			  
			List list = dao.search(firstname, lastname, confirmation, dispatchDate, zipcode, status, special,user.getFarmBase());
			form.setResults(list);
			return mapping.findForward(Constants.SEARCH);
		}
		else if ("ExistingDonor".equals(((DonationTicketForm) actionForm)
				.getAction())) {
			
			//retrieve existing ticket and copy name/personal info to new ticket
			DonationTicketDao dao = new DonationTicketDao();
			DonationTicket ticket = dao.find( new Long(form.getKey()) );
			
			DonationTicket newTicket = new DonationTicket();
			newTicket.setFirstname(ticket.getFirstname());
			newTicket.setLastname(ticket.getLastname());
			newTicket.setSuffix(ticket.getSuffix());
			newTicket.setFarmBase(ticket.getFarmBase());
			newTicket.setAddressLine1(ticket.getAddressLine1());
			newTicket.setAddressLine2(ticket.getAddressLine2());
			newTicket.setCity(ticket.getCity());
			newTicket.setState(ticket.getState());
			newTicket.setZipcode(ticket.getZipcode());
			newTicket.setBuilding(ticket.getBuilding());
			newTicket.setContactPhone(ticket.getContactPhone());
			newTicket.setPhoneOther(ticket.getPhoneOther());
			newTicket.setElevatorFlag(ticket.getElevatorFlag());
			newTicket.setEmailAddress(ticket.getEmailAddress());
			newTicket.setGateCode(ticket.getGateCode());
			newTicket.setGatedFlag(ticket.getGatedFlag());
			newTicket.setGateInstructions(ticket.getGateInstructions());
			newTicket.setStreetSuffix(ticket.getStreetSuffix());
			newTicket.setSubdivision(ticket.getSubdivision());
			newTicket.setUnit(ticket.getUnit());
			newTicket.setMajorIntersection(ticket.getMajorIntersection());
			newTicket.setStructureType(ticket.getStructureType());
			
			((DonationTicketForm) actionForm).setTicket(newTicket);
			return mapping.findForward(Constants.NEW);
		}
		else {
			this.loadLimits(request);
			return mapping.findForward(Constants.SUCCESS);
		}
	}

	private boolean saveTicket(DonationTicketForm form, SystemUser user,
			HttpServletRequest request) {

		boolean ok = true;
		form.getTicket().setFarmBase(user.getFarmBase());
		form.getTicket().setCreatedBy(user.getUsername());
		form.getTicket().setCreationDate(Validator.getEpoch() + "");
		try {
			DonationTicketDao dao = new DonationTicketDao();

			if (form.getTicket().getDonationId() == null) {
				long id = dao.save(form.getTicket());
				request.getSession().setAttribute(
						"MESSAGE_" + request.getSession().getId(),
						"Ticket has been successfully saved. Confirmation #"
								+ id);
				form.getTicket().setDonationId(id);
				if (form.getTicket().getEmailAddress()!=null&&form.getTicket().getEmailAddress().length()>10&&form.getTicket().getEmailAddress().contains("@"))
					this.sendEmail(form.getTicket());
			} else {
				dao.update(form.getTicket());
				if (form.getTicket().getEmailAddress()!=null&&form.getTicket().getEmailAddress().length()>10&&form.getTicket().getEmailAddress().contains("@"))
					this.sendEmail(form.getTicket());

				request.getSession().setAttribute(
						"MESSAGE_" + request.getSession().getId(),
						"Ticket has been successfully updated");
			}
		} catch (Exception e) {
			ok = false;
			request.getSession().setAttribute(
					"ERROR_" + request.getSession().getId(),
					"Error occurring is Action.saveTicket: " + e.getMessage());
			e.printStackTrace();
		}

		return ok;

	}

	private boolean saveCallLog(DonationTicketForm form, SystemUser user,
			HttpServletRequest request) {

		boolean ok = true;
		form.getCallLog().setFarmBase(user.getFarmBase());
		form.getCallLog().setCallAgent(user.getUsername());
		form.getCallLog().setCallDate(Validator.getEpoch() + "");
		try {
			CallLogDao dao = new CallLogDao();
			long id = dao.save(form.getCallLog());
		} catch (Exception e) {
			ok = false;
			request.getSession().setAttribute(
					"ERROR_" + request.getSession().getId(),
					"Error occurring is Action.saveCallLog: " + e.getMessage());
			e.printStackTrace();
		}

		return ok;

	}

	public boolean validate(DonationTicketForm form, SystemUser user) {
		boolean ok = true;

		ArrayList<ErrorMessage> errors = new ArrayList<ErrorMessage>();

		if (form.getTicket().getLastname() == null
				|| form.getTicket().getLastname().length() == 0) {
			errors.add(new ErrorMessage("", "lastname is required"));
			ok = false;
		}
		if (form.getTicket().getFirstname() == null
				|| form.getTicket().getFirstname().length() == 0) {
			errors.add(new ErrorMessage("", "firstname is required"));
			ok = false;
		}
		if (form.getTicket().getAddressLine1() == null
				|| form.getTicket().getAddressLine1().length() == 0) {
			errors.add(new ErrorMessage("", "address line 1 is required"));
			ok = false;
		}
		if (form.getTicket().getCity() == null
				|| form.getTicket().getCity().length() == 0) {
			errors.add(new ErrorMessage("", "city is required"));
			ok = false;
		}
		if (form.getTicket().getZipcode() == null
				|| form.getTicket().getZipcode().length() == 0) {
			errors.add(new ErrorMessage("", "zipcode is required"));
			ok = false;
		}
		if (form.getTicket().getContactPhone() == null
				|| form.getTicket().getContactPhone().length() == 0) {
			errors.add(new ErrorMessage("", "contact phone is required"));
			ok = false;
		}
		if (form.getTicket().getMajorIntersection() == null
				|| form.getTicket().getMajorIntersection().length() == 0) {
			errors.add(new ErrorMessage("", "major intersection is required"));
			ok = false;
		}
		if (form.getTicket().getSubdivision() == null
				|| form.getTicket().getSubdivision().length() == 0) {
			errors.add(new ErrorMessage("", "subdivision is required"));
			ok = false;
		}
		if (form.getTicket().getStreetSuffix() == null
				|| form.getTicket().getStreetSuffix().length() == 0) {
			errors.add(new ErrorMessage("", "street suffix is required"));
			ok = false;
		}
		if (form.getTicket().getStructureType() == null
				|| form.getTicket().getStructureType().length() == 0) {
			errors.add(new ErrorMessage("", "structure type is required"));
			ok = false;
		}
		if ("callbox code".equals(form.getTicket().getGateInstructions())) {
			if (form.getTicket().getGateCode() == null
					|| form.getTicket().getGateCode().length() == 0) {
				errors.add(new ErrorMessage("", "gate code is required"));
				ok = false;
			}
		}
		if (form.getTicket().getStatus() == null
				|| form.getTicket().getStatus().length() == 0) {
			errors.add(new ErrorMessage("", "status is required"));
			ok = false;
		}
		if (form.getTicket().getDispatchDate() == null
				|| form.getTicket().getDispatchDate().length() == 0) {
			errors.add(new ErrorMessage("", "dispatch date is required"));
			ok = false;
		}
		if (form.getTicket().getDispatchDate() != null
				&& form.getTicket().getDispatchDate().length() != 10) {
			errors.add(new ErrorMessage("", "dispatch date format needs to be MM/DD/YYYY"));
			ok = false;
		}
		
		if (form.getTicket().getSpecialFlag() != null
				&& form.getTicket().getSpecialFlag().length() == 0) {
			errors.add(new ErrorMessage("", "special designation is required"));
			ok = false;
		}
		
		if (form.getTicket().getCallRequirements() == null
				|| form.getTicket().getCallRequirements().length() == 0) {
			errors.add(new ErrorMessage("", "call requirements is required"));
			ok = false;
		}

		if (form.getTicket().getClothing() != null
				&& form.getTicket().getClothing().length() > 0) {
			if (form.getTicket().getClothingQtyType() == null
					|| form.getTicket().getClothingQtyType().length() == 0) {
				errors.add(new ErrorMessage("",
						"clothing packing type is required"));
				ok = false;
			}
		}

		if (form.getTicket().getBedding() != null
					&& form.getTicket().getBedding().length() > 0) {
				if (form.getTicket().getBeddingQtyType() == null
						|| form.getTicket().getBeddingQtyType().length() == 0) {
					errors.add(new ErrorMessage("",
							"bedding packing type is required"));
					ok = false;
				}
			}
		
		if (form.getTicket().getBooks() != null
					&& form.getTicket().getBooks().length() > 0) {
				if (form.getTicket().getBooksQtyType() == null
						|| form.getTicket().getBooksQtyType().length() == 0) {
					errors.add(new ErrorMessage("",
							"books packing type is required"));
					ok = false;
				}
			}
		
		if (form.getTicket().getMiscHouseholdItems() != null
					&& form.getTicket().getMiscHouseholdItems().length() > 0) {
				if (form.getTicket().getMiscHouseholdItemQtySize() == null
						|| form.getTicket().getMiscHouseholdItemQtySize()
								.length() == 0) {
					errors.add(new ErrorMessage("",
							"misc. household packing type is required"));
					ok = false;
				}
			}
		
		if (form.getTicket().getMattress() != null
					&& form.getTicket().getMattress().length() > 0) {
				if (form.getTicket().getMattressQtySize() == null
						|| form.getTicket().getMattressQtySize().length() == 0) {
					errors.add(new ErrorMessage("", "mattress size is required"));
					ok = false;
				}
			}
		
		if (form.getTicket().getWallUnit() != null
					&& form.getTicket().getWallUnit().length() > 0) {
				if (form.getTicket().getWallUnitQtySize() == null
						|| form.getTicket().getWallUnitQtySize().length() == 0) {
					errors.add(new ErrorMessage("",
							"wall unit pieces is required"));
					ok = false;
				}
			}
		
		if (form.getTicket().getTables() != null
					&& form.getTicket().getTables().length() > 0) {
				if (form.getTicket().getTableType() == null
						|| form.getTicket().getTableType().length() == 0) {
					errors.add(new ErrorMessage("", "table type is required"));
					ok = false;
				}
			}
		
		if (form.getTicket().getChair() != null
					&& form.getTicket().getChair().length() > 0) {
				if (form.getTicket().getChairType() == null
						|| form.getTicket().getChairType().length() == 0) {
					errors.add(new ErrorMessage("", "chair type is required"));
					ok = false;
				}
			}

		if (form.getTicket().getTelevision() != null
					&& form.getTicket().getTelevision().length() > 0) {
				if (form.getTicket().getTelevisionSize() == null
						|| form.getTicket().getTelevisionSize().length() == 0) {
					errors.add(new ErrorMessage("",
							"television size is required"));
					ok = false;
				}
			}
			
			
		if ( (form.getTicket().getZipcode() != null&& form.getTicket().getZipcode().length() > 0) && 
					(form.getTicket().getDispatchDate() != null&& form.getTicket().getDispatchDate().length() > 0) ) {
			
				String msg = this.validateZipcodeDay(form.getTicket().getZipcode(), form.getTicket().getDispatchDate(),user.getFarmBase(), form.getTicket().getSpecialFlag());
				if (msg!=null) {
					errors.add(new ErrorMessage("",msg));
					ok=false;
				}
		}		
		
		
		if ( (form.getTicket().getSpecialFlag() == null || form.getTicket().getSpecialFlag().length() == 0)) 
			form.getTicket().setSpecialFlag("No");
		
		/*
		 * Check if ticket limit is not met ONLY on new tickets not updating
		 * 
		 */
		if (form.getTicket().getDonationId()==null) {
			DailyLimitDao limitDao = new DailyLimitDao();
			DonationTicketDao donationDao = new DonationTicketDao();
			GregorianCalendar calendar = new GregorianCalendar();
			
			Date now = calendar.getTime(); 
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			DateFormat df2 = new SimpleDateFormat("EEEE");
			DailyLimit dailyLimit = limitDao.search(form.getTicket().getDispatchDate(),user.getFarmBase());
			
			int limit=dailyLimit.getDailyLimit();
			int special=dailyLimit.getSpecialLimit();
			
			//default regular limit to 65 if limit hasn't been set
			if (limit==0)
				limit=65;
			
			//default regular limit to 4 if limit hasn't been set
			if (special==0)
				special=4;
			
			if ("NO".equals(form.getTicket().getSpecialFlag())) {
				List list = donationDao.search(null, null, null, form.getTicket().getDispatchDate(), null, null, "NO", user.getFarmBase());
				if (list.size()>=limit) {
					String msg = "Ticket limit for "+form.getTicket().getDispatchDate()+" has been reached at "+user.getFarmBase()+".  Please select another date";
					if (msg!=null) {
						errors.add(new ErrorMessage("",msg));
						ok=false;
					}
				}
			}
			
			if ("YES".equals(form.getTicket().getSpecialFlag())) {
				List specials = donationDao.search(null, null, null, form.getTicket().getDispatchDate(), null, null, "YES", user.getFarmBase());
				if (specials.size()>=special) {
					String msg = "Ticket limit for specials on "+form.getTicket().getDispatchDate()+" has been reached at "+user.getFarmBase()+".  Please select another date";
					if (msg!=null) {
						errors.add(new ErrorMessage("",msg));
						ok=false;
					}
				}
			}
		}

		form.setErrors(errors);
		return ok;
	}
	
	private String validateZipcodeDay(String zipcode, String date, String farm, String specialFlag ) {
		String msg=null;
		
		if ("YES".equals(specialFlag)&&"Boynton Beach".equals(farm))
			return null;
		
		String[] day = { "Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday" };
		
		// Zipcode A  T,TH,Sat
		String[] zipcodesA= { 
				"33401","33403","33404","33405","33406","33407","33408","33408","33409","33410","33411",
				"33412","33413","33414","33415","33417","33418","33458","33460","33461","33462","33463",
				"33467","33449","33469","33470","33477","33478","33480","33426","33435","33436","33437",
				"33472","33473"	};
		
		// Zipcode B M, W, F
		String[] zipcodesB= { 
				"33428","33431","33432","33433","33434","33444","33445","33446","33483","33484","33486",
				"33487","33496","33498","33426","33435","33436","33437","33472","33473" };
		
		String[] zipcodesC= { "33311","33313" };
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(new java.util.Date(date));
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		
		boolean ok=true;
		if ("Boynton Beach".equals(farm)) {
		
			ok=false;
			
			// T TH Sa  Boynton Beach
			if (dayOfWeek==3||dayOfWeek==5||dayOfWeek==7)
				for (int a=0;a<zipcodesA.length;a++)
					if (zipcode.equals(zipcodesA[a]))
						ok=true;				
			// M W F   - Boynton Beach
			if (dayOfWeek==2||dayOfWeek==4||dayOfWeek==6)
				for (int b=0;b<zipcodesB.length;b++)
					if (zipcode.equals(zipcodesB[b]))
						ok=true;	
		}
		
		if ("Fort Lauderdale".equals(farm)) {
			ok=true;
			// W F   Fort Lauderdale only picks up those 2 zipcodes
			if (dayOfWeek!=4&&dayOfWeek!=6) {
			for (int c=0;c<zipcodesC.length;c++)
				if (zipcode.equals(zipcodesC[c]))
					ok=false;
			}
		} 
		
		if (!ok)
			msg="Pickups in zipcode "+zipcode+" are not serviced on "+day[dayOfWeek-1];
		
		return msg;
	}

	private void resetForm(DonationTicketForm form) {
		form.setAction(null);
		form.setCallLog(new CallLog());
		form.setTicket(new DonationTicket());
		form.setErrors(new ArrayList<ErrorMessage>());
		form.setMessage(null);
		form.setLastname("lastname");
		form.setFirstname("firstname");
		form.setConfirmation("conf#");
		form.setDispatchDate("pickup date");
		form.setSpecialFlag("special");
		form.setStatus("status");
		form.setZipcode("zipcode");
	}

	private void clearMessages(HttpServletRequest request) {

		request.getSession().setAttribute(
				"ERROR_" + request.getSession().getId(), null);
		request.getSession().setAttribute( 
				"MESSAGE_" + request.getSession().getId(), null);

	}
	
	public void loadLimits(HttpServletRequest request) {
		
		SystemUser user = (SystemUser) request.getSession().getAttribute(
				"USER_" + request.getSession().getId());
	
		
		GregorianCalendar calendar = new GregorianCalendar();
		
		DailyLimitDao limitDao = new DailyLimitDao();
		DonationTicketDao donationDao = new DonationTicketDao();
		Date now = calendar.getTime();
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		DateFormat df2 = new SimpleDateFormat("EEEE");
		DailyLimit dailyLimit = limitDao.search(df.format(now), user.getFarmBase());
		int limit=dailyLimit.getDailyLimit();
		
		List list1 = donationDao.search(null, null, null, df.format(now), null, "Reschedule", null, user.getFarmBase());
		List list2 = donationDao.search(null, null, null,  df.format(now), null, "Pending", null, user.getFarmBase());
		int count = list1.size()+list2.size();
		
		request.getSession().setAttribute("LIMIT1", limit+"");
		request.getSession().setAttribute("COUNT1", count+"");
		request.getSession().setAttribute("DATE1", df2.format(now).substring(0,3)+" "+df.format(now));
					
		//Advance the calendar one day: 
		for (int day=1;day<7;day++) {
			Date tomorrow = calendar.getTime();
			tomorrow.setTime(now.getTime() + (24*day)*60*60*1000);
			String formattedDate = df.format(tomorrow);
			String sDay = df2.format(tomorrow);
			
			DailyLimit dLimit = limitDao.search(formattedDate, user.getFarmBase());
			limit = dLimit.getDailyLimit();
			list1 = donationDao.search(null, null, null, formattedDate, null, "Reschedule", null, user.getFarmBase());
			list2 = donationDao.search(null, null, null,  formattedDate, null, "Pending", null, user.getFarmBase());
			count = list1.size()+list2.size();

			request.getSession().setAttribute("LIMIT"+(day+1), limit+"");
			request.getSession().setAttribute("COUNT"+(day+1), count+"");
			request.getSession().setAttribute("DATE"+(day+1), sDay.substring(0,3)+" "+formattedDate);
		}
					
	}
	
	private void sendEmail (DonationTicket ticket) {
		
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		//properties.put("mail.smtp.host", "SMTP1.NTWEBS.NET");//SMTP1.NTWEBS.NET
		//properties.put("mail.smtp.port", "25");//25
		//properties.put("mail.smtp.ssl.trust", "SMTP1.NTWEBS.NET");
		properties.put("mail.smtp.host", "smtp.gmail.com");//SMTP1.NTWEBS.NET
		properties.put("mail.smtp.port", "587");//25

		Session mailSession = Session.getDefaultInstance(properties,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						//return new PasswordAuthentication("WebMaster@FaithFarm.org","We9362Ma");// WebMaster@FaithFarm.org , We9362Ma
						return new PasswordAuthentication("faithfarm.intake@gmail.com","It0525Ff");// WebMaster@FaithFarm.org , We9362Ma
					}
				});
		try{
	         // Create a default MimeMessage object.
	         MimeMessage message = new MimeMessage(mailSession);
	         //message.setFrom(new InternetAddress("faithfarm.intake@gmail.com","Faith Farm Donations"));
	         
	         message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(ticket.getEmailAddress(),ticket.getEmailAddress()));
	         message.setSubject("Faith Farm Donation Pickup Confirmation#"+ticket.getDonationId());
	         String phone="";
	         if ("Fort Lauderdale".equals(ticket.getFarmBase())) {
	        	 message.setFrom(new InternetAddress("d2@faithfarm.org","Donation Fort Lauderdale"));
	        	 phone="(954) 763-7787";
	         }
	         if ("Boynton Beach".equals(ticket.getFarmBase())) {
	        	 message.setFrom(new InternetAddress("d3@faithfarm.org","Donation Boynton Beach"));
	        	 phone="(561) 737-2222";
	         }
	         if ("Okeechobee".equals(ticket.getFarmBase())) {
	        	 message.setFrom(new InternetAddress("d4@faithfarm.org","Donation Okeechobee"));
	        	 phone="(863) 763-4224";
	         }
	         
	         StringBuffer email = new StringBuffer("");
	         email.append("Dear "+ WordUtils.capitalize(ticket.getFirstname().toLowerCase())+" "+ WordUtils.capitalize(ticket.getLastname().toLowerCase())+",");
	         email.append("\rThank you for donating to Faith Farm of "+ticket.getFarmBase()+".\r");
	         email.append("A donation pickup has been scheduled for "+ticket.getDispatchDate()+" at:\r");
	         email.append("     "+WordUtils.capitalize(ticket.getAddressLine1().toLowerCase())+"\r");
	         email.append("     "+WordUtils.capitalize(ticket.getCity().toLowerCase())+", "+ticket.getState()+" "+ticket.getZipcode()+"\n\r");
	         email.append("Should you need to make changes or cancel your donation please call "+phone+".\n\r");
	         email.append("The following items have been scheduled for pickup:\n\r");
	         email.append("ITEM              QTY\r");
	         email.append("------------------------------------\r");
	        if (ticket.getAc()!=null && ticket.getAc().length()>0 ) email.append("Air Conditioner:  "+ticket.getAc()+"\r");
	        if (ticket.getBedding()!=null && ticket.getBedding().length()>0 ) email.append("Bedding:          "+ticket.getBedding()+" "+ticket.getBeddingQtyType()+"\r");
	        if (ticket.getBooks()!=null && ticket.getBooks().length()>0 ) email.append("Books:            "+ticket.getBooks()+" "+ticket.getBooksQtyType()+"\r");
	        if (ticket.getClothing()!=null && ticket.getClothing().length()>0 ) email.append("Clothing:         "+ticket.getClothing()+" "+ticket.getClothingQtyType()+"\r");
	        if (ticket.getComputer()!=null && ticket.getComputer().length()>0 ) email.append("Computer:         "+ticket.getComputer()+"\r");
	        if (ticket.getDesk()!=null && ticket.getDesk().length()>0 ) email.append("Desk:             "+ticket.getDesk()+"\r");
	        if (ticket.getChest()!=null && ticket.getChest().length()>0 ) email.append("Chest:            "+ticket.getChest()+"\r");
	        if (ticket.getArmoire()!=null && ticket.getArmoire().length()>0 ) email.append("Armoire:          "+ticket.getArmoire()+"\r");
	        if (ticket.getDresser()!=null && ticket.getDresser().length()>0 ) email.append("Dresser:          "+ticket.getDresser()+"\r");
	        if (ticket.getMirror()!=null && ticket.getMirror().length()>0 ) email.append("Mirror:           "+ticket.getMirror()+"\r");
	        if (ticket.getNightstand()!=null && ticket.getNightstand().length()>0 ) email.append("Nighstand:        "+ticket.getNightstand()+"\r");
	        if (ticket.getHeadboard()!=null && ticket.getHeadboard().length()>0 ) email.append("Headboard:        "+ticket.getHeadboard()+"\r");
	        if (ticket.getFootboard()!=null && ticket.getFootboard().length()>0 ) email.append("Footboard:        "+ticket.getFootboard()+"\r");
	        if (ticket.getRails()!=null && ticket.getRails().length()>0 ) email.append("Rails:            "+ticket.getRails()+"\r");
	        if (ticket.getLamp()!=null && ticket.getLamp().length()>0 ) email.append("Lamp:             "+ticket.getLamp()+"\r");
	        if (ticket.getLawnFurniture()!=null && ticket.getLawnFurniture().length()>0 ) email.append("Lawn Furniture:   "+ticket.getLawnFurniture()+"\r");
	        if (ticket.getMattress()!=null && ticket.getMattress().length()>0 ) email.append("Mattress:         "+ticket.getMattress()+" "+ticket.getMattressQtySize()+"\r");
	        if (ticket.getMiscHouseholdItems()!=null && ticket.getMiscHouseholdItems().length()>0 ) email.append("Misc. House Items:"+" "+ticket.getMiscHouseholdItems()+ticket.getMiscHouseholdItemQtySize()+"\r");
	        if (ticket.getRefridgerator()!=null && ticket.getRefridgerator().length()>0 ) email.append("Refrigerator:     "+ticket.getRefridgerator()+"\r");
	        if (ticket.getStove()!=null && ticket.getStove().length()>0 ) email.append("Stove:            "+ticket.getStove()+"\r");
	        if (ticket.getRecliner()!=null && ticket.getRecliner().length()>0 ) email.append("Recliner:         "+ticket.getRecliner()+"\r");
	        if (ticket.getSofa()!=null && ticket.getSofa().length()>0 ) email.append("Sofa:             "+ticket.getSofa()+"\r");
	        if (ticket.getLoveseat()!=null && ticket.getLoveseat().length()>0 ) email.append("Loveseat:         "+ticket.getLoveseat()+"\r");
	        if (ticket.getWallUnit()!=null && ticket.getWallUnit().length()>0 ) email.append("Wall Unit:        "+ticket.getWallUnit()+" "+ticket.getWallUnitQtySize()+"\r");
	        if (ticket.getTables()!=null && ticket.getTables().length()>0 ) email.append("Table:            "+ticket.getTables()+" "+ticket.getTableType()+"\r");
	        if (ticket.getChair()!=null && ticket.getChair().length()>0 ) email.append("Chair:            "+ticket.getChair()+ticket.getChairType()+"\r");
	        if (ticket.getTelevision()!=null && ticket.getTelevision().length()>0 ) email.append("Television:       "+ticket.getTelevision()+" "+ticket.getTelevisionSize()+"\r");
	        if (ticket.getElectronics()!=null && ticket.getElectronics().length()>0 ) email.append("Electronics:      "+ticket.getElectronics()+"\r");
	        if (ticket.getWasher()!=null && ticket.getWasher().length()>0 ) email.append("Washer:           "+ticket.getWasher()+"\r");
	    	if (ticket.getDryer()!=null && ticket.getDryer().length()>0 ) email.append("Dryer:            "+ticket.getDryer()+"\r");
	    	if (ticket.getExerciseEquipment()!=null && ticket.getExerciseEquipment().length()>0 ) email.append("Exercise Equip.   "+ticket.getExerciseEquipment()+"\r");
	        
	    	email.append("\n\rAgain, we thank you for your donation!");
	    	email.append("\n\n\n\rTHIS IS AN AUTOMATED MESSAGE. DO NOT REPLY.");
		        
	        message.setText(email.toString());
	         // Send message
		         try {
		     		String ipAddy=InetAddress.getLocalHost().getHostAddress();
		     		if ("50.63.180.165".equals(ipAddy))
		     				Transport.send(message);
		     			
		     		} catch (Exception e) {
		     			LOGGER.log(Level.SEVERE,"Error occurred in getting IP when trying to send email: "+e.getMessage());
		     		}
		         
		        }catch (Exception mex) {
		         mex.printStackTrace();
		         LOGGER.log(Level.SEVERE,"Error occurred sending email for application: "+mex.getMessage());
		      }
	}

}
