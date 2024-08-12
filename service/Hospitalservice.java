package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Hospital_details;

public interface Hospitalservice {
	List<Hospital_details> getAllHospital();
	void saveHospital(Hospital_details hospital);
	Hospital_details getHospitalById(Integer id);
	void deleteById(Integer id);
	

}
