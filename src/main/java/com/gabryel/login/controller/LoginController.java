package com.gabryel.login.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabryel.login.dto.LoginDeleteDTO;
import com.gabryel.login.dto.LoginLoginDTO;
import com.gabryel.login.dto.LoginRequestDTO;
import com.gabryel.login.dto.LoginResponseDTO;
import com.gabryel.login.entity.LoginEntity;
import com.gabryel.login.service.LoginService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*")
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
	
	@GetMapping("/name/{name}")
	public ResponseEntity<List<LoginResponseDTO>> findByName(@PathVariable String name){
		return ResponseEntity.ok(loginService.findByName(name));
	}
	
	@GetMapping
	public ResponseEntity<List<LoginResponseDTO>> findAll(){
		return ResponseEntity.ok(loginService.findAll());
	}
	
	@PutMapping
	public ResponseEntity<LoginResponseDTO> update(@RequestBody @Valid LoginRequestDTO dto){
		return ResponseEntity.ok(loginService.update(dto));
	}
	
	@DeleteMapping
	public ResponseEntity<Void> delete(@RequestBody @Valid LoginDeleteDTO dto){
		loginService.delete(dto);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginLoginDTO dto){
		return ResponseEntity.ok(loginService.login(dto));
	}
}
