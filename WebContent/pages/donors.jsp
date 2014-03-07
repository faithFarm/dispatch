<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ page import="java.util.ArrayList" %>


<jsp:include page="includes/header.jsp" flush="true"/>


<style type="text/css">

.tcalInput {
  background: #c3ccd0 url('images/cal.gif') 100% 50% no-repeat;
  border:1px solid #7e93b0;
	border-radius:2px;
	height: 10px;
	background-color:#e0e9f6;
	font: italic 10px Arial;
	color: #7e93b0;
}

</style>

<script language="javascript" type="text/javascript">
function ucase(obj) {
  obj.value=obj.value.toUpperCase();
}

function submit(action, key) {

	document.getElementById("action").value=action;
	document.getElementById("key").value=key;
	document.getElementById("form1").submit();
}
</script>

<html:form method="POST" action="/Dispatch.do" styleId="form1">
<tr>
 			<td bgcolor="#ffffff" align="center"><br /><br />
            <table width="800" cellpadding="0" cellspacing="0" border="0">
			 <tr>
            	<td align="center" bgcolor="#FFFFFF" >
                       <table width="800" cellpadding="0" cellspacing="0" border="0" class="grid">
                        	 <tr>
                            	<td colspan="9" height="25" bgcolor="#3b3f41">&nbsp;&nbsp;Donors</td>
                            </tr>   
                           
                  			<tr>
                            	<td colspan="9">
                                    <table width="800" cellpadding="0" cellspacing="0">
                                        <tr>
                                            <td width="40" height="18" background="<%=request.getContextPath() %>/images/searchHeaderBk.png" class="searchFieldHeader"></td>
                                            <td width="3" height="18" background="<%=request.getContextPath() %>/images/searchHeaderSpacer.png"></td>
                                            <td width="120" height="18" background="<%=request.getContextPath() %>/images/searchHeaderBk.png" class="searchFieldHeader">NAME</td>
                                            <td width="3" height="18" background="<%=request.getContextPath() %>/images/searchHeaderSpacer.png"></td>
                                            <td width="250" height="18" background="<%=request.getContextPath() %>/images/searchHeaderBk.png" class="searchFieldHeader">ADDRESS</td>
                                            <td width="3" height="18" background="<%=request.getContextPath() %>/images/searchHeaderSpacer.png"></td>
                                            <td height="18" background="<%=request.getContextPath() %>/images/searchHeaderBk.png" class="searchFieldHeader">CITY, STATE ZIPCODE</td>
                                            <td width="70" height="18" background="<%=request.getContextPath() %>/images/searchHeaderBk.png"></td>
                                            <td height="18" background="<%=request.getContextPath() %>/images/searchHeaderBk.png" class="searchFieldHeader">LAST DONATION</td>
                                            <td width="70" height="18" background="<%=request.getContextPath() %>/images/searchHeaderBk.png"></td>
                                         </tr>
                                         <% int i=1; %>
                                         <logic:notEmpty name="ticketForm" property="results">
				                           <logic:iterate id="list" name="ticketForm" property="results" >

                                         	<tr>
                                          			<td colspan="2" class="searchFieldResult">
		                                            	<font color="#000000"><a href="javascript:submit('ExistingDonor','<bean:write name="list" property="donationId"/>');"><%=i++ %></a></font>
		                                            </td>
                                            		<td colspan="2" class="searchFieldResult"><bean:write name="list" property="firstname"/>&nbsp;<bean:write name="list" property="lastname"/></td>
		                                            <td colspan="2" class="searchFieldResult"><bean:write name="list" property="addressLine1"/></td>
		                                            <td colspan="2" class="searchFieldResult"><bean:write name="list" property="city"/>, <bean:write name="list" property="state"/>&nbsp;&nbsp;<bean:write name="list" property="zipcode"/></td>
		                                     		<td colspan="2" class="searchFieldResult"><bean:write name="list" property="dispatchDate"/></td>
		                                     </tr>
                                        </logic:iterate>
		                                </logic:notEmpty>
		                                        
		                                  <logic:empty name="ticketForm" property="results">
												<tr>
		                                            <td colspan="10" class="searchFieldResult">No matching donors</td>
		                                        </tr>
		                                   </logic:empty>
                                       
                                       
                                    </table>
                                    
                                 </td>
                             </tr>
                             
                        </table>
                  </td>
            </tr>
            </table>
            </br>
            <html:submit property="action" value="Return to Ticket" />
            </td> 
          
            <html:hidden property="action" value="" styleId="action" />
            <html:hidden property="key" value="" styleId="key" />
       
           </html:form> 
</tr>     
<jsp:include page="includes/footer.jsp" flush="true"/>
