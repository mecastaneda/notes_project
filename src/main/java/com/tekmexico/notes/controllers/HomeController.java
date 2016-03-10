package com.tekmexico.notes.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tekmexico.notes.data.entities.User;
import com.tekmexico.notes.data.repositories.UserRepository;
import com.tekmexico.notes.data.services.AuthService;

@Controller
public class HomeController {
	
	private AuthService auth = new AuthService();

	@Autowired
	private UserRepository userRepo;
	
	@RequestMapping("/")
	public String goHome() {
		return "resources/main.html";
	}
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login() {
		System.out.println("login hit");
		return "resources/login/login.html";
	}
	
	@RequestMapping(value="/notes", method = RequestMethod.GET)
	public String notes() {
		System.out.println("notes hit");
		return "resources/notes/notes.html";
	}
	
	
	
	@RequestMapping(value="/auth", method = RequestMethod.POST)
	@ResponseBody
	public User auth(@RequestBody User user , HttpServletResponse response) {
		
		List<User> users = userRepo.findByNameAndPassword(user.getName(), user.getPassword());
		
		if(users.isEmpty()) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return null;
		} else {
			users.get(0).setToken(this.auth.getJwt(users.get(0).getUserId()));
			System.out.println("returning users" + users.get(0));
			return users.get(0);
		}
	}
	
	@RequestMapping(value="/checkToken", method = RequestMethod.POST)
	@ResponseBody
	public User checkToken(@RequestHeader(value="Authorization") String token, HttpServletResponse response) {
		
		System.out.println("Checking token: " + token);
		
		if(this.auth.testJwt(token)) {
			User _user = userRepo.findOne(this.auth.getUserId(token));
			return _user;
		} else {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return null;
		}
	}

}
