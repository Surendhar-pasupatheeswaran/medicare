package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Hospital_details;
import com.example.demo.repository.adminrepo;
@Service
public class adminserImpl implements Hospitalservice {
     @Autowired
     adminrepo repo;
     
	@Override
	public List<Hospital_details> getAllHospital() {
		// TODO Auto-generated method stub
		return this.repo.findAll();
	}

	@Override
	public void saveHospital(Hospital_details hospital) {
			this.repo.save(hospital);
	}

	@Override
	public Hospital_details getHospitalById(Integer id) {
        Optional<Hospital_details>optional=repo.findById(id);
        Hospital_details hos=null;
        if(optional.isPresent()) {
        	hos=optional.get();
        }
        else {
        	throw new RuntimeException("");
        }
		return hos ;
	}

	@Override
	public void deleteById(Integer id) {
this.repo.deleteById(id);
	}

	

}
