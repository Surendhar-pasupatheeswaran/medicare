package com.example.demo.controller;

import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamSource;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.example.demo.entity.AppointmentEntity;
import com.example.demo.entity.Medicine_entity;
import com.example.demo.entity.User;
import com.example.demo.repository.Patientrepository;
import com.example.demo.service.AppointmentServiceImplementation;
import com.example.demo.service.FileStorageServiceImple;
import com.example.demo.service.adminserImpl;
import com.example.demo.service.medicine_service;

import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	medicine_service meser;
	@Autowired
	private JavaMailSender mailSender;
    FileStorageServiceImple storageService;
	@Autowired
	private BCryptPasswordEncoder pass;
	@Autowired
	private Patientrepository userRepo;
	@Autowired
	private AppointmentServiceImplementation appser;
	@Autowired
	private adminserImpl repo; 
	@ModelAttribute
	private void userDetails(Model m,Principal p) {
		String email=p.getName();
		User patient=userRepo.findByEmail(email);
		m.addAttribute("patient", patient);
		}
	@GetMapping("/ChangePass")
     public String loadChangpass(){
		return "user/change_password";
		
	}
	@PostMapping("/updatepass")
	
	public String changePassword(Principal p,@RequestParam("oldpass") String oldpass,@RequestParam("newpass") String newpass,HttpSession Session) {
		String email=p.getName();
		User patient=userRepo.findByEmail(email);
		boolean f=pass.matches(oldpass, patient.getPassword());
		if(f) {
			patient.setPassword(pass.encode(newpass));
			User updatePass=userRepo.save(patient);
			if(updatePass!=null) {
				Session.setAttribute("succMsg","Password change Success");
				
			}
			else
			{
				Session.setAttribute("errorMsg","something wrong");
			}
		}
		else {
			Session.setAttribute("errorMsg", "Old password incorrect");
		}
		return "redirect:/user/ChangePass";
	}
	
	@GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("listHospital", repo.getAllHospital());
        return "user/home";
    } 
	@GetMapping("/listmedicine")
	public ModelAndView returnListProduct()
	{
	  ModelAndView mv = new ModelAndView();
	  List<Medicine_entity> products = meser.getAllMedicine();
	  mv.setViewName("user/images");
	  mv.addObject("products", products);
	  return mv;
	  
	}
	@PostMapping("/Appointment")
	public String create( AppointmentEntity appointment) {
         appser.save(appointment);
         return "user/Appointment";
    }	
	
      @GetMapping("/KG")
      public String email() {
    	  return "user/KG";
      }
      @GetMapping("/KMCH")
      public String Kmch(){
    	  return "user/Kmch";
    	  
      }
      @GetMapping("/Ganga")
      public String Ganga() {
    	  return "user/Ganga";
      }
      @GetMapping("/Apollo")
      public String Apollo() {
    	  return "user/Apollo";
      }
  
      @PostMapping("/sendEmail")
      public String sendEmail(HttpServletRequest request,
             final @RequestParam MultipartFile attachFile) {
  
     	final String emailTo = request.getParameter("mailTo");
         final String subject = request.getParameter("subject");
         final String message = request.getParameter("message");
  
         // for logging
         System.out.println("emailTo: " + emailTo);
         System.out.println("subject: " + subject);
         System.out.println("message: " + message);
         System.out.println("attachFile: " + attachFile.getOriginalFilename());
  
         mailSender.send(new MimeMessagePreparator() {
          
             @Override
             public void prepare(MimeMessage mimeMessage) throws Exception {
                 MimeMessageHelper messageHelper = new MimeMessageHelper(
                         mimeMessage, true, "UTF-8");
                 messageHelper.setTo(emailTo);
                 messageHelper.setSubject(subject);
                 messageHelper.setText(message);
                  
                 // determines if there is an upload file, attach it to the e-mail
                 String attachName = attachFile.getOriginalFilename();
                 if (!attachFile.equals("")) {
  
                     messageHelper.addAttachment(attachName, new InputStreamSource() {
                          
                         @Override
                         public InputStream getInputStream() throws IOException {
                             return attachFile.getInputStream();
                         }
                     });
                 }
                  
             }
  
         });
  
         return "redirect:/user/";
     }
 
      /*  @GetMapping("/images")
      public String getListImages(Model model) {
    	    List<ImageInfo> imageInfos = storageService.loadAll().map(path -> {
    	      String filename = path.getFileName().toString();
    	      String url = MvcUriComponentsBuilder
    	          .fromMethodName(UserController.class, "getImage", path.getFileName().toString()).build().toString();

    	      return new ImageInfo(filename, url);
    	    }).collect(Collectors.toList());

    	    model.addAttribute("images", imageInfos);

    	    return "images";
    	  }*/
   
      @GetMapping("/medicine")
      public String getMedicine(Model model) {
    	  model.addAttribute("listmedicine", meser.getAllMedicine());
    	  return "user/images";
      }
	}
	

