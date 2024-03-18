package com.orangejuice.portfolio.dtos;

import java.time.Instant;

public record AuthResponseDTO(
		String email, 
		String access_token, 
		Instant expires_in) {

}
