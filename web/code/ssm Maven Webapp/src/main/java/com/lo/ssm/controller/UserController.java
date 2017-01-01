package com.lo.ssm.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lo.ssm.beans.User;
import com.lo.ssm.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@RequestMapping("/login")
	public String login(User user,Model model){
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
