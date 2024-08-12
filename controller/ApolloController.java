package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/Apollo")
public class ApolloController {
		
	
	@Value("${phoneNumber:** Todo: buy a phone number from Twilio **}")
    private String phoneNumber;

    @GetMapping("/")
    public ModelAndView showDashboard(){
        ModelAndView dashboard = new ModelAndView("dashboard");
        dashboard.addObject("phoneNumber", phoneNumber);
        return dashboard;
    }
    @GetMapping("/emailrequest")
    public String emailrequest() {
    	return "Apollo/emailrequest";
    }
    
    
}
