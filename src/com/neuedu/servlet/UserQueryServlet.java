package com.neuedu.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neuedu.entity.User;
import com.neuedu.util.DBManager;

public class UserQueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	    //从配置文件中读取字符编码
		String charSet = this.getServletContext().getInitParameter("charSet");
		request.setCharacterEncoding(charSet); 
		
		request.setCharacterEncoding("utf-8");

		//接收数据
		String username = request.getParameter("username");
		
		//默认值
		if(username==null){
			username = ""; 
		}
		
		DBManager dbManager = DBManager.getInstance();
		String sql = "select * from user where username like ?";
		ResultSet rs = dbManager.execQuery(sql, "%" + username + "%");
		
		List<User> list = new ArrayList<>();
		
		try {
			while(rs.next()){  
				
				User user = new User();
				
				user.setUserid(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setScore(rs.getInt(4));
				user.setPhoto(rs.getString(5));
				user.setGender(rs.getString(6));
				user.setJob(rs.getString(7));
				user.setRegtime(rs.getTimestamp(9));
				
				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			dbManager.closeConnection();
		}
		
		//在request属性范围中保存用户列表
		request.setAttribute("list", list);
		
		//在request属性范围中保存用户名
		request.setAttribute("username", username);
		
		//转发到查询页面
		request.getRequestDispatcher("user_query.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
