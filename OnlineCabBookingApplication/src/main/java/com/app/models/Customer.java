package com.app.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long customerId;

	    

	    @NotBlank(message = "Customer name is required")
	    private String customerName;

	    @Email(message = "Invalid email format")
	    private String email;

	    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	    @NotBlank(message = "Password is required")
	    private String password;
	    
	    @NotBlank(message = "Customer name is required")
	    private String customerMobileNo;

	    private String role = "CUSTOMER";
	    

	    @JsonIgnore
	    @OneToMany(mappedBy = "customer")
	    private List<Booking> bookings;

	    
		public Customer(String customerName, String emailId, String password, String customerMobileNo,
				List<Booking> bookings) {
			super();
			this.customerName = customerName;
			this.email = emailId;
			this.password = password;
			this.customerMobileNo = customerMobileNo;
			this.bookings = bookings;
		}

		public Customer() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Long getCustomerId() {
			return customerId;
		}

		public void setCustomerId(Long customerId) {
			this.customerId = customerId;
		}

		public String getCustomerName() {
			return customerName;
		}

		public void setCustomerName(String customerName) {
			this.customerName = customerName;
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

		public String getCustomerMobileNo() {
			return customerMobileNo;
		}

		public void setCustomerMobileNo(String customerMobileNo) {
			this.customerMobileNo = customerMobileNo;
		}

		public List<Booking> getBookings() {
			return bookings;
		}

		public void setBookings(List<Booking> bookings) {
			this.bookings = bookings;
		}
		
		

		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}

		@Override
		public String toString() {
			return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", email=" + email
					+ ", password=" + password + ", customerMobileNo=" + customerMobileNo + ", bookings=" + bookings + "]";
		}
	    
	    
	    
	    
    
    
    
   
}
