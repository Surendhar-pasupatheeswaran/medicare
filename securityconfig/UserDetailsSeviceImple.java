package com.example.demo.securityconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepo;


@Service
public class UserDetailsSeviceImple implements UserDetailsService {
   @Autowired
	UserRepo useRepo;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		User patientdetails=useRepo.findByEmail(email);
		if(patientdetails!=null) {
			return new CustomUserDetail(patientdetails);
		}
		throw new UsernameNotFoundException("user not available");
	}

}
