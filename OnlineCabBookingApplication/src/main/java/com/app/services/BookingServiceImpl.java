package com.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.exceptions.BookingException;
import com.app.models.Booking;
import com.app.models.Cab;
import com.app.models.Customer;
import com.app.models.Driver;
import com.app.repositories.BookingRepository;
import com.app.repositories.CabRepository;
import com.app.repositories.CustomerRepository;
import com.app.repositories.DriverRepository;

@Service
public class BookingServiceImpl implements BookingService {
	
     @Autowired
     private BookingRepository bookingRepository;
     
     @Autowired
     private CustomerRepository customerRepository;
     
     @Autowired
     private CabRepository cabRepository;
    
     @Autowired
     private DriverRepository driverRepository;

     @Override
     public Booking insertBooking(Long customerId, Long driverId, Booking booking) throws BookingException {
         Customer customer = customerRepository.findById(customerId)
                 .orElseThrow(() -> new BookingException("Customer not found by customerId: " + customerId));

         Driver driver = driverRepository.findById(driverId)
                 .orElseThrow(() -> new BookingException("Driver not found by driverId: " + driverId));

         Cab cab = driver.getCabs(); 
         booking.setPickupLocation(booking.getPickupLocation());
         booking.setDestination(booking.getDestination());
         booking.setCustomer(customer);
         booking.setCab(cab);
         booking.setDriver(driver);

         return bookingRepository.save(booking);
     }

     @Override
     public Booking updateBooking(Long customerId, Long bookingId, Booking updatedBooking) throws BookingException {

        Optional<Booking> existingBookingOptional = bookingRepository.findById(bookingId);

         if (existingBookingOptional.isPresent()) {
             Booking existingBooking = existingBookingOptional.get();

       
             if (!existingBooking.getCustomer().getCustomerId().equals(customerId)) {
                 throw new BookingException("Customer does not have permission to update this booking");
             }

             existingBooking.setPickupLocation(updatedBooking.getPickupLocation());
             existingBooking.setDestination(updatedBooking.getDestination());
             

             return bookingRepository.save(existingBooking);
         } else {
             throw new BookingException("Booking not found by given bookingId: " + bookingId);
         }
     }

     @Override
     public Booking deleteBooking(Long customerId, Long bookingId) throws BookingException {
    
         Optional<Booking> bookingOptional = bookingRepository.findById(bookingId);

         if (bookingOptional.isPresent()) {
             Booking booking = bookingOptional.get();

    
             if (!booking.getCustomer().getCustomerId().equals(customerId)) {
                 throw new BookingException("Customer does not have permission to delete this booking");
             }
             bookingRepository.deleteById(bookingId);

             return booking;
         } else {
             throw new BookingException("Booking not found by given bookingId: " + bookingId);
         }
     }

//     @Override
//     public List<Booking> getAllBookings() throws BookingException {
//         return bookingRepository.findAll();
//     }
     
     @Override
     public List<Booking> getBookingsByCustomer(Long customerId) throws BookingException {
    	    Customer customer = customerRepository.findById(customerId)
    	            .orElseThrow(() -> new BookingException("Customer not found by customerId: " + customerId));
    	    
    	    return bookingRepository.findByCustomer(customer);
    	}

     @Override
     public List<Booking> getBookingsByCab(Long cabId) throws BookingException {
         Cab cab = cabRepository.findByCabId(cabId).get(0);
//                 .orElseThrow(() -> new BookingException("Cab not found by cabId: " + cabId));

         return cab.getBookings();
     }

	@Override
	public List<Booking> getAllBookingsDriver(Long driverId) throws BookingException {
	Driver driver = driverRepository.findById(driverId)
 	            .orElseThrow(() -> new BookingException("Customer not found by customerId: " + driverId));
 	    
 	    return bookingRepository.findByDriver(driver);
	}

	

}
