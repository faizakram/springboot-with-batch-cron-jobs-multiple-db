package com.spring.batch.secondary.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spring.batch.secondary.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Integer> {

	@Query("select m from Manager m where m.email=:email")
	public Optional<Manager> findByEmail(@Param("email") String email);

}