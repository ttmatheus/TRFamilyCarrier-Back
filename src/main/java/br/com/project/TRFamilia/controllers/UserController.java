package br.com.project.TRFamilia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.project.TRFamilia.models.User;
import br.com.project.TRFamilia.services.UserService;

@RestController
@RequestMapping("users")
public class UserController {
	
	@Autowired
	private UserService userService;

	@PostMapping("/create")
	public User createUser(@RequestBody User user) {
		return userService.saveUser(user);
	}
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody User user) {
		return userService.loginUser(user);
	}
}
