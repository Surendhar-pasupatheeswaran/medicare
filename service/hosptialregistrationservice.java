package com.example.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.demo.entity.Hospital;

public interface hosptialregistrationservice extends UserDetailsService {
	public Hospital findByEmail(String email);
	 
	    void save(Hospital user);
	}


