package com.app.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Driver {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long driverId;

	    @NotBlank(message = "Driver name is required")
	    private String driverName;



	    @Email(message = "Invalid email format")
	    private String email;

	    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	    @NotBlank(message = "Password is required")
	    private String password;
	    
	    @NotBlank(message = "Mobile no is required")
	    private String driverMobileNo;
	    
	    private String role = "DRIVER";
	    
	    @JsonIgnore
	    @OneToOne(mappedBy = "driver")
	    private Cab cabs;
	    
	    @JsonIgnore
	    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL)
	    private List<Booking> bookings;
	    
	    

		public Driver(@NotBlank(message = "Driver name is required") String driverName,
				@Email(message = "Invalid email format") String email,
				@NotBlank(message = "Password is required") String password,
				@NotBlank(message = "Mobile no is required") String driverMobileNo, String role, Cab cabs,
				List<Booking> bookings) {
			super();
			this.driverName = driverName;
			this.email = email;
			this.password = password;
			this.driverMobileNo = driverMobileNo;
			this.role = role;
			this.cabs = cabs;
			this.bookings = bookings;
		}
		
		

		public Driver() {
			super();
			// TODO Auto-generated constructor stub
		}



		public Long getDriverId() {
			return driverId;
		}

		public void setDriverId(Long driverId) {
			this.driverId = driverId;
		}

		public String getDriverName() {
			return driverName;
		}

		public void setDriverName(String driverName) {
			this.driverName = driverName;
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

		public String getDriverMobileNo() {
			return driverMobileNo;
		}

		public void setDriverMobileNo(String driverMobileNo) {
			this.driverMobileNo = driverMobileNo;
		}

		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}

		public Cab getCabs() {
			return cabs;
		}

		public void setCabs(Cab cabs) {
			this.cabs = cabs;
		}

		public List<Booking> getBookings() {
			return bookings;
		}

		public void setBookings(List<Booking> bookings) {
			this.bookings = bookings;
		}

		@Override
		public String toString() {
			return "Driver [driverId=" + driverId + ", driverName=" + driverName + ", email=" + email + ", password="
					+ password + ", driverMobileNo=" + driverMobileNo + ", role=" + role + ", cabs=" + cabs + ", bookings="
					+ bookings + "]";
		}

	       
    
    
    
}