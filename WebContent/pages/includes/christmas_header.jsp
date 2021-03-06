<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>  
<%@ page import="java.util.Date" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="refresh" content="14400;url=/dispatch/pages/security/login.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<link href="<%=request.getContextPath() %>/styles/christmas_style.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath() %>/styles/tcal.css" rel="stylesheet" type="text/css" />
<style>
    .dotted {border: 1px dotted #456879; border-style: none none dotted; color: #fff; background-color: #fff; }
</style>
<meta name = "viewport" content = "initial-scale = 1, user-scalable = no">
		<style>
			canvas{
			}
		</style>
<script src="https://maps.googleapis.com/maps/api/js?sensor=false&libraries=places"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/scripts/tcal.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/scripts/chart.js"></script>


<script language="javascript" type="text/javascript">
  
   function moveOnMax(field,nextFieldID){
	  if(field.value.length >= field.maxLength){
	    document.getElementById(nextFieldID).focus();
	  }
	}

function maskPhone(e,f){

  	
	var len = f.value.length;
	var key = whichKey(e);
	if(key>47 && key<58)
	{
		if( len==3 )f.value=f.value+'-'
		else if(len==7 )f.value=f.value+'-'
		else f.value=f.value;
	}
	else{
		f.value = f.value.replace(/[^0-9-]/,'')
		f.value = f.value.replace('--','-')
	}
}

function maskSsn(e,f){

  	
	var len = f.value.length;
	var key = whichKey(e);
	if(key>47 && key<58)
	{
		if( len==3 )f.value=f.value+'-'
		else if(len==6 )f.value=f.value+'-'
		else f.value=f.value;
	}
	else{
		f.value = f.value.replace(/[^0-9-]/,'')
		f.value = f.value.replace('--','-')
	}
}
 
function whichKey(e) {
   	
	var code;
	if (!e) var e = window.event;
	if (e.keyCode) code = e.keyCode;
	else if (e.which) code = e.which;
	return code
//	return String.fromCharCode(code);
}
 
</script>

</head>
  
<body topmargin="0" leftmargin="0" bgcolor="#383838">
 
<table width="100%" cellpadding="0" cellspacing="0">
<tr>
<td width="25%"></td>
<td width="950" align="center">
		<table width="100%" cellpadding="0" cellspacing="0" border="0">
			<tr>
				<td align="center" border="0" bgcolor="#FFFFFF">
					<div id="navigation">
						<table width="100%" cellpadding="0" cellspacing="0" border="0">
							<tr>
								<td align="left" valign="center" width="168"><img src="<%=request.getContextPath()%>/images/santa.jpg"/></td>
								<td align="right" valign="top">
									<font style="font: italic 11px Arial;color: #e4e4e4;text-align:right;">(<bean:write name="loginForm" property="systemUser.username" />@<bean:write name="loginForm" property="systemUser.farmBase" />&nbsp;&nbsp;/&nbsp;&nbsp;<%=new java.util.Date()%>)&nbsp;&nbsp;</font>&nbsp;&nbsp;
                    			</td>
							</tr>
						</table>
					</div>
                		<table width="100%" cellpadding="0" cellspacing="0" border="0">
                		<tr>
								<td bgcolor="#7e1313" width="100%">
                     			<logic:equal name="loginForm" property="systemUser.userRole" value="Administrator">
                     			&nbsp;&nbsp;&nbsp;&nbsp;
	                     		  		<a href="<%=request.getContextPath()%>/Dispatch.do?action=Home">Home</a>&nbsp;&nbsp;&nbsp;&nbsp;
						          		<a href="<%=request.getContextPath()%>/Dispatch.do?action=New">New Ticket</a>&nbsp;&nbsp;&nbsp;&nbsp;
										<a href="<%=request.getContextPath()%>/Dispatch.do?action=Search">View/Edit Ticket</a>&nbsp;&nbsp;&nbsp;&nbsp;
						                <a href="<%=request.getContextPath()%>/Dispatch.do?action=Level">Ticket Levels</a>&nbsp;&nbsp;&nbsp;&nbsp;
										<a href="<%=request.getContextPath()%>/Dispatch.do?action=Log">Call Log</a>&nbsp;&nbsp;&nbsp;&nbsp;
				                        <a href="<%=request.getContextPath()%>/Login.do?action=logout">Logout</a>&nbsp;&nbsp;&nbsp;&nbsp;
									</ul> 
								 </logic:equal>
                    			<logic:equal name="loginForm" property="systemUser.userRole" value="Agent">
                    			&nbsp;&nbsp;&nbsp;&nbsp;
	                    				<a href="<%=request.getContextPath()%>/Dispatch.do?action=Home">Home</a>&nbsp;&nbsp;&nbsp;&nbsp;
						                <a href="<%=request.getContextPath()%>/Dispatch.do?action=New">New Ticket</a>&nbsp;&nbsp;&nbsp;&nbsp;
										<a href="<%=request.getContextPath()%>/Dispatch.do?action=Search">View/Edit Ticket</a>&nbsp;&nbsp;&nbsp;&nbsp;
						                <a href="<%=request.getContextPath()%>/Dispatch.do?action=Log">Call Log</a>&nbsp;&nbsp;&nbsp;&nbsp;
						                <a href="<%=request.getContextPath()%>/Login.do?action=logout">Logout</a>
								</logic:equal>
						</td>
						</tr>
						</table>
				</td>
			</tr>
           </div>
           
<