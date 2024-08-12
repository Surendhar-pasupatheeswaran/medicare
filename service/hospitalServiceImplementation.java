package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.entity.Hospital;
import com.example.demo.repository.hospital;

public class hospitalServiceImplementation implements hosptialregistrationservice {
	@Autowired
    private hospital hos;
 
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
 
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Hospital user = hos.findByEmail(email);
		 
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
 
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getAuthorities());
    	}

	@Override
	public Hospital findByEmail(String email) {
		// TODO Auto-generated method stub
		return hos.findByEmail(email);
	}

	@Override
	public void save(Hospital user) {
		// TODO Auto-generated method stub
		user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
 
        hos.save(user);
	}

}
