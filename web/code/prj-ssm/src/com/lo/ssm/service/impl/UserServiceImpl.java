package com.lo.ssm.service.impl;



import javax.annotation.Resource;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lo.ssm.bean.User;
import com.lo.ssm.dao.UserDao;
import com.lo.ssm.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Resource
	private UserDao userDao;

	@Override
	public User getUser4Login(User user) {
		return userDao.getUser4login(user);
	}

	@Override
	public int insert(User user) {
		
		return userDao.insert(user);
	}
	
	
}
