package com.app.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cab {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long cabId;

	    @NotBlank(message = "Cab registration number is required")
	    private String cabRegistrationNumber;
	    
	   @JsonIgnore
	    @OneToOne
	    @JoinColumn(name = "driver_id")
	    private Driver driver;

	   
	   @JsonIgnore
	    @OneToMany(mappedBy = "cab")
	    private List<Booking> bookings;

		public Cab(String cabRegistrationNumber, Driver driver, List<Booking> bookings) {
			super();
			this.cabRegistrationNumber = cabRegistrationNumber;
			this.driver = driver;
			this.bookings = bookings;
		}

		public Cab() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Long getCabId() {
			return cabId;
		}

		public void setCabId(Long cabId) {
			this.cabId = cabId;
		}

		public String getCabRegistrationNumber() {
			return cabRegistrationNumber;
		}

		public void setCabRegistrationNumber(String cabRegistrationNumber) {
			this.cabRegistrationNumber = cabRegistrationNumber;
		}

		public Driver getDriver() {
			return driver;
		}

		public void setDriver(Driver driver) {
			this.driver = driver;
		}

		public List<Booking> getBookings() {
			return bookings;
		}

		public void setBookings(List<Booking> bookings) {
			this.bookings = bookings;
		}

		@Override
		public String toString() {
			return "Cab [cabId=" + cabId + ", cabRegistrationNumber=" + cabRegistrationNumber + ", driver=" + driver
					+ ", bookings=" + bookings + "]";
		}
	  
    
}