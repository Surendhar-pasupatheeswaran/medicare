package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Hospital;
import com.example.demo.entity.User;

public interface hospital extends JpaRepository<Hospital, Integer> {
	public Hospital findByEmail(String email);

}
