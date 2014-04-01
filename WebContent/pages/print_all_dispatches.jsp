<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Faith Farm Ministries - Donation Dispatch System 2.0</title>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/styles/dispatch.css"  />
</head>

<script language="javascript">

	
	 
	function printPage()
	{
	window.print();
	}
</script>

<STYLE TYPE="text/css">
     body { line-height: 1.00 }
     
     @media print
{
  .page-break  { display:block; page-break-before:always; }
}

     td {}
     .receiptTitle {
     	font-family: sans-serif;
     	font-size: 14px;
     	font-weight: normal;
     	line-height: 1.00;
     }
         .receiptFormItems {
     	font-family: sans-serif;
     	font-size: 14px;
     	font-weight: normal;
     	line-height: 1.00;
     }
         .receiptTitle {
     	font-family: sans-serif;
     	font-size: 14px;
     	font-weight: normal;
     	line-height: 1.00;
     }    .receiptFormAddress {
     	font-family: sans-serif;
     	font-size: 14px;
     	font-weight: normal;
     	line-height: 1.00;
     }     .receiptFormHeading {
     	font-family: sans-serif;
     	font-size: 14px;
     	font-weight: normal;
     	line-height: 1.00;
     } .receiptFooter {
     	font-family: sans-serif;
     	font-size: 12px;
     	font-weight: normal;
     	line-height: 1.00;
</STYLE>

<body onLoad="javascript:printPage();" topmargin="0" >
<!-- body -->
<html:form method="POST" action="/Dispatch.do" styleId="form1"> 
	
<logic:iterate id="list" name="ticketForm" property="printList" >

<table  width="700" cellpadding="0" cellspacing="0" >
		<tr>				
				<td valign="top" align="left" class="receiptTitle">
					<img src="<%=request.getContextPath()%>/images/logo.png">
					<br/> 
					<b> <bean:write name="list" property="farmBase" />&nbsp;Donation Pickup Ticket</b><br/>
					<br/>
				</td>
				<td valign="top" align="right" class="receiptTitle">
					<b>CUSTOMER ORDER</b><br/> 
					CONFIRMATION #: <bean:write name="list" property="donationId" /><br/>
					DISPATCH DATE: <bean:write name="list" property="dispatchDate" /><br/>
					CALL REQUIREMENTS:<b><bean:write name="list" property="callRequirements" /></b><br/>
					CALL DATE:<bean:write name="list" property="creationDate" /></br>
					CALL AGENT:<bean:write name="list" property="createdBy" /></br>		
					SPECIAL:<bean:write name="list" property="specialFlag" /></br>			
					<br/>
				</td>
		</tr>
		<logic:equal name="list" property="farmBase" value="Boynton Beach">
		<tr>
			<td class="dispatchFormHeading" align="left">9538 Hwy 441, Boynton Beach, Florida</td>
			<td class="dispatchFormHeading" align="right">(561) 737-2222&nbsp;/&nbsp;FAX (561) 735-0227</td>
		</tr>
		</logic:equal>
		<logic:equal name="list" property="farmBase" value="Fort Lauderdale">
		<tr>
			<td class="dispatchFormHeading" align="left">1980 NW 9th Ave, Fort Lauderdale, Florida</td>
			<td class="dispatchFormHeading" align="right">(954)763-7787&nbsp;/&nbsp;FAX (954)468-1416</td>
		</tr>
		</logic:equal>
  		<tr>
			<td colspan="2"><hr></td>
		</tr>
</table>
<table  width="700" cellpadding="0" cellspacing="0">
<tr> 
	<td align="left" valign="top">	
		<table width="350"  cellpadding="0" cellspacing="0">
	 <tr>
			<td class="receiptFormAddress" align="left"><br/><b>CUSTOMER:</b></br></td>
		</tr>
	 
		<tr>
			<td class="receiptFormAddress" align="left">
			
					<bean:write name="list" property="firstname" />&nbsp;<bean:write name="list" property="lastname" /><br/>
					<bean:write name="list" property="addressLine1" /><br/>
					<bean:write name="list" property="city" />,&nbsp;<bean:write name="list" property="state" />&nbsp;<bean:write name="list" property="zipcode" />
					<br/><br/>
					<b>Contact Phone:</b>&nbsp;<bean:write name="list" property="contactPhone" /><br/>
					<b>Alt. Phone:</b>&nbsp;<bean:write name="list" property="phoneOther" /><br/>
					<b>Email:</b>&nbsp;<bean:write name="list" property="emailAddress" /><br/>
					<br/><br/>
					
			</td>
		</tr>
	</table>
	
	</td>
	<td width="50%" class="receiptFormAddress" align="left" valign="top">
		<br/>
					<b>Major Intersection:</b><bean:write name="list" property="majorIntersection" /><br/>
					<b>Street Suffix:</b><bean:write name="list" property="streetSuffix" /><br/>
					<b>Subdivision:</b><bean:write name="list" property="subdivision" /><br/>
					<br/>
					<b>Property Type:</b>&nbsp;<bean:write name="list" property="structureType" /><br/>
					<b>Gated Community #:</b>&nbsp;<bean:write name="list" property="gatedFlag" /><br/>
					<b>Gate Instructions:</b>&nbsp;<bean:write name="list" property="gateInstructions" /><br/>
					<b>Gate Code:</b>&nbsp;<bean:write name="list" property="gateCode" /><br/>
					<b>Building #:</b>&nbsp;<bean:write name="list" property="building" /><br/>
					<b>Unit #:</b>&nbsp;<bean:write name="list" property="unit" /><br/>
					<b>Elevator Access:</b>&nbsp;<bean:write name="list" property="elevatorFlag" /><br/>
					<b>Floor #:</b>&nbsp;<bean:write name="list" property="floor" /><br/>
	</td>
	</tr>
	<tr>
		<td colspan="2" class="receiptFormItems">
		<br/>
			<table width="700" border="1" cellpadding="0" cellspacing="0">
			<tr>
				<td colspan="4" class="receiptFormHeading"><b>ITEM LISTING</b></td>
			</tr>
			  	<tr>
					<td class="receiptFormItems" width="50">Air Conditioner</td>
					<td class="receiptFormItems" width="100"><bean:write name="list" property="ac" /></td>
					<td class="receiptFormItems" width="50">Bedding</td>
					<td class="receiptFormItems" width="100"><bean:write name="list" property="bedding" />&nbsp;<bean:write name="list" property="beddingQtyType" /></td>
				</tr>
				
				<tr>
					<td class="receiptFormItems" width="50">Books</td>
					<td class="receiptFormItems" width="100"><bean:write name="list" property="books" />&nbsp;<bean:write name="list" property="booksQtyType" /></td>
					<td class="receiptFormItems" width="50">Clothing</td>
					<td class="receiptFormItems" width="100"><bean:write name="list" property="clothing" />&nbsp;<bean:write name="list" property="clothingQtyType" /></td>
				</tr>
				
				<tr>
					<td class="receiptFormItems" width="50">Computer</td>
					<td class="receiptFormItems" width="100"><bean:write name="list" property="computer" />&nbsp;</td>
					<td class="receiptFormItems" width="50">Ottoman</td>
					<td class="receiptFormItems" width="100"><bean:write name="list" property="ottoman" /></td>
				</tr>
				
				<tr>
					<td class="receiptFormItems" width="50">Desk</td>
					<td class="receiptFormItems" width="100"><bean:write name="list" property="desk" /></td>
					<td class="receiptFormItems" width="50">Chest</td>
					<td class="receiptFormItems" width="100"><bean:write name="list" property="chest" />&nbsp;</td>
				</tr>
				
				<tr>
					<td class="receiptFormItems" width="50">Armoire</td>
					<td class="receiptFormItems" width="100"><bean:write name="list" property="armoire" /></td>
					<td class="receiptFormItems" width="50">Dresser</td>
					<td class="receiptFormItems" width="100"><bean:write name="list" property="dresser" />&nbsp;</td>
				</tr>
				
				<tr>
					<td class="receiptFormItems" width="50">Mirror</td>
					<td class="receiptFormItems" width="100"><bean:write name="list" property="mirror" /></td>
					<td class="receiptFormItems" width="50">Nightstand</td>
					<td class="receiptFormItems" width="100"><bean:write name="list" property="nightstand" />&nbsp;</td>
				</tr>				
				
				<tr>
					<td class="receiptFormItems" width="50">Headboard</td>
					<td class="receiptFormItems" width="100"><bean:write name="list" property="headboard" /></td>
					<td class="receiptFormItems" width="50">Footboard</td>
					<td class="receiptFormItems" width="100"><bean:write name="list" property="footboard" />&nbsp;</td>
				</tr>				
				
				
				<tr>
					<td class="receiptFormItems" width="50">Rails</td>
					<td class="receiptFormItems" width="100"><bean:write name="list" property="rails" /></td>
					<td class="receiptFormItems" width="50">Lamp</td>
					<td class="receiptFormItems" width="100"><bean:write name="list" property="lamp" />&nbsp;</td>
				</tr>				
				
				
				<tr>
					<td class="receiptFormItems" width="50">Lawn Furniture</td>
					<td class="receiptFormItems" width="100"><bean:write name="list" property="lawnFurniture" /></td>
					<td class="receiptFormItems" width="50">Mattress/Box Spring</td>
					<td class="receiptFormItems" width="100"><bean:write name="list" property="mattress" />&nbsp;<bean:write name="list" property="mattressQtySize" /></td>
				</tr>				
				
				
				
				<tr>
					<td class="receiptFormItems" width="50">Misc. Household Items</td>
					<td class="receiptFormItems" width="100"><bean:write name="list" property="miscHouseholdItems" />&nbsp;<bean:write name="list" property="miscHouseholdItemQtySize" /></td>
					<td class="receiptFormItems" width="50">Refigerator</td>
					<td class="receiptFormItems" width="100"><bean:write name="list" property="refridgerator" />&nbsp;</td>
				</tr>			
				
				
				<tr>
					<td class="receiptFormItems" width="50">Stove</td>
					<td class="receiptFormItems" width="100"><bean:write name="list" property="stove" /></td>
					<td class="receiptFormItems" width="50">Recliner</td>
					<td class="receiptFormItems" width="100"><bean:write name="list" property="recliner" />&nbsp;</td>
				</tr>						
	
				<tr>
					<td class="receiptFormItems" width="50">Sofa</td>
					<td class="receiptFormItems" width="100"><bean:write name="list" property="sofa" /></td>
					<td class="receiptFormItems" width="50">Loveseat</td>
					<td class="receiptFormItems" width="100"><bean:write name="list" property="loveseat" />&nbsp;</td>
				</tr>						
	
					<tr>
					<td class="receiptFormItems" width="50">Wall Unit</td>
					<td class="receiptFormItems" width="100"><bean:write name="list" property="wallUnit" />&nbsp;<bean:write name="list" property="wallUnitQtySize" /></td>
					<td class="receiptFormItems" width="50">Table</td>
					<td class="receiptFormItems" width="100"><bean:write name="list" property="tables" />&nbsp;<bean:write name="list" property="tableType" /></td>
				</tr>						
	
					<tr>
					<td class="receiptFormItems" width="50">Chair</td>
					<td class="receiptFormItems" width="100"><bean:write name="list" property="chair" />&nbsp;<bean:write name="list" property="chairType" /></td>
					<td class="receiptFormItems" width="50">Television</td>
					<td class="receiptFormItems" width="100"><bean:write name="list" property="television" />&nbsp;<bean:write name="list" property="televisionSize" /></td>
				</tr>						
	
					<tr>
					<td class="receiptFormItems" width="50">Electronic Equipment</td>
					<td class="receiptFormItems" width="100"><bean:write name="list" property="electronics" /></td>
					<td class="receiptFormItems" width="50">Washer</td>
					<td class="receiptFormItems" width="100"><bean:write name="list" property="washer" />&nbsp;</td>
				</tr>						
	
				<tr>
					<td class="receiptFormItems" width="50">Dryer</td>
					<td class="receiptFormItems" width="100"><bean:write name="list" property="dryer" /></td>
					<td class="receiptFormItems" width="50">Exercise Equipment</td>
					<td class="receiptFormItems" width="100"><bean:write name="list" property="exerciseEquipment" />&nbsp;</td>
				</tr>						
							
				
			</table>
		</td>
	</tr>
	</table>
</td>
</tr>
</table>
<table  width="700" border="1" cellpadding="0" cellspacing="0">
<tr>
	<td height="1" bgcolor="#000000"></td>
</tr>
<tr>
	<td height="60" class="receiptFormItems" valign="top" align="left">
		<b>NOTES:</b><br/>
		<bean:write name="list" property="specialNotes" />
	</td>
</tr>
<tr>
</table>
<table  width="700" border="0" cellpadding="0" cellspacing="0">
<tr>
	<td class="receiptFooter">
    	Thank you for donating today! Your donation affords FFM a way to provide services in the community
		that bring the light of Christ into the lives of those less fortunate who have fallen victim to
		addiction and may otherwise not know the gift of Hope!
		<br/><br/></br>
	</td>	
</tr>
</table>

<div class="page-break"></div>

</logic:iterate>
</html:form>
</body>
</html>