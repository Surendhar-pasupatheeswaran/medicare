package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.example.demo.entity.Hospital_details;
import com.example.demo.entity.Medicine_entity;
import com.example.demo.entity.Sponsor;
import com.example.demo.service.FileStorageService;
import com.example.demo.service.SponsorService;
import com.example.demo.service.FileStorageService;
import com.example.demo.service.adminserImpl;
import com.example.demo.service.medicine_service;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


@Controller
@RequestMapping("/admin")
public class AdminController {
		@Autowired
		adminserImpl ser;
		@Autowired
		medicine_service meser;
		@Autowired
		private adminserImpl repo;
		@Autowired
		SponsorService sponsser;
		@Autowired
		FileStorageService storageService;
		@Value("${phoneNumber:** Todo: buy a phone number from Twilio **}")
	    private String phoneNumber;

	    @GetMapping("/")
	    public ModelAndView showDashboard(){
	        ModelAndView dashboard = new ModelAndView("Acknowledgementforpatient");
	        dashboard.addObject("phoneNumber", phoneNumber);
	        return dashboard;
	    }
	   @GetMapping("/new_hospital")
	   public String new_employee() {
		   return"admin/new_hospital";
	   }
	   @GetMapping("/medicine")
	   public String medicine() {
		   return "admin/upload_form";
	   }
	   @GetMapping("/home")
	   public String home() {
		   return "index";
	   }
	  @GetMapping("/sponsor")
	  public String sponsor() {
		  return "sponsor";
	  }
	  
	  @GetMapping("/register")
	  public String register() {
		  return "register";
	  }
	  @GetMapping("/signin")
	  public String login() {
		  return "login";
	  }
	  
	    @PostMapping("/saveMedicine")
	    public String saveMedicine(@RequestParam("file") MultipartFile file,
	    		@RequestParam("name") String name,
	    		@RequestParam("price") int price,
	    		@RequestParam("quantity") String quantity)
	    {
	    	
	    	meser.saveMedicine(file, name, price, quantity);
	    	System.out.println("Data Saved ");
	    	return "admin/upload_form";
	    }
	    
	   @PostMapping("/saveHospital")
	   public String saveHospital(@ModelAttribute ("hospital")Hospital_details hospital) {
		   ser.saveHospital(hospital);
		   return "redirect:/admin/new_hospital";
	   }
	  // @DeleteMapping("/deleteHospital")
	   
	   
	  @GetMapping ("/viewhospital")
	  public String checkhospital(Model model)
		{
		        model.addAttribute("listHospital", repo.getAllHospital());
		  return "admin/viewhospital";
	  }
	   @GetMapping("/sponsor/edit/{id}")
	   public  String editSponsorForm(@PathVariable Integer id,Model model){
		   model.addAttribute("sponsor", sponsser.getAllSponsorById(id));
		   return "admin/editSponsor";
	   }
	    @GetMapping( "/deleteRow/{id}")
	      public String deleteRow(@PathVariable("id") Integer id) {
	          repo.deleteById(id);
	          return "redirect:/admin/viewhospital";
	      }
	   
}
