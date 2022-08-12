package com.spring.batch.secondary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.batch.secondary.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Long> {

}