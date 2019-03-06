package com.neuedu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.neuedu.entity.User;
import com.neuedu.service.UserService;
import com.neuedu.service.impl.UserServiceImpl;
import com.neuedu.util.DBManager;

public class UserLoginServlet extends HttpServlet {
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
		
		UserService userService = new UserServiceImpl();
		
		//调用业务方法
		User user = userService.login(username, password);
		
		if(user!=null){  //登录成功
			
			//将user对象保存到session属性范围中
			session.setAttribute("user", user);
			
			//在application属性范围中更新在线用户人数 +1
			if(application.getAttribute("onlineCount")==null){   //第一次访问
				application.setAttribute("onlineCount", 1);
			}else{
				int onlineCount = (Integer)application.getAttribute("onlineCount");
				application.setAttribute("onlineCount", onlineCount + 1);
			}
			
			//重定向到主页
			response.sendRedirect("index.jsp");
			
		}else{
			
			//使用JS实现客户端重定向
			out.print("<script>alert('用户名或密码错误，请重新登录');location='user_login.jsp'</script>");
	
		}
				
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
