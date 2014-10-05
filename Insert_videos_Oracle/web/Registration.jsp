<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" lang="en-us">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registration Form</title>
<link href="<s:url value="/css/scubaform.css"/>" rel="stylesheet" type="text/css"/>
<script language="JavaScript" type="text/javascript" src="struts/registrationvalidate.js"></script>

</head>
<body>
<h3>Registration Form</h3>

<div id="container">

    <s:form action="logentry!savelogin.action" method="POST" validate="true" >
    
		<fieldset>
		<legend>Login information</legend>

		<s:actionerror labelposition="top/right" />
		<s:fielderror labelposition="top/right"/>
		
		<div class="reqfield">
		<label id="lbluname" for="username">Username:</label>
		<s:textfield name="login.username" value="%{login.username}" id="username" cssClass="textbox" required="true" tooltip="Enter Username" onfocus="UnameFocus()" onblur="this.style.backgroundColor='#ffffff'; this.style.border = '1px solid #acc6db'" onkeydown="this.style.backgroundColor='#ffffff'" /> 
		</div>
	
		<div class="reqfield">
		<label id="lblpass" for="password">Password:</label>
		<s:password name="password" id="password" cssClass="textbox" tooltip="Enter Password must be 6 to 10 characters long " onfocus="PassWordFocus()" onblur="this.style.backgroundColor='#ffffff'; this.style.border = '1px solid #acc6db'" onkeydown="this.style.backgroundColor='#ffffff'" /> 
		</div>
	
		<div class="reqfield">
		<label id="lblretype" for="repass">Re-Type Pass:</label>
		<s:password name="repass" id="repass" cssClass="textbox" onfocus="RetypePassFocus()" onblur="this.style.backgroundColor='#ffffff'; this.style.border = '1px solid #acc6db'" onKeyDown="this.style.backgroundColor='#ffffff'" />
		</div>

		<s:hidden name="login.password" value="%{login.password}"/>
		</fieldset>
    
		<fieldset>
		<legend>Contact information</legend>
		
		<div class="reqfield">
		<label id="ulemail" for="email">Email:</label>
		<s:textfield name="login.email" value="%{login.email}" id="email" cssClass="textbox" tooltip="Enter Email" onfocus="UemailFocus()" onblur="this.style.backgroundColor='#ffffff'; this.style.border = '1px solid #acc6db'" onkeydown="this.style.backgroundColor='#ffffff'" />
		</div>
		</fieldset>
	</s:form>


	<s:form action="regstr!savereg.action" method="POST" validate="true">
		<fieldset>
		<legend>Personal information</legend>

		<s:actionerror labelposition="top/right" />
		<s:fielderror labelposition="top/right"/>
		
		<div class="reqfield">
		<label id="uname" for="name">Name:</label>
		<s:textfield name="user.name" value="%{user.name}" id="uname" cssClass="textbox" tooltip="Enter name" onfocus="UnameFocus()" onblur="this.style.backgroundColor='#ffffff'; this.style.border = '1px solid #acc6db'" onkeydown="this.style.backgroundColor='#ffffff'" />
		</div>

		<s:hidden name="user.email" value="%{login.email}"/>

		<div class="reqfield">
        <label for="gender">Gender:</label>
        <s:checkbox name="user.sex" label="Male" fieldValue="M"/>
		<s:checkbox name="user.sex" label="Female" fieldValue="F"/>
		</div>
     				
		<div class="reqfield">
		<label id="ulage" for="age">Age:</label>
	    <s:textfield name="user.age" value="%{user.age}" id="age" cssClass="textbox" tooltip="Enter age" onfocus="FUageFocus()" onblur="this.style.backgroundColor='#ffffff'; this.style.border = '1px solid #acc6db'" onkeydown="this.style.backgroundColor='#ffffff'" />
		</div>

		</fieldset>
  
		<fieldset>   
		<legend>Address </legend>
		<div class="reqfield">
		<label id="staddress" for="address">Address:</label>
		<s:textfield name="address" id="address" cssClass="textbox" tooltip="Enter address" onfocus="UaddressFocus()" onblur="this.style.backgroundColor='#ffffff'; this.style.border = '1px solid #acc6db'" onkeydown="this.style.backgroundColor='#ffffff'" />
		</div>
		
		<div class="reqfield">
		<label id="staddress" for="address">City:</label>
		<s:textfield name="city" id="city" cssClass="textbox" tooltip="Enter city" onfocus="UaddressFocus()" onblur="this.style.backgroundColor='#ffffff'; this.style.border = '1px solid #acc6db'" onkeydown="this.style.backgroundColor='#ffffff'" />
		</div>

		<div class="optfield">
		<label for="state">State:</label>
		<s:textfield name="state" id="state" cssClass="textbox" onfocus="UaddressFocus()" onblur="this.style.backgroundColor='#ffffff'; this.style.border = '1px solid #acc6db'" onkeydown="this.style.backgroundColor='#ffffff'" />
		</div>
		
		<div class="reqfield">
		<label id="usercity" for="city">Country:</label>
		<s:textfield  name="country" id="country" cssClass="textbox" tooltip="Enter country" onfocus="UCityFocus()" onblur="this.style.backgroundColor='#ffffff'; this.style.border = '1px solid #acc6db'" onkeydown="this.style.backgroundColor='#ffffff'" />
		</div>
     
		<div class="reqfield">
		<label id="userzip" for="zipcode">Zip code:</label>
		<s:textfield name="zipcode" id="zipcode" cssClass="textbox" tooltip="Enter zipcode" onfocus="UzipFocus()" onblur="this.style.backgroundColor='#ffffff'; this.style.border = '1px solid #acc6db'" onkeydown="this.style.backgroundColor='#ffffff'" 
		onkeyup="combAddress()"/> 
		</fieldset>

		<s:hidden name="user.address" value="address"/>
   
	</s:form>
    		   
     
   
		<div id="butdiv" cssClass="reqfield">
		<s:submit value"Submit" class="submit" />
		</div>

		<div id="butdiv" cssClass="reqfield">
		<s:submit value"Cancel" class="submit" name="redirect:/Registration.jsp"/>
		</div>
       
    
    </s:form>

</div>
<!--End Form Container-->
</body>
</html>