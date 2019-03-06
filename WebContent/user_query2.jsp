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
	//转码
	request.setCharacterEncoding("utf-8");

	//接收数据
	String username = request.getParameter("username");
	
	//默认值
	if(username==null){
		username = ""; 
	}

%>

<form action="user_query.jsp" method="post">
	用户名<input type="text" name="username" value="<%=username%>"/><br/>
	<input type="submit" value="查询"/>
</form>

<table border="1">
<%
	DBManager dbManager = DBManager.getInstance();
	String sql = "select * from user where username like ?";
	ResultSet rs = dbManager.execQuery(sql, "%" + username + "%");
	
	out.print("<tr><td>用户头像</td><td>用户名</td><td>积分</td><td>性别</td><td>职业</td><td>注册时间</td></tr>");
	
	while(rs.next()){  
		out.print("<tr>");
		out.print("<td><img src='image/photo/" + rs.getString(5) +"'/></td>");
		
		out.print("<td>" + StringUtil.convertKeyword(rs.getString(2), username) + "</td>");
		
		out.print("<td>" + rs.getInt(4) + "</td>");
		
		out.print("<td>" + rs.getString(6) + "</td>");
		
		out.print("<td>" + rs.getString(7) + "</td>");
		
		out.print("<td>" + StringUtil.convertDatetime(rs.getTimestamp(9)) + "</td>");
		
		out.print("</tr>");
	}
	
	dbManager.closeConnection();
%>

</table>
</body>
</html>