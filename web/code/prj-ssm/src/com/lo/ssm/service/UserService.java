package com.lo.ssm.service;

import com.lo.ssm.bean.User;

public interface UserService {

	 User getUser4Login(User user);

	int insert(User user);

}
