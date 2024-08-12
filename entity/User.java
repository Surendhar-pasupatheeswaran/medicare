package com.example.demo.entity;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name="Patient")
public class User implements Serializable {
	@Id
	
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column(name="Username",nullable=false)
	@NotNull(message = "Username cannot be null")
    @Size(min = 4, max = 10, message = "Username must be between 4 and 10 characters")
	private String name;
	@Column(name="Password",nullable=false)
	@NotNull(message="Password need not to be null")
	@Pattern(regexp="^[a-zA-Z0-9]{10}",message="length must be 3")  
	@Size(min=8,max=10)
	private String password;
	@NotNull(message="Email need not be null")
	@Email(message="Invalid email id")
	@Size(min=10,max=25)
	@Column(name="Email-ID",nullable=false)
	private String email;
	@NotNull(message="address need not be null")
	@Size(min=10,max=20)
	@Column(name="Address",nullable=false)
	private String address;
    @Column(name="Phone",nullable=false)
    @NotNull(message="Phone need not be null")
    @Size(min=10,max=12)
	private String phone;
   
	private String role;
	public User(int id, String name, String password, String email, String address, String phone,String role) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.role=role;
	}
	public User() {
		
	}
	public String getrole() {
		return role;
	}
	public void setrole(String role) {
		this.role=role;
	}
	
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}



	
}
