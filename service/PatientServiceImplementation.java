package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.demo.entity.User;
import com.example.demo.repository.Patientrepository;
@Service
public class PatientServiceImplementation implements PatientService {
	@Autowired
	private Patientrepository patientrepo;
	@Autowired
	private BCryptPasswordEncoder passwordENcode;
	@Override
	public User createUser(User patient) {
         patient.setPassword(passwordENcode.encode(patient.getPassword()));
         patient.setrole("ROLE_USER");
		return patientrepo.save(patient);
			
	}
	
	    public User updateUser(User user) {
	        User existingUser = patientrepo.findById(user.getId()).orElseThrow(() -> new RuntimeException("User not found"));
	        existingUser.setName(user.getName());
	        existingUser.setEmail(user.getEmail());
	        existingUser.setPassword(user.getPassword());
	        return patientrepo.save(existingUser);
	    }
	}


