<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%

	//String username = (String)pageContext.getAttribute("username");
	//String username = (String)request.getAttribute("username");
	//String username = (String)session.getAttribute("username");
	String username = (String)application.getAttribute("username");

%>

用户名=<%=username %>

<br/>

<a href="c.jsp">访问c.jsp</a>

</body>
</html>