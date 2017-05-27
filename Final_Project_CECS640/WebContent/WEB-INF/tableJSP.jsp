<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body>

	<form name="myform" method="post" action="Directions">
		<input type = "hidden" name="action" value = "query">
		<input type = "hidden" name = "type" value = "select">
		<input type = "hidden" name = "pk" value = "none">
		<input type = "submit" value="Lets go!">
	</form>
    ${sqlResult}

</body>
</html>