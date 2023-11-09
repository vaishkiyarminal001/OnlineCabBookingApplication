package com.app.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.models.Admin;
import com.app.models.Customer;
import com.app.models.Driver;
import com.app.repositories.AdminRepository;
import com.app.repositories.CustomerRepository;
import com.app.repositories.DriverRepository;



@Service
public class ManualUserDetailsService implements UserDetailsService {
    
	@Autowired
	 private CustomerRepository customerRepository;
	
	@Autowired
	private AdminRepository adminRepository; 
	
	@Autowired
	private DriverRepository driverRepository;
	
	
	
	public boolean isAdmin(String email) {
	   Optional<Admin> admin = adminRepository.findByEmail(email);
		if(admin.isPresent()) return true;
		else return false;
	}
	public boolean isCustomer(String email) {
		   Optional<Customer> customer = customerRepository.findByEmail(email);
			if(customer.isPresent()) return true;
			else return false;
		}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		if(isAdmin(email)) {
			Optional<Admin> admin = adminRepository.findByEmail(email);
			 
			 if(admin.isEmpty()) throw new UsernameNotFoundException("Admin not found");
			 Admin us = admin.get();
			List<GrantedAuthority> authorities = new ArrayList<>() ;
			SimpleGrantedAuthority autho = new SimpleGrantedAuthority("ROLE_"+us.getRole().toUpperCase()) ;
			authorities.add(autho) ;
			User secUser = new User(us.getEmail(), us.getPassword(),  authorities) ;
			return secUser ;
		}else if (isCustomer(email)) {
			
			Optional<Customer> customer = customerRepository.findByEmail(email);
				 
				 if(customer.isEmpty()) throw new UsernameNotFoundException("User not found");
				 Customer us = customer.get();
				 	 
				List<GrantedAuthority> authorities = new ArrayList<>() ;
				SimpleGrantedAuthority autho = new SimpleGrantedAuthority("ROLE_"+us.getRole().toUpperCase()) ;
				authorities.add(autho) ;
				User secUser = new User(us.getEmail(), us.getPassword(),  authorities) ;
				return secUser ;

			}else {

				Optional<Driver> driver = driverRepository.findByEmail(email);
					 
					 if(driver.isEmpty()) throw new UsernameNotFoundException("Driver not found");
					 Driver us = driver.get();
					 List<GrantedAuthority> authorities = new ArrayList<>() ;
					SimpleGrantedAuthority autho = new SimpleGrantedAuthority("ROLE_"+us.getRole().toUpperCase()) ;
					authorities.add(autho) ;
					User secUser = new User(us.getEmail(), us.getPassword(),  authorities) ;
					return secUser ;

			}
		}


}
