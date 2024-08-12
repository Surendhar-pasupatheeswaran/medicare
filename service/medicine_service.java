package com.example.demo.service;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Medicine_entity;
import com.example.demo.repository.Medicinerepo;

@Service
public class medicine_service {
		
	@Autowired
	Medicinerepo repo;
	
	public List<Medicine_entity> getAllMedicine(){
		return this.repo.findAll();
	}
	public void  saveMedicine(MultipartFile file,String name,int price
			,String quantity)
	{
		Medicine_entity p = new Medicine_entity();
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		if(fileName.contains(".."))
		{
			System.out.println("not a a valid file");
		}
		try {
			p.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		p.setQuantity(quantity);
		
        p.setName(name);
        p.setPrice(price);
        
        repo.save(p);
	}
	public void deleteByID(Integer id) {
		this.repo.deleteById(id);
	}
	
}
