<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>  


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<link href="<%=request.getContextPath() %>/styles/style.css" rel="stylesheet" type="text/css" />

<style>
#td {
}
.error {
	font: bold 11px Arial;
	color: #b71524;
	text-align:left; 
}

</style>

</head>
   
<body topmargin="0" leftmargin="0" bgcolor="#383838">
 
<table width="100%" cellpadding="0" cellspacing="0" border="0">
<tr>
<td width="100%" height="500" valign="center" align="center" border="0">
		
		<table width="486" height="229" cellpadding="0" cellspacing="0" border="0">
			<tr>
            	<td  width="486" align="center" valign="center" background="<%=request.getContextPath() %>/images/loginPanel.png" >
                <html:form method="POST" action="/Login.do"> 
                    <table align="right" border="0" >
                        <tr>
                        	<td height="90" colspan="2">
                        	</td>
                        </tr>
                        <logic:notEmpty name="loginForm" property="errorMessage">
                         <tr>
                            <td width="80"></td>
                            <td width="250" height="23" class="error">
		                       		<bean:write name="loginForm" property="errorMessage"/>
		                    </td>
		                </tr>
		                </logic:notEmpty>
                        <tr>
                            <td width="80"></td>
                            <td width="250" height="23">
                            	<html:text property="username" size="20" maxlength="20"  styleClass="login" onfocus="javascript:this.value=''"></html:text>
                            </td>
                        </tr>
                        <tr>
                            <td width="80"></td>
                            <td width="142" height="23">
                            	<html:password property="password" size="20" maxlength="20"  styleClass="login" onfocus="javascript:this.value=''"></html:password>
                            </td>
                        </tr>
                        
                        <tr>
                            <td width="80"></td>
                            <td width="142" height="23">
                           		<input type="submit" name="action" value="Login" class="imageButtonLogin" title="Login" />  
                           	</td>
                        </tr>
                     </table>
                    
                  </html:form>
                </td>
           </tr>
		</table>
        
</td>
</tr>
</table>
				
</br></br></br></br></br></br>                
<div id="footer" align="center">
    Faith Farm Ministries, 9538 U.S. 441, Boynton Beach, FL 33472. &copy;2013</br>
    (561) 737-2222 | (561) 735-0227 FAX</br></br>					
</div>
			
</body>
</html>
