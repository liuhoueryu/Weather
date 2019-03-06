package com.neuedu.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.neuedu.dao.UserDAO;
import com.neuedu.entity.User;
import com.neuedu.util.DBManager;

public class UserDAOImpl implements UserDAO {

	@Override
	public User findUser(String username, String password) {
		
		DBManager dbManager = DBManager.getInstance();
		
		String sql = "select * from user where username = ? and password = ?";
		
		ResultSet rs = dbManager.execQuery(sql, username, password);
		
		try {
		
			if(rs.next()){    //登录成功
					
				//取出用户编号
				int userid = rs.getInt(1);
			
				//取出积分
				int score = rs.getInt(4);
			
				//取出头像
				String photo = rs.getString(5);
				
				//创建并填充user对象
				User user = new User();
				
				user.setUserid(userid);
				user.setUsername(username);
				user.setScore(score);
				user.setPhoto(photo);
				
				return user;
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally{
			dbManager.closeConnection();
		}
		
		return null;
	}

}
