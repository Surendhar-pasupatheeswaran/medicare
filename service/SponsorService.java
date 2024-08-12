package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Sponsor;
import com.example.demo.repository.SponsorRepo;

@Service
public class SponsorService {
	@Autowired
	SponsorRepo repo;
	public List<Sponsor> getAllSponsor(){
       return this.repo.findAll();		
	}
	public void saveSponsor(Sponsor sponsor) {
       this.repo.save(sponsor);
	}
	public void deleteSponsor(Integer id) {
		this.repo.deleteById(id);
	}
	public Sponsor getAllSponsorById(Integer id) {
		return repo.findById(id).get();
	}
	public Sponsor updateSponsor(Sponsor sponsor) {
		return repo.save(sponsor);
	}

}
