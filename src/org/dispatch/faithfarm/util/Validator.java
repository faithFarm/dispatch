package org.dispatch.faithfarm.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

	   public static String validateRequired(String key, String value)
	   {
	     if ((value == null) || (value.length() == 0) || (value.trim().length() == 0)) {
	       return key + " is required.";
	     }
	     return "";
	   }
	 
	   public static String validateEmail(String email)
	   {
	     if (("NA".equals(email)) || ("na".equals(email))) {
	      return "";
	     }
	     if ((email == null) || (email.length() == 0) || (email.trim().length() == 0)) {
	       return "Email Address is required.";
	     }
	     Pattern p = Pattern.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
	     Matcher m = p.matcher(email);
	     boolean b = m.matches();
	      
	     if (!b) {
	      return "email address entered is not valid.";
	     }
	     return "";
	     }
	   
	   public static String validatePhone(String phone)
	   {
	    
	     if ((phone == null) || (phone.length() != 13)) {
	       return "Contact Phone is required";
	     }
	     Pattern p = Pattern.compile("^[\\(]{0,1}([0-9]){3}[\\)]{0,1}[ ]?([^0-1]){1}([0-9]){2}[ ]?[-]?[ ]?([0-9]){4}[ ]*((x){0,1}([0-9]){1,5}){0,1}$");
	     Matcher m = p.matcher(phone);
	     boolean b = m.matches();
	     
	     
	     if (!b) {
	      return "Contact Phone entered is not valid.";
	     }
	     return "";
	     }
	   
	   public static String cleanData(String value) {
		   if (value == null||value.equals("null")) {
		      return "";
		   } 
		   return value;
	   }
	   
	   public static String cleanForSql(String value) {
		   if (value != null) 
			   value=value.replace("'", "''");
		   return value;
	   }
	   
	   public static long getEpoch() {
		    long epoch;
		    Date date = new java.util.Date();
		    epoch = date.getTime();
	   return epoch;
	   }
}
