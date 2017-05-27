<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="design.css"/>
<link rel="stylesheet" type="text/css" href="design.css"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Please Log In</title>
</head>
<body>
<div class="header"><h1>Welcome to the Solar System Sandbox</h1></div>
<%	Object error = request.getAttribute("Error");
	String error_message = "";
	if(error != null)
		error_message = error.toString();
%>
<ul>
	<li><a href="explore.jsp">Explore!</a></li>
		<li><a href="delete.jsp">Destroy!</a></li>
		<li><a href="create.jsp">Create!</a></li>
		<li><a href="login.jsp">Login!</a></li>
		<li><a>Welcome ${user}</a></li>
</ul>
<span class="error"><%=error_message %></span>
<form action="Directions" method="post">
<input type = "hidden" name = "action" value = "login">
<fieldset class="fieldset-auto-width">
<table>
	<tr>
		<td>Username <input type="text" name="username"></td>
	</tr>
	<tr>
		<td>Password <input type="password" name="password"></td>
	</tr>
	<tr>
		<td><input type="submit" value="Login"></td>
	</tr>
</table>
</fieldset>	
</form>
<span class = "extend">Create a new <a href="account.jsp">account</a></span>
</body>
</html>