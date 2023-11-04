package com.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.models.Admin;
import com.app.repositories.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminRepository adminRepository;
	
	
	
	
	@Override
	public Admin createAdmin(Admin admin) {
		// TODO Auto-generated method stub
	return adminRepository.save(admin);
		
	}
	
	

}
