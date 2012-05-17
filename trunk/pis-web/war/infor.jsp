<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%String title =  request.getParameter("title");
    	title = title == ""? "Information" : title;
    	String message = request.getParameter("message");
    	String url = request.getParameter("url");
    	url = url == ""? "/index.html" : url;
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%= title%></title>
</head>
<body>
	<div><%= message %><a href="<%=url%>">Go back</a></div>
</body>
</html>