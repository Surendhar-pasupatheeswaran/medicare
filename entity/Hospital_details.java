package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name="Hospital_Details")
public class Hospital_details {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column (name="HospitalName")

	private String name;
	@Column(name="AvailBed")
	private int bed;
	@Column(name="AvailOxy")
	private int oxygen;
	@Column(name="AvailDoc")
	private int doctor;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getBed() {
		return bed;
	}
	public void setBed(int bed) {
		this.bed = bed;
	}
	public int getOxygen() {
		return oxygen;
	}
	public void setOxygen(int oxygen) {
		this.oxygen = oxygen;
	}
	public int getDoctor() {
		return doctor;
	}
	public void setDoctor(int doctor) {
		this.doctor = doctor;
	}
	public Hospital_details(int id, String name, int bed, int oxygen, int doctor) {
		super();
		this.id = id;
		this.name = name;
		this.bed = bed;
		this.oxygen = oxygen;
		this.doctor = doctor;
	}
	public Hospital_details() {
		
	}
	
}
