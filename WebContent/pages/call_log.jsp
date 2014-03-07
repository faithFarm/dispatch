<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>


<jsp:include page="includes/header.jsp" flush="true"/>

<% 
	String required = "<img src='../images/required.png'/>"; 
%>

<html:form method="POST" action="/Dispatch.do"> 
			<tr>
            	<td align="left" valign="center" border="0" bgcolor="#FFFFFF">
                        <h1>Call Information</h1>
                        
                        <% if (session.getAttribute("MESSAGE_"+session.getId())!=null) { %>
                        	<h5><img src="../images/success.png"/><%=session.getAttribute("MESSAGE_"+session.getId()) %></h5>
                        <% } %>
                        
                		<table width="400" cellpadding="0" cellspacing="0" border="0">
                        	<tr>
                            	<td width="25"></td>
                            	<td class="fieldHeading" >Type Of Call<%=required%></td> 
                                <td class="fieldHeading" >Source<%=required%></td>
                                <td></td>
                            </tr>
                            <tr>
                            	<td width="25"></td>
                            	<td >
                                  <html:select property="callLog.callType" styleClass="select" >
										<html:option value="">Select</html:option>
								  <html:optionsCollection name="dllCallType" value="value" label="label" />
								  </html:select>
								 </td> 
                                <td>
                                	<html:select property="callLog.source" styleClass="select" >
										<html:option value="">Select</html:option>
										<html:optionsCollection name="dllSource" value="value" label="label" />
									</html:select>
                                </td>
                                <td></td>
                            </tr>
                          
                            <tr>
                             <td colspan="3" align="right">
                             	</br>
                             		<input type="submit" name="action" value="Continue"  title="Continue" />
                             </td>
                             <td></td>
                            </tr>
                  		</table>
                  </td>
            </tr>
           </html:form>      
<jsp:include page="includes/footer.jsp" flush="true"/>
