package com.app.services;

import java.util.List;

import com.app.exceptions.BookingException;
import com.app.models.Booking;

public interface BookingService {
	  Booking insertBooking(Long customerId, Long driverId, Booking booking) throws BookingException;
	    Booking updateBooking(Long customerId, Long bookingId,  Booking booking) throws BookingException ;
	    Booking deleteBooking(Long customerId, Long bookingId) throws BookingException;
	    List<Booking> getAllBookingsDriver(Long driverId) throws BookingException;
	    List<Booking> getBookingsByCustomer(Long customerId) throws BookingException;
	    List<Booking> getBookingsByCab(Long cabId) throws BookingException;
	   

}
