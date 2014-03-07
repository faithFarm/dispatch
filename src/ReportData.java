import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.dispatch.faithfarm.domain.CallLog;
import org.dispatch.faithfarm.hibernate.dao.CallLogDao;
import org.dispatch.faithfarm.utils.Validator;


public class ReportData {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		CallLogDao dao = new CallLogDao();
		
		List list = dao.search("Fort Lauderdale");

		int index=0;
		int totalCalls=0;
		String prevDate="";
		
		for (Iterator iterator =
				 list.iterator(); iterator.hasNext();){
			 CallLog call = (CallLog)iterator.next();
			 
			String date1 =convertDate(call.getCallDate());
			if (!date1.equals(prevDate)) {
				//System.out.println (prevDate+"="+index);
				prevDate=date1;
				index=0;
			} else
			 index++;
			
			totalCalls++;
		}
		
		//System.out.println("Total Calls: "+totalCalls);
		
	}
	
	public static String convertDate(String epoch)
	{		
		String fDate="";
		try {
		Date ddate = new java.util.Date(new Long(epoch));
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		fDate=df.format(ddate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return fDate;
	}
	
	

}
