<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
   <%@ taglib prefix="s" uri="/struts-tags" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Video Tube File Upload Form</title>
<link href="<s:url value="/css/scubaform.css"/>" rel="stylesheet" type="text/css"/>

</head>
<body>
<div id="container">
	<fieldset>
		<legend>File Upload</legend>

		<s:form action="Upload" enctype="multipart/form-data" method="POST">
		
			<div class="reqfield">
			<label id="lblconuname" for="fname">Title </label>
			<s:textfield name="title" required="true" tooltip="Enter Title" />
			</div>

			<div class="reqfield">
			<label for="company">Category </label>
			<s:textfield name="category" required="true" tooltip="Enter Category" />
			</div>

			<div class="reqfield">
			<label id="lblcomment" for="comments">Description </label>
			<s:textarea name="description" label="Description" cols="35" rows="8" /> 
			<br />
			<s:reset/>
			</div>
			
			<br />
			<table >
				<tr>
					<th><label for="file" class="required">Your File </label></th>
					<td>
						<s:file name="file" />
					</td>
				</tr>
				
				<tr>
					<td><br />
					</td>
				</tr>

				<tr>
					<th><label for="file" class="required">Thumbnail </label></th>
					<td>
						<s:file name="attachment" />
					</td>
				</tr>
			</table>	
			<br />	
			<div id="butdiv" class="reqfield">
			<s:submit value="Upload"/>
			</div>
	</fieldset>
		</s:form>
	
</body>