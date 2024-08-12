package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Hospital_details;

public interface adminrepo extends JpaRepository<Hospital_details,Integer>{

}
