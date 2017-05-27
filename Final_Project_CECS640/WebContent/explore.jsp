<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="design.css"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Planet Explorer</title>
</head>
<body>
<% Object user = session.getAttribute("user");
	if(user == null)
	{
		response.sendRedirect("login.jsp");
	}
%>
<div class="header"><h1>Welcome to the Solar System Sandbox</h1></div>
<ul>
	<li><a href="explore.jsp">Explore!</a></li>
		<li><a href="delete.jsp">Destroy!</a></li>
		<li><a href="create.jsp">Create!</a></li>
		<li><a href="login.jsp">Login!</a></li>
		<li><a>Welcome ${user}</a></li>
</ul>
<% Object a = request.getAttribute("QueryRan"); 
String direct = "";
if(a == null)
	{
		direct = "/WEB-INF/tableJSP.jsp";
	}
else
{
	direct = "/WEB-INF/tableJSPNosub.jsp";
}
%>

<jsp:include page='<%=direct %>'/>
</body>
</html>