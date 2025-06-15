package br.com.project.TRFamilia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.project.TRFamilia.dto.CreateUserDTO;
import br.com.project.TRFamilia.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("users")
public class UserController {
	
	@Autowired
	private UserService userService;

	@PostMapping("/create")
	public ResponseEntity<?> createUser(@RequestBody @Valid CreateUserDTO user, HttpServletRequest request) {
		String userId = (String) request.getAttribute("userId");
        String userEmail = (String) request.getAttribute("userEmail");
		String userRole = (String) request.getAttribute("userRole");
		return userService.saveUser(user, userId, userEmail, userRole);
	}
}
