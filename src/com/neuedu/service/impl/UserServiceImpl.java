package com.neuedu.service.impl;

import com.neuedu.dao.UserDAO;
import com.neuedu.dao.impl.UserDAOImpl;
import com.neuedu.entity.User;
import com.neuedu.service.UserService;

public class UserServiceImpl implements UserService {

	@Override
	public User login(String username, String password) {
		
		UserDAO userDAO = new UserDAOImpl();
		
		User user = userDAO.findUser(username, password);
		
		return user;
	}

}
