package com.example.demo.entity;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity

public class Sponsor {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="Name")
	@NotNull(message = "LastName can not be null!!")
    @NotEmpty(message = "LastName can not be empty!!")	
	@Size(min=2,max=20)
	private String Name;
	@Column(name="Address")
	@NotNull
	@Size(min=10,max=20)
	private String address;
	@Column(name="Phone")
	@NotNull
    @Size(max = 10, min = 10, message = "Mobile number should be of 10 digits")
    @Pattern(regexp = "[7-9][0-9]{9}", message = "Mobile number is invalid!!")	@Size(min=10,max=12)
	private String phone;

	@NotNull
	@Size(min=10,max=20)
	@Column(name="EmailId")
	private String emailId;
	@Column(name="Amount")
	@NotNull
	private int Amount;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String  getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	public String getEmailId() {
		return emailId;
	}
	public Sponsor(int id, @NotNull @Size(min = 2, max = 20) String name,
			@NotNull @Size(min = 10, max = 20) String address, @NotNull @Size(min = 10, max = 12) String phone,
			@NotNull Date dateofbirth, @NotNull int aadhar, @NotNull @Size(min = 10, max = 20) String emailId,
			@NotNull int amount) {
		super();
		this.id = id;
		this.Name = name;
		this.address = address;
		this.phone = phone;
	    this.emailId = emailId;
		this.Amount = amount;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public int getAmount() {
		return Amount;
	}
	public void setAmount(int Amount) {
		this.Amount=Amount;
	}
	public Sponsor() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
