package com.gabryel.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabryel.login.dto.LoginRequestDTO;
import com.gabryel.login.dto.LoginResponseDTO;
import com.gabryel.login.service.LoginService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/logins")
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@PostMapping
	public ResponseEntity<LoginResponseDTO> create(@RequestBody @Valid LoginRequestDTO dto){
		LoginResponseDTO loginResponse = loginService.create(dto);
		return ResponseEntity.ok(loginResponse);
	}
}
