package com.lo.ssm.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lo.ssm.beans.User;
import com.lo.ssm.mapper.UserMapper;
import com.lo.ssm.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public User getUser4Login(User user) {
		return userMapper.getUser4Login(user);
	}


	


}
