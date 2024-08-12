package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.AppointmentEntity;
@Repository
public interface Appointmentrepository extends JpaRepository<AppointmentEntity, Integer> {

    
}