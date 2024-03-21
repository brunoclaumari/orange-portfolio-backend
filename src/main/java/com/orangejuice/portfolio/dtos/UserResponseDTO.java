package com.orangejuice.portfolio.dtos;


public record UserResponseDTO(
		String name,
		String surname,
		String email, 
		String user_role,
		String img_profile,
		String errorMessage) {

}
