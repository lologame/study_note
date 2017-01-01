package com.tanc.activity.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tanc.activity.entity.User;
import com.tanc.activity.mapper.UserMapper;
import com.tanc.activity.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserMapper userMapper;

	public void addUser(User user) {
		userMapper.insert(user);		
	}
}
