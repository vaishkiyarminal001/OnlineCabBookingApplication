package com.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.DTO.CustomerUpdateRequest;
import com.app.exceptions.AdminException;
import com.app.exceptions.CustomerException;
import com.app.models.Booking;
import com.app.models.Customer;
import com.app.repositories.BookingRepository;
import com.app.repositories.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private BookingRepository bookingRepository;

	@Override
	public Customer insertCustomer(Customer customer) throws CustomerException {
		try {
			
			if (customerRepository.findByEmail(customer.getEmail()) != null) {
				throw new CustomerException("Customer with the same email already exists");
			}
			return customerRepository.save(customer);
		} catch (Exception e) {
			throw new CustomerException("Failed to insert Customer");
		}
	}

	@Override
	public Customer updateCustomer(Long customerId, CustomerUpdateRequest customerUpdateRequest) throws CustomerException {
	    try {
	        Optional<Customer> existingCustomer = customerRepository.findById(customerId);
	        if (existingCustomer.isPresent()) {
	            Customer existing = existingCustomer.get();
	            existing.setCustomerName(customerUpdateRequest.getCustomerName());
	            existing.setCustomerMobileNo(customerUpdateRequest.getCustomerMobileNo());
	            existing.setEmail(customerUpdateRequest.getEmailId());
	     

	            return customerRepository.save(existing);
	        } else {
	            throw new CustomerException("Customer not found");
	        }
	    } catch (Exception e) {
	        throw new CustomerException("Failed to update Customer");
	    }
	}


	@Override
	public Customer deleteCustomer(Long customerId) throws CustomerException {
		try {
			
			Optional<Customer> customer = customerRepository.findById(customerId);
			if (customer.isPresent()) {
				List<Booking> bookings = bookingRepository.findByCustomer(customer.get());
				bookingRepository.deleteAll(bookings);
				customerRepository.deleteById(customerId);
				return customer.get();
			} else {
				throw new CustomerException("Customer not found");
			}
		} catch (Exception e) {
			throw new CustomerException("Failed to delete Customer");
		}
	}

	@Override
	public Customer viewCustomer(Long customerId) throws CustomerException {
		try {
			Optional<Customer> customer = customerRepository.findById(customerId);
			if (customer.isPresent()) {
				return customer.get();
			} else {
				throw new CustomerException("Customer not found");
			}
		} catch (Exception e) {
			throw new CustomerException("Failed to view Customer");
		}
	}

	@Override
	public List<Booking> getAllPreviousBookings() throws CustomerException {
		try {
			
			return bookingRepository.findAll(); 
		} catch (Exception e) {
			throw new CustomerException("Failed to fetch previous bookings");
		}
	}

	@Override
	public Optional<Customer> findByEmail(String Email) throws CustomerException {
		Optional<Customer> user= customerRepository.findByEmail(Email);
		 if(user.isEmpty()) throw new AdminException("No customer found");
		 return user;
	}
}
