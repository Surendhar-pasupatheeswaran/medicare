package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Medicine_entity;

public interface Medicinerepo extends JpaRepository<Medicine_entity, Integer> {

}
