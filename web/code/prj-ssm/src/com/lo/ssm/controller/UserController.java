package com.lo.ssm.controller;


import javax.annotation.Resource;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lo.ssm.bean.User;
import com.lo.ssm.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Resource
	private UserService userService;
	
	@RequestMapping("/login")
	public String login(User user,Model model){
		
		User nUser = new User();
//		nUser.setId(3);
		nUser.setUsername("Lucy");
		nUser.setPassword("123");
		userService.insert(nUser);
		User loginUser = userService.getUser4Login(user);
		if(loginUser != null){
			model.addAttribute("user", loginUser);
			return "success";
		}
		else{
			return "error";
		}
	}
}
