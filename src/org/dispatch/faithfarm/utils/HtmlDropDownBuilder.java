package org.dispatch.faithfarm.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.dispatch.faithfarm.domain.DropDownItem;
import org.dispatch.faithfarm.domain.SystemUser;

public class HtmlDropDownBuilder {

	public void refresh(HttpSession session) {
	   
		SystemUser user = (SystemUser) session.getAttribute(
				"USER_" + session.getId());
		
		
		List<DropDownItem> rosterStatus = new ArrayList<DropDownItem>();
		  
		List<DropDownItem> status = new ArrayList<DropDownItem>();
	        status.add(new DropDownItem("Pending","Pending"));
	        status.add(new DropDownItem("Cancelled by Donor","Cancelled by Donor"));
	        if (user!=null&&user.getFarmBase().equals("Fort Lauderdale")) { 
	        	status.add(new DropDownItem("Assigned","Assigned"));
	        	status.add(new DropDownItem("Completed","Completed"));
		        status.add(new DropDownItem("In Route","In Route"));
		        status.add(new DropDownItem("No Response","No Response"));
		        status.add(new DropDownItem("Reschedule","Reschedule"));
		        status.add(new DropDownItem("10/5","10/5"));
		    }
	        
	        List<DropDownItem> callReq = new ArrayList<DropDownItem>();
	        callReq.add(new DropDownItem("tag(donation only)","tag(donation only)"));
	        callReq.add(new DropDownItem("half-hour call","half-hour call"));
	        if (user!=null&&user.getFarmBase().equals("Fort Lauderdale"))
	        	callReq.add(new DropDownItem("one hour call","one hour call"));
	        
	        List<DropDownItem> location = new ArrayList<DropDownItem>();
	        location.add(new DropDownItem("inside","inside"));
	        location.add(new DropDownItem("carport","carport"));
	        location.add(new DropDownItem("outside","outside"));
	        location.add(new DropDownItem("porch","porch"));
	        location.add(new DropDownItem("other","other"));
	        
	        List<DropDownItem> structureType = new ArrayList<DropDownItem>();
	        structureType.add(new DropDownItem("apartment","apartment"));
	        structureType.add(new DropDownItem("business","business"));
	        structureType.add(new DropDownItem("condominium","condominium"));
	        structureType.add(new DropDownItem("home","home"));
	        structureType.add(new DropDownItem("townhome","townhome"));
	        structureType.add(new DropDownItem("assisted living facility","assisted living facility"));
	        structureType.add(new DropDownItem("mobile home","mobile home"));
	        structureType.add(new DropDownItem("",""));
	        
	        List<DropDownItem> streetSuffix = new ArrayList<DropDownItem>();
	        streetSuffix.add(new DropDownItem("avenue","avenue"));
	        streetSuffix.add(new DropDownItem("boulevard","boulevard"));
	        streetSuffix.add(new DropDownItem("circle","circle"));
	        streetSuffix.add(new DropDownItem("court","court"));
	        streetSuffix.add(new DropDownItem("drive","drive"));
	        streetSuffix.add(new DropDownItem("highway","highway"));
	        streetSuffix.add(new DropDownItem("lane","lane"));
	        streetSuffix.add(new DropDownItem("manor","manor"));
	        streetSuffix.add(new DropDownItem("parkway","parkway"));
	        streetSuffix.add(new DropDownItem("place","place"));
	        streetSuffix.add(new DropDownItem("road","road"));
	        streetSuffix.add(new DropDownItem("street","street"));
	        streetSuffix.add(new DropDownItem("terrace","terrace"));
	        streetSuffix.add(new DropDownItem("way","way"));
	        streetSuffix.add(new DropDownItem("other","other"));
	        
	        List<DropDownItem> source = new ArrayList<DropDownItem>();
	        source.add(new DropDownItem("repeat donor/customer","repeat donor/customer"));
	        source.add(new DropDownItem("trucks","trucks"));
	        source.add(new DropDownItem("news/magazine","news/magazine"));
	        source.add(new DropDownItem("radio/tv ad","radio/tv ad"));
	        source.add(new DropDownItem("friend/relative","friend/relative"));
	        source.add(new DropDownItem("internet","internet"));
	        source.add(new DropDownItem("other","other"));
	        
	        List<DropDownItem> gate = new ArrayList<DropDownItem>();
	        gate.add(new DropDownItem("callbox code","callbox code"));
	        gate.add(new DropDownItem("ask security","ask security"));
	        gate.add(new DropDownItem("buzz owner by last name","buzz owner by last name"));
	        
	        List<DropDownItem> floor = new ArrayList<DropDownItem>();
	        for (int i = 1; i < 51; i++) {
	             floor.add(new DropDownItem(i+"",i+""));
	        }
	        
	        List<DropDownItem> qtyType = new ArrayList<DropDownItem>();
	        qtyType.add(new DropDownItem("boxes","boxes"));
	        qtyType.add(new DropDownItem("bags","bags"));

	        List<DropDownItem> pieces = new ArrayList<DropDownItem>();
	        pieces.add(new DropDownItem("1 piece","1 piece"));
	        pieces.add(new DropDownItem("2 pieces","2 pieces"));
	        pieces.add(new DropDownItem("3 pieces","3 pieces"));
	        pieces.add(new DropDownItem("4 pieces","4 pieces"));
	        pieces.add(new DropDownItem("5 pieces","5 pieces"));
	        pieces.add(new DropDownItem("6 pieces","6 pieces"));
	        pieces.add(new DropDownItem("7 pieces","7 pieces"));
	        pieces.add(new DropDownItem("8 pieces","8 pieces"));
	        pieces.add(new DropDownItem("9 pieces","9 pieces"));
	        pieces.add(new DropDownItem("10 pieces","10 pieces"));
	        
	        List<DropDownItem> mattress = new ArrayList<DropDownItem>();
	        mattress.add(new DropDownItem("twin","twin"));
	        mattress.add(new DropDownItem("full","full"));
	        mattress.add(new DropDownItem("queen","queen"));
	        mattress.add(new DropDownItem("king","king"));
	        mattress.add(new DropDownItem("Mixed Types","Mixed Types"));
	        
	        List<DropDownItem> tvsize = new ArrayList<DropDownItem>();
	        tvsize.add(new DropDownItem("9-inch","9-inch"));
	        tvsize.add(new DropDownItem("13-inch","13-inch"));
	        tvsize.add(new DropDownItem("21-inch","21-inch"));
	        tvsize.add(new DropDownItem("27-inch","27-inch"));
	        tvsize.add(new DropDownItem("31-inch","31-inch"));
	        tvsize.add(new DropDownItem("40-inch","40-inch"));
	        tvsize.add(new DropDownItem("48-inch","48-inch"));
	        tvsize.add(new DropDownItem("Other","Other"));
	        
	        
	        List<DropDownItem> tableType = new ArrayList<DropDownItem>();
	        tableType.add(new DropDownItem("Dining","Dining"));
	        tableType.add(new DropDownItem("End","End"));
	        tableType.add(new DropDownItem("Coffee","Coffee"));
	        tableType.add(new DropDownItem("Kitchen","Kitchen"));
	        tableType.add(new DropDownItem("Other","Other"));
	        tableType.add(new DropDownItem("Mixed Types","Mixed Types"));
	        
	        List<DropDownItem> chairType = new ArrayList<DropDownItem>();
	        chairType.add(new DropDownItem("Dinette","Dinette"));
	        chairType.add(new DropDownItem("Kitchen table","Kitchen table"));
	        chairType.add(new DropDownItem("Other","Other"));
	        chairType.add(new DropDownItem("Mixed Types","Mixed Types"));
	
	        List<DropDownItem> callType = new ArrayList<DropDownItem>();
	        callType.add(new DropDownItem("New Ticket","New Ticket"));
	        callType.add(new DropDownItem("Cancel Ticket","Cancel Ticket"));
	        callType.add(new DropDownItem("Donation Ticket","Donation Ticket"));
	        callType.add(new DropDownItem("Other Ticket","Other Ticket"));
	        callType.add(new DropDownItem("Phone Reject","Phone Reject"));
	        callType.add(new DropDownItem("Previously Written Ticket Inquiry","Previously Written Ticket Inquiry"));
	        callType.add(new DropDownItem("Reschedule","Reschedule"));

	        List<DropDownItem> yesNo = new ArrayList<DropDownItem>();
	        yesNo.add(new DropDownItem("Yes","Yes"));
	        yesNo.add(new DropDownItem("No","No"));

	     
	        session.setAttribute("dllLocation",location);
	        session.setAttribute("dllStatus",status);
	        session.setAttribute("dllLocation",location);
	        session.setAttribute("dllStructure",structureType);
	        session.setAttribute("dllStreetSuffix",streetSuffix);
	        session.setAttribute("dllCallReq",callReq);
	        session.setAttribute("dllGate",gate);
	        session.setAttribute("dllFloor",floor);
	        session.setAttribute("dllSource",source);
	        session.setAttribute("dllQtyType",qtyType);
	        session.setAttribute("dllMattress",mattress);
	        session.setAttribute("dllTvSize",tvsize);
	        session.setAttribute("dllTableType",tableType);
	        session.setAttribute("dllChairType",chairType);
	        session.setAttribute("dllPieces",pieces);	
	        session.setAttribute("dllCallType",callType);
	        session.setAttribute("dllYesNo",yesNo);
	   }
	
	

}
