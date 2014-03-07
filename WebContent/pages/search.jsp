<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ page import="java.util.ArrayList" %>


<jsp:include page="includes/header.jsp" flush="true"/>

<script language="javascript" type="text/javascript">
function ucase(obj) {
  obj.value=obj.value.toUpperCase();
}

function submit(action, key) {

	document.getElementById("action").value=action;
	document.getElementById("key").value=key;
	document.getElementById("form1").submit();
}

function isNumberKey(evt)
{
   var charCode = (evt.which) ? evt.which : event.keyCode
   if (charCode > 31 && (charCode < 48 || charCode > 57))
      return false;

   return true;
}
</script>

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

<html:form method="POST" action="/Dispatch.do" styleId="form1"> 
<tr>
 			<td bgcolor="#ffffff" align="center"><br /><br />
 			 <logic:notEmpty name="ticketForm" property="message">
            		    <table width="800" cellpadding="0" cellspacing="0" border="0">
                            <tr>
                            	<td width="18">
                            		<img src="<%=request.getContextPath()%>/images/message.png" />
                            	</td>
                            	<td class="instructions" align="left">
                            		<i><bean:write name="ticketForm" property="message" /></i>
                            	</td>
                            </tr>
                        </table>
                        </br>
                        </logic:notEmpty>
            <table width="800" cellpadding="0" cellspacing="0" border="0">
			 <tr>
            	<td align="center" bgcolor="#FFFFFF" >
            	            
                       <table width="800" cellpadding="0" cellspacing="0" border="0" class="grid">
                        	 <tr>
                            	<td colspan="7" height="25" bgcolor="#3b3f41">&nbsp;&nbsp;Donations&nbsp;<bean:write name="ticketForm" property="farmBase"/></td>
                            	<td bgcolor="#3b3f41" alight="right">
                            		<logic:notEmpty name="ticketForm" property="results">
                            			<a href="javascript:submit('Print All','');"><img src="<%=request.getContextPath() %>/images/printer.png" height="20" width="20" title="Print Dispatches" alt="Print Dispatches"/></a>
                            		</logic:notEmpty>
                            	</td>
                            </tr>   
                       
                            
                            
                            <tr>
                            	<td height="23" valign="center" background="images/searchGroupBk.png" class="searchMenuHeader">
                                		<html:text property="lastname" size="25" maxlength="20"  styleClass="textboxSearch" onfocus="javascript:this.value='';" onkeyup="ucase(this)"/>
                                        <html:text property="firstname" size="20" maxlength="20"  styleClass="textboxSearch" onfocus="javascript:this.value='';" onkeyup="ucase(this)"/>
                                        <html:text property="zipcode" size="10" maxlength="10"  styleClass="textboxSearch" onfocus="javascript:this.value='';" onkeypress="return isNumberKey(event)" />
                                        <html:text property="confirmation" size="15" maxlength="15"  styleClass="textboxSearch" onfocus="javascript:this.value='';" onkeypress="return isNumberKey(event)" /> 
                                        <html:text property="dispatchDate" size="30" maxlength="10"  styleClass="tcal" onfocus="javascript:this.value='';" onkeyup="ucase(this)"/>
                                    
                                   <html:select property="status" styleClass="ddlSearch" >
										<html:option value="">status</html:option>
										<html:optionsCollection name="dllStatus" value="value" label="label" />
								   </html:select>
									
                                   <html:select property="specialFlag" styleClass="ddlSearch" >
										<html:option value="">special</html:option>
										<html:optionsCollection name="dllYesNo" value="value" label="label" />
									</html:select>
                                </td>
                                
                                <td colspan="7" align="left" valign="center" background="images/searchGroupBk.png" class="searchMenuHeader">
                                	<html:submit property="action" value="SearchTickets" styleClass="imageButtonSearch" title="Search Tickets" />
                                </td>
                            </tr>
                  			<tr>
                            	<td colspan="9">
                                    <table width="800" cellpadding="0" cellspacing="0">
                                        <tr>
                                            <td width="5" height="18" background="<%=request.getContextPath() %>/images/searchHeaderBk.png" class="searchFieldHeader"></td>
                                            <td width="3" height="18" background="<%=request.getContextPath() %>/images/searchHeaderSpacer.png"></td>
                                            <td width="90" height="18" background="<%=request.getContextPath() %>/images/searchHeaderBk.png" class="searchFieldHeader">CONFIRMATION</td>
                                            <td width="3" height="18" background="<%=request.getContextPath() %>/images/searchHeaderSpacer.png"></td>
                                            <td width="150" height="18" background="<%=request.getContextPath() %>/images/searchHeaderBk.png" class="searchFieldHeader">DONOR</td>
                                            <td width="3" height="18" background="<%=request.getContextPath() %>/images/searchHeaderSpacer.png"></td>
                                            <td width="120" height="18" background="<%=request.getContextPath() %>/images/searchHeaderBk.png" class="searchFieldHeader">PICKUP DATE</td>
                                            <td width="3" height="18" background="<%=request.getContextPath() %>/images/searchHeaderSpacer.png"></td>
                                            <td width="100" height="18" background="<%=request.getContextPath() %>/images/searchHeaderBk.png" class="searchFieldHeader">ZIPCODE</td>
                                            <td width="3" height="18" background="<%=request.getContextPath() %>/images/searchHeaderSpacer.png"></td>
                                            <td width="70" height="18" background="<%=request.getContextPath() %>/images/searchHeaderBk.png" class="searchFieldHeader">SPECIAL</td>
                                            <td width="3" height="18" background="<%=request.getContextPath() %>/images/searchHeaderSpacer.png"></td>
                                            <td width="70" height="18" background="<%=request.getContextPath() %>/images/searchHeaderBk.png" class="searchFieldHeader">STATUS</td>
                                            <td width="3" height="18" background="<%=request.getContextPath() %>/images/searchHeaderSpacer.png"></td>
                                            <td height="18" background="<%=request.getContextPath() %>/images/searchHeaderBk.png" class="searchFieldHeader">AGENT</td>
                                        </tr>
                                        
                                         <% int i=1; %>
                                         <logic:notEmpty name="ticketForm" property="results">
				                           <logic:iterate id="list" name="ticketForm" property="results" >
		                                         <tr>
		                                         	<td colspan="2" class="searchFieldResult"><%=i++ %></td>
		                                            <td colspan="2" class="searchFieldResult">
		                                            	<a href="javascript:submit('Update','<bean:write name="list" property="donationId"/>');"><font color="#000000"><bean:write name="list" property="donationId"/></font></a>
		                                            	<logic:equal name="loginForm" property="systemUser.userRole" value="Administrator">
															&nbsp;&nbsp;
		                                            		<a href="javascript:submit('Delete','<bean:write name="list" property="donationId"/>');"><img src="<%=request.getContextPath() %>/images/DeleteRed.png"/></a>
		                                            	</logic:equal>
		                                            </td>
		                                            <td colspan="2" class="searchFieldResult"><bean:write name="list" property="lastname"/>,&nbsp;<bean:write name="list" property="firstname"/></td>
		                                            <td colspan="2" class="searchFieldResult"><bean:write name="list" property="dispatchDate"/></td>
		                                            <td colspan="2" class="searchFieldResult"><bean:write name="list" property="zipcode"/></td>
		                                            <td colspan="2" class="searchFieldResult"><bean:write name="list" property="specialFlag"/></td>
		                                            <td colspan="2" class="searchFieldResult"><bean:write name="list" property="status"/></td>
		                                            <td class="searchFieldResult"><bean:write name="list" property="createdBy"/></td>
		                                        </tr>
		                                     </logic:iterate>
		                                  </logic:notEmpty>
		                                        
		                                  <logic:empty name="ticketForm" property="results">
												<tr>
		                                            <td colspan="10" class="searchFieldResult">No Results</td>
		                                        </tr>
		                                   </logic:empty>
                                   	 </table>
                                 </td>
                             </tr>
                             
                        </table>
                  </td>
            </tr>
            </table>
            </td> 
            
            <html:hidden property="action" value="" styleId="action" />
            <html:hidden property="key" value="" styleId="key" />
            
           </html:form> 
</tr>     
<jsp:include page="includes/footer.jsp" flush="true"/>
