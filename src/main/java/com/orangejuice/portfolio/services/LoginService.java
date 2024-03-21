package com.orangejuice.portfolio.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.orangejuice.portfolio.dtos.RegisterDTO;
import com.orangejuice.portfolio.dtos.UserResponseDTO;
import com.orangejuice.portfolio.entities.User;
import com.orangejuice.portfolio.repositories.UserRepository;
import com.orangejuice.portfolio.services.exceptions.MyValidationException;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class LoginService {
	
	@Autowired
	UserRepository userRepository;
	
	@Transactional
	public UserResponseDTO register(@Valid RegisterDTO data) {		
		
		User newUser = new User();
		if(this.userRepository.findByEmail(data.email()) != null) {			
			//return new UserResponseDTO(null, null, null,null,null, String.format("Email %s já cadastrado para outro usuário", data.email()));
			throw new MyValidationException(String.format("Email %s já cadastrado para outro usuário", data.email()));
		}
		
		String encriptedPass = new BCryptPasswordEncoder().encode(data.password());
		//EnumRole role = EnumRole.valueOf(data.user_role());
		newUser = new User(data.name(),data.surname(), data.email(), encriptedPass, data.user_role(), data.img_profile());
		
		this.userRepository.save(newUser);
		
		return new UserResponseDTO(
				newUser.getName(), 
				newUser.getSurname(),
				newUser.getEmail(), 
				newUser.getUserRole().getRole(), 
				newUser.getImg_profile(),
				null);
	}

}
