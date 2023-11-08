package com.app.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;


    @NotBlank(message = "Pickup location is required")
    private String pickupLocation;

    @NotBlank(message = "Destination is required")
    private String destination;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
     
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "cab_id")
    private Cab cab;
   
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;
    
	public Booking(String pickupLocation, String destination, Customer customer, Cab cab, Driver driver) {
		super();
		this.pickupLocation = pickupLocation;
		this.destination = destination;
		this.customer = customer;
		this.cab = cab;
		this.driver = driver;
	}

	public Booking() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public String getPickupLocation() {
		return pickupLocation;
	}

	public void setPickupLocation(String pickupLocation) {
		this.pickupLocation = pickupLocation;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Cab getCab() {
		return cab;
	}

	public void setCab(Cab cab) {
		this.cab = cab;
	}

	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", pickupLocation=" + pickupLocation + ", destination=" + destination
				+ ", customer=" + customer + ", cab=" + cab + "]";
	}
    
    
    
    
    
    
    
   
}