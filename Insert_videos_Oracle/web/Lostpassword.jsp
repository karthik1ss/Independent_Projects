<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lost Password request Form</title>
<link href="<s:url value="/css/scubaform.css"/>" rel="stylesheet" type="text/css"/>
<script language="JavaScript" type="text/javascript" src="struts/mainjs.js"></script>
</head>
<body>

<div id="container">
<h3>Lost Password Request</h3>
  <s:form action="forgot!find.action" method="POST" validate="true" >

    <fieldset>
    <legend>Password Request</legend>

	<s:actionerror labelposition="top/right" />
	<s:fielderror labelposition="top/right"/>

	<div class="reqfield">
      <label for="emailreq">Email:</label>
      <s:textfield name="email" value="%{login.email}" id="email" cssClass="textbox" tooltip="Enter Email" onfocus="this.style.backgroundColor='#FFF9EC'" onblur="this.style.backgroundColor='#ffffff'" />
	</div>
    <br />
    
	<div style="text-align: center; margin-top: 3px;">
    <s:submit value="Submit" cssClass="submit"/>
    </div>
    </fieldset>
    
    
  </s:form>
</div>

</body>
</html>

