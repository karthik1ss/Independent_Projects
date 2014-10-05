<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="s" uri="/struts-tags" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Video Tube Login Form</title>
<link href="<s:url value="/css/scubaform.css"/>" rel="stylesheet" type="text/css"/>
<script language="JavaScript" type="text/javascript" src="struts/mainjs.js"></script>
</head>
<body>

<div id="container">
<h3>Login Form</h3>
    <s:form action="login!savelogin.action" method="POST" validate="true" >

		<fieldset>
		<legend>Login</legend>

		<s:actionerror labelposition="top/right" />
		<s:fielderror labelposition="top/right"/>

		<div class="reqfield">
		<label id="lbluname" for="username">Username:</label>
		<s:textfield name="username" value="%{login.username}" cssClass="textbox" id="username" required="true" tooltip="Enter Username" />
		</div>
    
		<div class="reqfield">
		<label id="lblpass" for="password">Password:</label>
		<s:password name="password" value="%{login.password}" cssClass="textbox" id="password" required="true" tooltip="Enter Password" />
	    </div>
    
		
		<br />
		<br />
		<a href="H:\project\Insert Videos\VideoTube\VideoTube\web\Lostpassword.jsp" title="Can't remember your password? Click this link to request your password">Forgot password</a>&nbsp;|&nbsp;<a href="H:\project\Insert Videos\VideoTube\VideoTube\web\Registration.jsp" title="Register to access all our scuba diving services and recieve product update through our newletter">Register Now</a>

		<br />
		<br />
		<div id="butdiv" cssClass="reqfield">
		<s:submit value="Login" cssClass="submit"/>
		</div>
		</fieldset>
		
    </s:form>
</div>


</body>
</html>