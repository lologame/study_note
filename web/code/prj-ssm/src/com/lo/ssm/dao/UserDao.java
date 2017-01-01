package com.lo.ssm.dao;

import com.lo.ssm.bean.User;

public interface UserDao {

	 User getUser4login(User user);

	int insert(User user); 

}
