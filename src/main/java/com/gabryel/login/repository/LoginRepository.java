package com.gabryel.login.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gabryel.login.entity.LoginEntity;

@Repository
public interface LoginRepository extends JpaRepository<LoginEntity, Long>{
	List<LoginEntity> findByNameContainingIgnoreCase(String name);
	Optional<LoginEntity> findByEmail(String email);
}
