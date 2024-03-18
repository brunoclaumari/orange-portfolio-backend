package com.orangejuice.portfolio.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.orangejuice.portfolio.dtos.AuthResponseDTO;
import com.orangejuice.portfolio.dtos.LoginDTO;
import com.orangejuice.portfolio.dtos.RegisterDTO;
import com.orangejuice.portfolio.dtos.UserResponseDTO;
import com.orangejuice.portfolio.entities.User;
import com.orangejuice.portfolio.repositories.UserRepository;
import com.orangejuice.portfolio.security.MyTokenService;
import com.orangejuice.portfolio.services.LoginService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/myauth")
public class LoginController {

	@Autowired
	AuthenticationManager authenticationManager;
	
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	LoginService myAuthService;
	
	@Autowired
	MyTokenService tokenService;
	
	@PostMapping("/login")
	public ResponseEntity<AuthResponseDTO> login(@RequestBody @Valid LoginDTO data) {
		var userNamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
		var auth = this.authenticationManager.authenticate(userNamePassword);
		
		//var expire = auth.get
		var token = tokenService.generateToken((User)auth.getPrincipal());
		
		AuthResponseDTO resp = new AuthResponseDTO(auth.getName(), token, tokenService.getExpiresIn());
		
		return ResponseEntity.ok().body(resp);
	}
	
	@PostMapping("/register")
	public ResponseEntity<UserResponseDTO> register(@RequestBody @Valid RegisterDTO data) {
		

		UserResponseDTO userResponseDTO = myAuthService.register(data);
		if(userResponseDTO.errorMessage() != null) //se retornou ID null, é porque já tem o usuário
			ResponseEntity.badRequest().body(userResponseDTO);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(userResponseDTO.email()).toUri();
		
		return ResponseEntity.created(uri).body(userResponseDTO);
	}
	
}
