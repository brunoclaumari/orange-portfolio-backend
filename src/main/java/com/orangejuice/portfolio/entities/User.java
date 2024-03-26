package com.orangejuice.portfolio.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.orangejuice.portfolio.enums.EnumRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name = "tb_user")
public class User implements UserDetails, Serializable{// UserDetails, 
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	
	private String first_name;	
	
	private String surname;
	
	private String email;//é o login, propriamente dito
	
	private String password;	
	
	@Column(name = "user_role",columnDefinition = "integer")	
	private EnumRole user_role;

	private String img_profile;
	
	public User() {
		
	}

	public User(Long id,String first_name, String surname, String email, String password, EnumRole user_role, String img_profile) {		
		this.id = id;
		this.first_name = first_name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.user_role = user_role;
		this.img_profile = img_profile;
	}
	
	public User(String first_name, String surname, String email, String password, EnumRole user_role, String img_profile) {	
		this.first_name = first_name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.user_role = user_role;
		this.img_profile = img_profile;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public EnumRole getUserRole() {
		return this.user_role;
	}

	public void setUserRole(EnumRole user_role) {
		this.user_role = user_role;
	}	
	

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getImg_profile() {
		return img_profile;
	}

	public void setImg_profile(String img_profile) {
		this.img_profile = img_profile;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id);
	}

		
	
	public boolean hasRole(String roleName) {

		return this.user_role.getRole().equals(roleName);
	}

	@Override 
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		if(this != null) {
			if(this.user_role == EnumRole.ADMIN) {
				return List.of(
						new SimpleGrantedAuthority("ROLE_ADMIN"),
						new SimpleGrantedAuthority("ROLE_USER")
						);
			}
			
			return List.of(new SimpleGrantedAuthority("ROLE_USER"));			
		}
		
	return null;
	

	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	

}
