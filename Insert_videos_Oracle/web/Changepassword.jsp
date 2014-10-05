<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Change Password Form</title>
<link href="<s:url value="/css/scubaform.css"/>" rel="stylesheet" type="text/css"/>
<script language="JavaScript" type="text/javascript" src="struts/mainjs.js"></script>
</head>
<body>

<div id="container">
<h3>Change Password Request</h3>
  <s:form action="change!savelogin.action" method="POST" validate="true" >

    <fieldset>
    <legend>Change Password </legend>

	<s:actionerror labelposition="top/right" />
	<s:fielderror labelposition="top/right"/>

	<div class="reqfield">
      <label for="emailreq">Old Password:</label>
      <s:password name="oldpassword" value="%{login.password}" id="email" cssClass="textbox" tooltip="Enter Old password" onfocus="this.style.backgroundColor='#FFF9EC'" onblur="this.style.backgroundColor='#ffffff'" />
	</div>
    <br />

	<div class="reqfield">
      <label for="emailreq">New Password:</label>
      <s:password name="newpassword" id="email" cssClass="textbox" tooltip="Enter New password" onfocus="this.style.backgroundColor='#FFF9EC'" onblur="this.style.backgroundColor='#ffffff'" />
	</div><br />

	<div class="reqfield">
      <label for="emailreq">Confirm Password:</label>
      <s:password name="confirmpassword" id="email" cssClass="textbox" onfocus="this.style.backgroundColor='#FFF9EC'" onblur="this.style.backgroundColor='#ffffff'" />
	</div>
    
	<div style="text-align: center; margin-top: 3px;">
    <s:submit value="Submit" cssClass="submit"/>
    </div>
    </fieldset>
    
    
  </s:form>
</div>

</body>
</html>

