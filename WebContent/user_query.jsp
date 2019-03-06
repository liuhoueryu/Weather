<%@page import="com.neuedu.entity.User"%>
<%@page import="java.util.List"%>
<%@page import="com.neuedu.util.StringUtil"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.neuedu.util.DBManager"%>
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
	if(request.getAttribute("list")==null){
		response.sendRedirect("UserQueryServlet");
		return;
	}
%>

<%
	//从request属性范围中取出用户列表
	List<User> list = (List<User>)request.getAttribute("list");

	//从request属性范围中取出用户名
	String username = (String)request.getAttribute("username");

%>


<form action="UserQueryServlet" method="post">
	用户名<input type="text" name="username" value="<%=username%>"/><br/>
	<input type="submit" value="查询"/>
</form>

<table border="1">

	<tr><td>用户头像</td><td>用户名</td><td>积分</td><td>性别</td><td>职业</td><td>注册时间</td></tr> 

<%
	for(User user : list){
%>
		<tr>
			 <td><img src="image/photo/<%=user.getPhoto()%>"/></td>
			 <td><%=StringUtil.convertKeyword(user.getUsername(), username) %></td>
			  <td><%=user.getScore() %></td>
			  <td><%=user.getGender() %></td>
			  <td><%=user.getJob() %></td>
			  <td><%=StringUtil.convertDatetime(user.getRegtime()) %></td>
		</tr>
<%
	}
	
%>

</table>
</body>
</html>