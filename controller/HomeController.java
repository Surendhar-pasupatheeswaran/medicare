package com.example.demo.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Hospital;
import com.example.demo.entity.Hospital_details;
import com.example.demo.entity.Sponsor;
import com.example.demo.entity.User;
import com.example.demo.repository.Patientrepository;
import com.example.demo.repository.hospital;
import com.example.demo.service.PatientService;
import com.example.demo.service.SponsorService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	@Autowired
	private hospital hos;
	@Autowired
	private PatientService patientservice;
	@Autowired
	private Patientrepository userRepo;
	@Autowired 
	SponsorService sponser;
	@ModelAttribute
	private void userDetails(Model m,Principal p) {
		if(p!=null) {
			String email=p.getName();
			User patient=userRepo.findByEmail(email);
			m.addAttribute("patient", patient);
		}
	}
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	@GetMapping("/payment")
	public String payment (){
		return "payment";
		
	}
	@GetMapping ("/signin")
	public String login() {
			return "login";
	}
	@GetMapping("/register")
		public String register() {
		return "register";
		}
	@GetMapping("/hosptial")
	public String hospital() {
		return "hosptial";
	}
	@GetMapping("/about")
	public String about() {
		return "about";
	}
	
	public static class MessageDetails {
        public List<String> numbers;
        public String message;
    }

    @Value("${phoneNumber}")
    private String myTwilioPhoneNumber;

    @Autowired
    public HomeController(
        @Value("${twilioAccountSid}") String twilioAccountSid,
        @Value("${twilioAuthToken}") String twilioAuthToken) {
        Twilio.init(twilioAccountSid, twilioAuthToken);
    }

    @PostMapping("/send-messages")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void sendMessages(@RequestBody MessageDetails messageDetails) {

        messageDetails.numbers.stream().forEach( number -> {
            Message message = Message.creator(
                new PhoneNumber(number),
                new PhoneNumber(myTwilioPhoneNumber),
                messageDetails.message).create();
            System.out.println("Sent message w/ sid: " + message.getSid());
        });
    }

	@GetMapping("/sponsor")
	public String sponsor(Sponsor sponsor) {
		
		return "sponsor";
	}
	  @PostMapping("/savespons")
	   public String savespons(@ModelAttribute ("sponsor")Sponsor sponsor) {
		   sponser.saveSponsor(sponsor);
		   return "redirect:/payment";
	   }
	@PostMapping("/createUser")
	public String createuser(@Valid User patient,BindingResult bindingresult,Model model) {
	    if(bindingresult.hasErrors()) {
	    	return "register";
	    }
	     patientservice.createUser(patient);
		
		return "redirect:/register";
	
}
	
}

