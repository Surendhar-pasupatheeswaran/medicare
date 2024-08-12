package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.AppointmentEntity;
import com.example.demo.repository.Appointmentrepository;

@Service
public class AppointmentServiceImplementation  {

	private Appointmentrepository appointmentRepository;

	//inject employee dao
	@Autowired   //Adding bean id @Qualifier
	public AppointmentServiceImplementation( Appointmentrepository obj)
	{
		appointmentRepository=obj;
	}
	
	
	public void save(AppointmentEntity app)
	{
		
		appointmentRepository.save(app);
	}
	
	
	public List<AppointmentEntity> findAll() {
		return appointmentRepository.findAll();
	}

    
	
}
