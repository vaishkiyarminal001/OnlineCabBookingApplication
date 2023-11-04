package com.app.services;

import org.springframework.stereotype.Service;

import com.app.models.Admin;

@Service
public interface AdminService {
	
	Admin createAdmin(Admin admin);

}
