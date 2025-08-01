package com.gabryel.login.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginDeleteDTO {
	
	@Email(message  = "Invalid email")
	@NotBlank(message = "Email is requeired")
	private String email;
	
	@NotBlank(message = "Password is requeired")
	private String password;
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
}
