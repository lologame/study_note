package com.tanc.activity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tanc.activity.entity.User;
import com.tanc.activity.service.IUserService;

@Controller
public class MainController {
	
	@Autowired
	private IUserService userService;

	@RequestMapping("/")
	public String index(){
		return "index";
	}
}
