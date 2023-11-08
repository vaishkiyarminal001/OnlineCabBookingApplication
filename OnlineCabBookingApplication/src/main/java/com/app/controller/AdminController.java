package com.app.controller;

import java.net.http.HttpRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.models.Admin;
import com.app.services.AdminService;



@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private AdminService adminService;
	
	@PostMapping("/")
	public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin){
		Admin a = adminService.createAdmin(admin);
		return new ResponseEntity<Admin>(a,HttpStatus.CREATED);
	}
}
