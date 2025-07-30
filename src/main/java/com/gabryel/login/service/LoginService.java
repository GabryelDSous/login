package com.gabryel.login.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gabryel.login.dto.LoginRequestDTO;
import com.gabryel.login.dto.LoginResponseDTO;
import com.gabryel.login.entity.LoginEntity;
import com.gabryel.login.mapper.LoginMapper;
import com.gabryel.login.repository.LoginRepository;

@Service
public class LoginService {
	
	
	private final LoginRepository loginRepository;

	public LoginService(LoginRepository loginRepository) {
		this.loginRepository = loginRepository;
	}
	
	public LoginResponseDTO create(LoginRequestDTO dto) {
		Optional<LoginEntity> existingUser = loginRepository.findByEmail(dto.getEmail());
		
		if(existingUser.isPresent())
			throw new RuntimeException("This email is already being used");
		
		LoginEntity login = LoginMapper.toEntity(dto);
		LoginEntity saved = loginRepository.save(login);
		return LoginMapper.toDTO(saved);
	}
	
	
	
}
