package com.app.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity

public class Admin {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long adminId;
	    
	    @NotBlank(message = "Admin name is required")
	    private String adminName;

	    @Email(message = "Invalid email address")
	    private String email;

	    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	    @NotBlank(message = "Password is required")
	    @Size(min = 8, message = "Password must be at least 8 characters long")
	    private String password;

	    @NotBlank(message = "Mobile no is required")
	    private String adminMobileNo;
	    
	    
	    private String role = "ADMIN";
	    
	    
	    
		public String getRole() {
			return role;
		}


		public void setRole(String role) {
			this.role = role;
		}


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

