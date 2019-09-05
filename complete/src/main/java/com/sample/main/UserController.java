package com.sample.main;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.sample.main.model.LoginForm;
import com.sample.main.model.UserRegister;
import com.sample.main.repository.User;
import com.sample.main.repository.UserRepository;

@RequestMapping("/api/auth")
@RestController
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
    PasswordEncoder bCryptPasswordEncoder;
	
	
	@PostMapping("/user/login")
	ResponseEntity<String> userLogin(@Valid @RequestBody LoginForm loginForm)
	{
		System.out.println("inside userlogin");
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword()));
		System.out.println("inside userlogin"+authentication);
		return ResponseEntity.ok().body("Successfully");
		
	}
	
	@PostMapping("/user/register")
	ResponseEntity<String> userRegister(@Valid @RequestBody UserRegister userRegister)
	{
		if(userRepository.existsByEmail(userRegister.getEmail()))
		{
			 return new ResponseEntity<String>("Fail -> Email is already taken!",
	                    HttpStatus.BAD_REQUEST);
		}
		
		if(userRepository.existsByUsername(userRegister.getUsername()))
		{
			 return new ResponseEntity<String>("Fail -> Username is already taken!",
	                    HttpStatus.BAD_REQUEST);
		}
	
		System.out.println(userRegister.getEmail());
		User user = new User();
		user.setEmail(userRegister.getEmail());
		user.setUserName(userRegister.getUsername());
		user.setPassword(bCryptPasswordEncoder.encode(userRegister.getPassword()));
		userRepository.save(user);
		return ResponseEntity.ok().body("User registered successfully!");
	}

}
