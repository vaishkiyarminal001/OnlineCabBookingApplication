package com.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.models.Booking;
import com.app.models.Customer;
import com.app.models.Driver;

public interface BookingRepository extends JpaRepository<Booking, Long> {
	 List<Booking> findByCustomer(Customer customer);
	 List<Booking> findByDriver(Driver driver);
}
