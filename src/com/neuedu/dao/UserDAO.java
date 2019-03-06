package com.neuedu.dao;

import com.neuedu.entity.User;

public interface UserDAO {

	User findUser(String username, String password);
	
}
