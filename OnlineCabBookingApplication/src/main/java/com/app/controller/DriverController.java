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

import com.app.exceptions.AdminException;
import com.app.exceptions.BookingException;
import com.app.exceptions.CabException;
import com.app.exceptions.DriverException;
import com.app.models.Booking;
import com.app.models.Cab;
import com.app.models.Driver;
import com.app.services.BookingService;
import com.app.services.CabService;
import com.app.services.DriverService;

@RestController
@RequestMapping("/driver")
public class DriverController {

    @Autowired
    private DriverService driverService;
    
    @Autowired
    private CabService cabService;
    
    @Autowired
    private BookingService bookingService;
    
    
    @Autowired
 	private PasswordEncoder passwordEncoder;
 	
     
 	@PostMapping("/signin")
 	public ResponseEntity<String> logInUserHandler(Authentication auth) throws AdminException {
 	Driver driver = driverService.findByEmail(auth.getName()).get();
 		return new ResponseEntity<>(driver.getEmail() + " Logged In Successfully", HttpStatus.ACCEPTED);
 	}
     
     
    
    

    @PostMapping("/addDriver")
    public ResponseEntity<Driver> insertDriver(@RequestBody Driver driver) {
        try {
        	 driver.setPassword(passwordEncoder.encode(driver.getPassword()));
            Driver newDriver = driverService.insertDriver(driver);
            return new ResponseEntity<>(newDriver, HttpStatus.CREATED);
        } catch (DriverException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateDriver/{driverId}")
    public ResponseEntity<?> updateDriver(@PathVariable Long driverId, @RequestBody Driver driver) {
        try {
            Driver updatedDriver = driverService.updateDriver(driverId, driver);
            if (updatedDriver != null) {
                return new ResponseEntity<>(updatedDriver, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (DriverException e) {
            return new ResponseEntity<String>("Something wrong in given ID",HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("deleteDriver/{driverId}")
    public ResponseEntity<?> deleteDriver(@PathVariable Long driverId) {
        try {
            Driver deletedDriver = driverService.deleteDriver(driverId);
            if (deletedDriver != null) {
                return new ResponseEntity<>(deletedDriver, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (DriverException e) {
            return new ResponseEntity<>("Somnething wrong in given ID",HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("getDriverProfile/{driverId}")
    public ResponseEntity<?> viewDriver(@PathVariable Long driverId) {
        try {
            Driver driver = driverService.viewDriver(driverId);
            if (driver != null) {
                return new ResponseEntity<>(driver, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (DriverException e) {
            return new ResponseEntity<>("Somnething wrong in given ID",HttpStatus.BAD_REQUEST);
        }
    }
    


    @PostMapping("insertCabToDriver/{driverId}")
    public ResponseEntity<?> insertCab(@PathVariable Long driverId, @RequestBody Cab cab) {
        try {
            Cab newCab = cabService.insertCab(cab, driverId);
            return new ResponseEntity<>(newCab, HttpStatus.CREATED);
        } catch (CabException e) {
            return new ResponseEntity<>("Somnething wrong in given ID",HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("updateCab/{driverId}/{cabId}")
    public ResponseEntity<?> updateCab(@PathVariable Long driverId, @PathVariable Long cabId, @RequestBody Cab cab) {
        try {
            Cab updatedCab = cabService.updateCab(driverId, cabId, cab);
            if (updatedCab != null) {
                return new ResponseEntity<>(updatedCab, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (CabException e) {
            return new ResponseEntity<>("Somnething wrong in given ID",HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("deleteCab/{driverId}/{cabId}")
    public ResponseEntity<?> deleteCab(@PathVariable Long driverId, @PathVariable Long cabId) {
        try {
            Cab deletedCab = cabService.deleteCab(driverId, cabId);
            if (deletedCab != null) {
                return new ResponseEntity<>(deletedCab, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (CabException e) {
            return new ResponseEntity<>("Somnething wrong in given ID",HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Booking>> getBookingsByCustomer(@PathVariable Long customerId) {
        try {
            List<Booking> bookings = bookingService.getBookingsByCustomer(customerId);
            return new ResponseEntity<>(bookings, HttpStatus.OK);
        } catch (BookingException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/getAllBookingsOfDriver/{driverId}/")
	    public ResponseEntity<List<Booking>> getAllBookingsForDriver(@PathVariable Long driverId) {
	      
	       try {
	    	   List<Booking> bookings = bookingService.getAllBookingsDriver(driverId);
	    	   return new ResponseEntity<>(bookings, HttpStatus.OK);
		} catch (BookingException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	    }

}
