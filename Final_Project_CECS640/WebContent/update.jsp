<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="design.css"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update a Planet!</title>
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
<h3>Please the name of the planet <br>Click the value you wish to change <br>Enter in the new value</h3>
<span class="error"><%=error_message %></span>

<form action="Directions" method="post">
<input type = "hidden" name = "action" value = "update">
<input type = "hidden" name = "type" value = "update">
<fieldset class="fieldset-auto-width">
<table>
	<tr>
		<td>Planet Name <input type="text" name="pname"></td>
	</tr>
	<tr>
		<td>  <input type="radio" name="update" value="size">Size</td>
	</tr>
		<tr>
		<td>  <input type="radio" name="update" value="mass">Mass</td>
	</tr>
		<tr>
		<td>  <input type="radio" name="update" value="temp">Temperature</td>
	</tr>
		<tr>
		<td>  <input type="radio" name="update" value="dist">Temperature</td>
	</tr>
		<tr>
		<td>  <input type="radio" name="update" value="orbit">Orbit time</td>
	</tr>
		<tr>
		<td>  <input type="radio" name="update" value="dl">Day Length</td>
		</tr>
			<tr>
		<td>Value for selected column <input type="text" name="value"></td>
	</tr>
	<tr>
		<td><input type="submit" value="Update"></td>
	</tr>
</table>	
</fieldset>
</form>
</body>
</html>