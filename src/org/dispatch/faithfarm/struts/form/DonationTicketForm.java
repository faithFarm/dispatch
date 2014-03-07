package org.dispatch.faithfarm.struts.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;
import org.dispatch.faithfarm.domain.CallLog;
import org.dispatch.faithfarm.domain.DailyLimit;
import org.dispatch.faithfarm.domain.DonationTicket;
import org.dispatch.faithfarm.domain.ErrorMessage;

public class DonationTicketForm  extends ActionForm {
	
	private DonationTicket ticket=new DonationTicket();
	private CallLog callLog = new CallLog();
	private DailyLimit limit = new DailyLimit();
	private String message;
	private ArrayList<ErrorMessage>errors=new ArrayList<ErrorMessage>();
	private List<DonationTicket>results = new ArrayList<DonationTicket>();
	private List<DonationTicket>printList = new ArrayList<DonationTicket>();
	private String lastname="lastname";
	private String firstname="firstname";
	private String dispatchDate="pickup date";
	private String zipcode="zipcode";
	private String specialFlag;
	private String confirmation="conf#";
	private String status;
	private String farmBase;
	private String action=null;
	private String key=null;
	private String callDate="";
	
	public DailyLimit getLimit() {
		return limit;
	}

	public void setLimit(DailyLimit limit) {
		this.limit = limit;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getFarmBase() {
		return farmBase;
	}

	public void setFarmBase(String farmBase) {
		this.farmBase = farmBase;
	}

	public List<DonationTicket> getResults() {
		return results;
	}

	public void setResults(List<DonationTicket> results) {
		this.results = results;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getConfirmation() {
		return confirmation;
	}

	public void setConfirmation(String confirmation) {
		this.confirmation = confirmation;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getDispatchDate() {
		return dispatchDate;
	}

	public void setDispatchDate(String dispatchDate) {
		this.dispatchDate = dispatchDate;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getSpecialFlag() {
		return specialFlag;
	}

	public void setSpecialFlag(String specialFlag) {
		this.specialFlag = specialFlag;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public ArrayList<ErrorMessage> getErrors() {
		return errors;
	}

	public void setErrors(ArrayList<ErrorMessage> errors) {
		this.errors = errors;
	}

	public CallLog getCallLog() {
		return callLog;
	}

	public void setCallLog(CallLog callLog) {
		this.callLog = callLog;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public DonationTicket getTicket() {
		return ticket;
	}

	public void setTicket(DonationTicket ticket) {
		this.ticket = ticket;
	}

	public List<DonationTicket> getPrintList() {
		return printList;
	}

	public void setPrintList(List<DonationTicket> printList) {
		this.printList = printList;
	}

	public String getCallDate() {
		return callDate;
	}

	public void setCallDate(String callDate) {
		this.callDate = callDate;
	}
	

	
	
}
