package br.com.project.TRFamilia.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.project.TRFamilia.annotations.JustAdmin;
import br.com.project.TRFamilia.dto.CreateUserDTO;
import br.com.project.TRFamilia.models.User;
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

	@GetMapping
	@JustAdmin
	public List<User> getUser(
		@RequestParam(name = "user_type", required = false) String userType) {
		if("admin".equalsIgnoreCase(userType)) {
			return userService.getAdmins();
		} else if("driver".equalsIgnoreCase(userType)) {
			return userService.getDrivers();
		}

		return userService.getAllUsers();
	}
}
