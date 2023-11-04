package com.app.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity

public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;
    private String adminName;
    private String email;
    private String password;
    private String adminMobileNo;
    
    
	public Admin(String adminName, String email, String password, String adminMobileNo) {
		super();
		
		this.adminName = adminName;
		this.email = email;
		this.password = password;
		this.adminMobileNo = adminMobileNo;
	}


	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Long getAdminId() {
		return adminId;
	}


	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}


	public String getAdminName() {
		return adminName;
	}


	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getAdminMobileNo() {
		return adminMobileNo;
	}


	public void setAdminMobileNo(String adminMobileNo) {
		this.adminMobileNo = adminMobileNo;
	}


	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", adminName=" + adminName + ", email=" + email + ", password=" + password
				+ ", adminMobileNo=" + adminMobileNo + "]";
	}
	
	
    
    
    
}

