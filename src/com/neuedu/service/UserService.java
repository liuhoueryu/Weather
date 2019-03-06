package com.neuedu.service;

import com.neuedu.entity.User;

public interface UserService {

	User login(String username, String password);
	
}
