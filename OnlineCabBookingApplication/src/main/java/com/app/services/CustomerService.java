package com.app.services;

import java.util.List;
import java.util.Optional;

import com.app.DTO.CustomerUpdateRequest;
import com.app.exceptions.CustomerException;
import com.app.models.Booking;
import com.app.models.Customer;

public interface CustomerService {
	
	 Customer insertCustomer(Customer customer) throws CustomerException;
	 Customer updateCustomer(Long custmerId, CustomerUpdateRequest customer) throws CustomerException;
	 Customer deleteCustomer(Long customerId) throws CustomerException;
	 Customer viewCustomer(Long customerId) throws CustomerException;
	 List<Booking> getAllPreviousBookings() throws CustomerException;
	 Optional<Customer> findByEmail(String Email) throws CustomerException;
	    

}
