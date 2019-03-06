package com.neuedu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.neuedu.entity.User;
import com.neuedu.util.DBManager;

public class UserRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//设置输出内容类型
		response.setContentType("text/html;charset=utf-8");	

		//获取out输出对象
		PrintWriter out = response.getWriter();		

		//获取session对象
	    HttpSession session = request.getSession();	

	    //获取application对象
		ServletContext application = this.getServletContext();

		//从配置文件中读取字符编码
		String charSet = this.getServletContext().getInitParameter("charSet");
		request.setCharacterEncoding(charSet); 
		
		//接收数据
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String photo = request.getParameter("photo");
		
		String gender = request.getParameter("gender");
		String job = request.getParameter("job");
		String[] interests = request.getParameterValues("interest");
		
		//字符串数组拼接字符串
		String interest = "";
		if(interests!=null){
			for(String s : interests){
				interest += s + " ";
			}
			interest = interest.trim();
		}
		
		//从配置文件中读取新用户积分
		int registerScore = Integer.parseInt(this.getInitParameter("registerScore"));
		
		//服务端数据检验
		if(username.length()<5 || username.length()>10){
			out.print("<script>alert('server:用户名的长度必须在5-10个字符之间');history.back()</script>");
			return;
		}
		
		DBManager dbManager = DBManager.getInstance();
		
		String sql = "insert into user values(null, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		boolean flag = dbManager.execUpdate(sql, username, password, registerScore, photo, gender, job, interest, new Date());
		
		if(flag){   //插入成功
			
			sql = "select * from user where username = ? and password = ?";
			ResultSet rs = dbManager.execQuery(sql, username, password);
			
			try {
				if(rs.next()){   //登录成功
						
					//取出用户编号
					int userid = rs.getInt(1);

					//创建并填充user对象
					User user = new User();
					user.setUserid(userid);
					user.setUsername(username);
					user.setScore(registerScore);
					user.setPhoto(photo);
					
					//将user对象保存到session属性范围中
					session.setAttribute("user", user);
					
					//在application属性范围中更新在线用户人数 +1
					if(application.getAttribute("onlineCount")==null){   //第一次访问
						application.setAttribute("onlineCount", 1);
					}else{
						int onlineCount = (Integer)application.getAttribute("onlineCount");
						application.setAttribute("onlineCount", onlineCount + 1);
					}
					
					//延迟跳转到主页
					//response.sendRedirect("index.jsp");
					response.sendRedirect("user_register_result.jsp");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				dbManager.closeConnection();
			}
			
		}else{     //插入失败
			
			//使用JS实现客户端重定向
			out.print("<script>alert('注册失败，请重新输入');history.back()</script>");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
