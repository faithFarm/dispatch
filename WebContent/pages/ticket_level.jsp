<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
  
<jsp:include page="includes/header.jsp" flush="true"/>


<style type="text/css">
#input {}
.textbox1 { 
	border:1px solid #456879;
	border-radius:3px;
	height: 13px;
	background-color:#e4e4e4;
	font: 10px Arial;
	color: #444446;
}
.tcalActive {
	border:1px solid #456879;
	border-radius:3px;
	height: 13px;
	background-color:#e4e4e4;
	font: 10px Arial;
	color: #444446;
}
.tcal {
	border:1px solid #456879;
	border-radius:3px;
	height: 13px;
	background-color:#e4e4e4;
	font: 10px Arial;
	color: #444446;
}
</style>
<% 
	String required = "<img src='"+request.getContextPath()+"/images/required.png' title='required field'/>"; 
%>
<html:form method="POST" action="/Dispatch.do"> 
			<tr> 
            	<td align="left" valign="center" border="0" bgcolor="#FFFFFF">
                        <h1>Adjust Daily Ticket Limit</h1>
                		<table width="300" cellpadding="0" cellspacing="0" border="0">
                        	<tr>
                            	<td width="25"></td>
                            	<td class="fieldHeading" >Pickup Date<%=required%></td> 
                                <td class="fieldHeading" >Regular Limit<%=required%></td>
                                <td class="fieldHeading" >Special Limit<%=required%></td>
                                <td></td>
                            </tr>
                            <tr>
                            	<td width="25"></td>
                                <td>
                                	<html:text property="limit.dispatchDate" size="12" styleClass="tcal" />
                                </td>
                            	<td >
                                	<html:text property="limit.dailyLimit" size="3" maxlength="3" styleClass="textbox1"  />
                                </td> 
                                <td >
                                	<html:text property="limit.specialLimit" size="3" maxlength="3" styleClass="textbox1"  />
                                </td> 
                                
                                <td></td>
                            </tr>
                          
                            <tr>
                             <td></td>
                             <td colspan="3" align="left"></br><html:submit property="action" value="SaveLevel" styleClass="imageButtonSaveOnly" title="Save" /></td>
                             <td></td>
                            </tr>
                  		</table>
                  </td>
            </tr>
           </html:form>      
<jsp:include page="includes/footer.jsp" flush="true"/>
