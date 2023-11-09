package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.DTO.CustomerUpdateRequest;
import com.app.exceptions.AdminException;
import com.app.exceptions.BookingException;
import com.app.exceptions.CustomerException;
import com.app.models.Booking;
import com.app.models.Customer;
import com.app.services.BookingService;
import com.app.services.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    
    @Autowired
	private BookingService bookingService;
    
    
    @Autowired
	private PasswordEncoder passwordEncoder;
	
    
	@PostMapping("/signin")
	public ResponseEntity<String> logInUserHandler(Authentication auth) throws AdminException {
	Customer customer = customerService.findByEmail(auth.getName()).get();
		return new ResponseEntity<>(customer.getEmail() + " Logged In Successfully", HttpStatus.ACCEPTED);
	}
    
    

    @PostMapping("/addCustomer")
    public ResponseEntity<Customer> insertCustomer(@RequestBody Customer customer) {
        try {
        	
        	   customer.setPassword(passwordEncoder.encode(customer.getPassword()));
            Customer newCustomer = customerService.insertCustomer(customer);
         
            return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
        } catch (CustomerException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateCustomer/{customerId}")
    public ResponseEntity<?> updateCustomer(@PathVariable Long customerId, @RequestBody CustomerUpdateRequest customer) {
        try {
            Customer updatedCustomer = customerService.updateCustomer(customerId, customer);
            if (updatedCustomer != null) {
                return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
            } else {
                return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
            }
        } catch (CustomerException e) {
            return new ResponseEntity<>("Somthing wrong in given Id, Please check" ,HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteCustomer/{customerId}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long customerId) {
        try {
            Customer deletedCustomer = customerService.deleteCustomer(customerId);
            if (deletedCustomer != null) {
                return new ResponseEntity<>(deletedCustomer, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Somthing wrong in given Id, Please check" ,HttpStatus.NOT_FOUND);
            }
        } catch (CustomerException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("getCustomerProfile/{customerId}")
    public ResponseEntity<?> viewCustomer(@PathVariable Long customerId) {
        try {
            Customer customer = customerService.viewCustomer(customerId);
            if (customer != null) {
                return new ResponseEntity<>(customer, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (CustomerException e) {
            return new ResponseEntity<>("Not able to fetch",HttpStatus.BAD_REQUEST);
        }
    }



	@PostMapping("bookeRide/insert/{customerId}/{driverId}")
	public ResponseEntity<?> insertBooking(@PathVariable Long customerId, @PathVariable Long driverId, @RequestBody Booking booking) {
		try {
			Booking createdBooking = bookingService.insertBooking(customerId, driverId, booking);
			return new ResponseEntity<>(createdBooking, HttpStatus.CREATED);
		} catch (BookingException e) {
			return new ResponseEntity<>("somthing worng in given id",HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/updateInRide/{customerId}/{bookingId}")
	public ResponseEntity<?> updateBooking(@PathVariable Long customerId, @PathVariable Long bookingId,
			@RequestBody Booking updatedBooking) {
		try {
			Booking updatedBookingResult = bookingService.updateBooking(customerId, bookingId, updatedBooking);
			return new ResponseEntity<>(updatedBookingResult, HttpStatus.OK);
		} catch (BookingException e) {
			return new ResponseEntity<>("Somtning wrong in id",HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/deleteRide/{customerId}/{bookingId}")
	public ResponseEntity<?> deleteBooking(@PathVariable Long customerId, @PathVariable Long bookingId) {
		try {
			Booking deletedBooking = bookingService.deleteBooking(customerId, bookingId);
			return new ResponseEntity<>(deletedBooking, HttpStatus.OK);
		} catch (BookingException e) {
			return new ResponseEntity<>("Somthing wrong in id",HttpStatus.BAD_REQUEST);
		}
	}

//	@GetMapping("/allRides")
//	public ResponseEntity<?> getAllBookings() {
//		try {
//			List<Booking> bookings = bookingService.getAllBookings();
//			return new ResponseEntity<List<Booking>>(bookings, HttpStatus.OK);
//		} catch (BookingException e) {
//			return new ResponseEntity<>("not available",HttpStatus.NOT_FOUND);
//		}
//	}

	@GetMapping("/getBookingsByCustomer/{customerId}")
	public ResponseEntity<List<Booking>> getBookingsByCustomer(@PathVariable Long customerId) {
		try {
			List<Booking> bookings = bookingService.getBookingsByCustomer(customerId);
			return new ResponseEntity<>(bookings, HttpStatus.OK);
		} catch (BookingException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
   
	@GetMapping("/getBookingsByCab/{cabId}")
	public ResponseEntity<List<Booking>> getBookingsByCab(@PathVariable Long cabId) {
		try {
			List<Booking> bookings = bookingService.getBookingsByCab(cabId);
			return new ResponseEntity<>(bookings, HttpStatus.OK);
		} catch (BookingException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	
	}
	}
