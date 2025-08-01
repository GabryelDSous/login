package com.gabryel.login.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gabryel.login.dto.LoginDeleteDTO;
import com.gabryel.login.dto.LoginLoginDTO;
import com.gabryel.login.dto.LoginRequestDTO;
import com.gabryel.login.dto.LoginResponseDTO;
import com.gabryel.login.entity.LoginEntity;
import com.gabryel.login.exception.RepositoryIsEmpty;
import com.gabryel.login.exception.UserDidntFind;
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
	
	public List<LoginResponseDTO> findByName(String name) {
		List<LoginEntity> existingUsers = loginRepository.findByNameContainingIgnoreCase(name);
		if(existingUsers.isEmpty())
			throw new UserDidntFind("Users '" + name + "' could not be found");
		return existingUsers
				.stream()
				.map(LoginMapper::toDTO)
				.toList();
	}
	
	public List<LoginResponseDTO> findAll(){
		if(loginRepository.findAll().isEmpty())
			throw new RepositoryIsEmpty("Repository is empty");
		return loginRepository.findAll()
				.stream()
				.map(LoginMapper::toDTO)
				.toList();
	}
	
	public LoginResponseDTO update(LoginRequestDTO dto) {
		Optional<LoginEntity> existingUser = loginRepository.findByEmail(dto.getEmail());
		if(!existingUser.isPresent())
			throw new UserDidntFind("mail user '" + dto.getEmail() + "' could not be found");
		
		LoginEntity login = LoginMapper.toEntity(dto);
		login.setId(existingUser.get().getId());
		LoginEntity saved = loginRepository.save(login);
		
		return LoginMapper.toDTO(saved);
		
	}
	
	public void delete(LoginDeleteDTO dto) {
		LoginEntity user = loginRepository.findByEmail(dto.getEmail())
				.orElseThrow(() -> new UserDidntFind("mail user '" + dto.getEmail() + "' could not be found"));
		if(!user.getPassword().equals(dto.getPassword()))
			throw new RuntimeException("invalid password");
		loginRepository.delete(user);
	}
	
	public LoginResponseDTO login(LoginLoginDTO dto) {
		LoginEntity user = loginRepository.findByEmail(dto.getEmail())
				.orElseThrow(() -> new UserDidntFind("mail user '" + dto.getEmail() + "' could not be found"));
		if(!user.getPassword().equals(dto.getPassword()))
			throw new RuntimeException("invalid password");
		return LoginMapper.toDTO(user);
	}
}