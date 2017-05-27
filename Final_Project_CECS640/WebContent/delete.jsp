<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="design.css"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete a Planet</title>
</head>
<body>
<% Object user = session.getAttribute("user");
	if(user == null)
	{
		response.sendRedirect("login.jsp");
	}
%>
<%	Object error = request.getAttribute("Error");
	String error_message = "";
	if(error != null)
		error_message = error.toString();
%>
<div class="header"><h1>Welcome to the Solar System Sandbox</h1></div>
<ul>
	<li><a href="explore.jsp">Explore!</a></li>
		<li><a href="delete.jsp">Destroy!</a></li>
		<li><a href="create.jsp">Create!</a></li>
		<li><a href="login.jsp">Login!</a></li>
		<li><a>Welcome ${user}</a></li>
</ul>
<h3>Please enter name of planet you wish to destroy</h3>
<span class="error"><%=error_message %></span>
<form action="Directions" method="post">
<input type = "hidden" name = "action" value = "destroy">
<input type = "hidden" name = "type" value = "destroy">
<fieldset class="fieldset-auto-width">
<table>
	<tr>
		<td>Planet name <input type="text" name="planet"></td>
	</tr>
	<tr>
		<td><input type="submit" value="Destroy"></td>
	</tr>
</table>	
</fieldset>
</form>
</body>
</html>