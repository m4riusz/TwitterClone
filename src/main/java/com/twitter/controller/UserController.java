package com.twitter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.twitter.model.User;
import com.twitter.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping (value = "/", method = RequestMethod.GET)
	public String main(){
		
		return "index";
	}
	
	@RequestMapping (value = "/create", method = RequestMethod.GET)
	public String create(){
		User user = new User();
		user.setEmail("some@email.email");
		user.setUsername("username");
		user.setPassword("password");
		userService.createUser(user);
		return "index";
	}
	
	@RequestMapping (value = "/list", method = RequestMethod.GET)
	public String list(){
		System.out.println(userService.getAllUsers());
		return "index";
	}
}
