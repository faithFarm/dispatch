<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>System Error</title>
<link href="styles/style.css" rel="stylesheet" type="text/css" />
</head>

<body topmargin="0" leftmargin="0" bgcolor="#1d477c">

<table width="100%" cellpadding="0" cellspacing="0" border="0">
<tr>
	<td align="left" colspan="2" height="90"></td>
</tr>
<tr>
	<td><img src="<%=request.getContextPath() %>/images/woman.jpg" width="1000" height="625"/></td>
    <td width="50" bgcolor="#598dcd">&nbsp;</td>
    <td width="40%" bgcolor="#598dcd" valign="top">
    </br></br></br>
    	<h2>System Error</h2>
    </br></br>
        <h3>An error has occurred in the application. <i><%=session.getAttribute("ERROR_"+session.getId())%></i></h3>
     </br></br></br></br></br>
        
    </td>
 </tr>
 
 </table>

</body>
</html>
