package com.gabryel.login.mapper;

import com.gabryel.login.dto.LoginRequestDTO;
import com.gabryel.login.dto.LoginResponseDTO;
import com.gabryel.login.entity.LoginEntity;

public class LoginMapper {
	
		public static LoginEntity toEntity(LoginRequestDTO dto) {
			LoginEntity login = new LoginEntity();
			login.setName(dto.getName());
			login.setEmail(dto.getEmail());
			login.setPassword(dto.getPassword());
			login.setBornYear(dto.getBirthYear());
			
			return login;
		}
		
		public static LoginResponseDTO toDTO(LoginEntity login) {
			LoginResponseDTO dto = new LoginResponseDTO();
			dto.setId(login.getId());
			dto.setName(login.getName());
			dto.setEmail(login.getEmail());
			dto.setBirthYear(login.getBirthYear());
			
			return dto;
		}
}
