package com.orangejuice.portfolio.dtos;


import com.orangejuice.portfolio.enums.EnumRole;


public record RegisterDTO(
		String name, 
		String surname,
		String img_profile,
		String email, 
		String password, 
		EnumRole user_role) {

}
