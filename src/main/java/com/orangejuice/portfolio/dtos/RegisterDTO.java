package com.orangejuice.portfolio.dtos;


import com.orangejuice.portfolio.enums.EnumRole;


public record RegisterDTO(String name, String email, String password, EnumRole user_role) {

}
