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
import com.app.exceptions.CustomerException;
import com.app.models.Admin;
import com.app.models.Booking;
import com.app.models.Cab;
import com.app.models.Customer;
import com.app.models.Driver;
import com.app.services.AdminService;
import com.app.services.BookingService;
import com.app.services.CabService;
import com.app.services.CustomerService;


@RestController
@RequestMapping("/admin")
public class AdminController {
	
	
    @Autowired
    private AdminService adminService;
    
    @Autowired
    private BookingService bookingService;
    
    @Autowired
    private CustomerService customerService;
    
 
    
    @Autowired
    private CabService cabService;
    
    @Autowired
	private PasswordEncoder passwordEncoder;
	
    
	@PostMapping("/signin")
	public ResponseEntity<String> logInUserHandler(Authentication auth) throws AdminException {
		Admin admin = adminService.findByEmail(auth.getName()).get();
		return new ResponseEntity<>(admin.getEmail() + " Logged In Successfully", HttpStatus.ACCEPTED);
	}
    
    @PostMapping("/addAdmin")
    public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin) {
        try {
        	admin.setPassword(passwordEncoder.encode(admin.getPassword()));
            Admin createdAdmin = adminService.createAdmin(admin);
            
            return new ResponseEntity<>(createdAdmin, HttpStatus.CREATED);
        } catch (AdminException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/getAdminProfile/{adminId}")
    public ResponseEntity<?> getAdmin(@PathVariable Long adminId) {
        try {
            Admin profile = adminService.getAdminById(adminId);
            return new ResponseEntity<Admin>(profile, HttpStatus.OK);
        } catch (AdminException e) {
            return new ResponseEntity<String>("Admin not found by given adminId", HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/getAllAdmin")
    public ResponseEntity<List<Admin>> getAllAdmins() {
        List<Admin> admins = adminService.getAllAdmins();
        return new ResponseEntity<>(admins, HttpStatus.OK);
    }
    
    @PutMapping("/updateAdmin/{adminId}")
    public ResponseEntity<?> updateAdmin(@PathVariable Long adminId, @RequestBody Admin admin) {
        try {
            Admin updatedAdmin = adminService.updateAdmin(adminId, admin);
            return new ResponseEntity<Admin>(updatedAdmin, HttpStatus.OK);
        } catch (AdminException e) {
            return new ResponseEntity<String>("Admin not found by given adminId", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteAdmin/{adminId}")
    public ResponseEntity<?> deleteAdmin(@PathVariable Long adminId) {
        try {
            Admin deletedAdmin = adminService.deleteAdmin(adminId);
            return new ResponseEntity<>(deletedAdmin, HttpStatus.OK);
        } catch (AdminException e) {
        	 return new ResponseEntity<String>("Admin not found by given adminId", HttpStatus.NOT_FOUND);
        }
        }
    

    @GetMapping("/getAllCustomers")
    public ResponseEntity<?> viewCustomers() {
        try {
            List<Customer> customers = adminService.viewCustomers();
            return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
        } catch (AdminException e) {
        	 return new ResponseEntity<String>("Not available any Customers", HttpStatus.NOT_FOUND);
        }
        }
    

    @GetMapping("/getAllDrivers")
    public ResponseEntity<?> viewAllDrivers() {
        try {
            List<Driver> drivers = adminService.viewAllDrivers();
            return new ResponseEntity<List<Driver>>(drivers, HttpStatus.OK);
        } catch (AdminException e) {
        	 return new ResponseEntity<String>("Not available any Driver", HttpStatus.NOT_FOUND);
        }
    }
    
	@DeleteMapping("/deleteCustomerRide/{customerId}/{bookingId}")
	public ResponseEntity<?> deleteBooking(@PathVariable Long customerId, @PathVariable Long bookingId) {
		try {
			Booking deletedBooking = bookingService.deleteBooking(customerId, bookingId);
			return new ResponseEntity<>(deletedBooking, HttpStatus.OK);
		} catch (BookingException e) {
			return new ResponseEntity<>("Somthing wrong in given Id, Please check" , HttpStatus.BAD_REQUEST);
		}
	}
    @DeleteMapping("/deleteDriver/{driverId}/{cabId}")
    public ResponseEntity<?> deleteCab(@PathVariable Long driverId, @PathVariable Long cabId) {
        try {
            Cab deletedCab = cabService.deleteCab(driverId, cabId);
            if (deletedCab != null) {
                return new ResponseEntity<>(deletedCab, HttpStatus.OK);
            } else {
                return new ResponseEntity<Cab>(HttpStatus.NOT_FOUND);
            }
        } catch (CabException e) {
            return new ResponseEntity<String>("Somthing wrong in given Id, Please check" ,HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/getCustomers-AllPrevious-bookings")
    public ResponseEntity<?> getAllPreviousBookings() {
        try {
            List<Booking> bookings = customerService.getAllPreviousBookings();
            if(bookings.isEmpty()) {
            	 return new ResponseEntity<>("Not available any past bookings",HttpStatus.OK);
            }
            return new ResponseEntity<List<Booking>>(bookings, HttpStatus.OK);
        } catch (CustomerException e) {
            return new ResponseEntity<>("Not available any past bookings",HttpStatus.BAD_REQUEST);
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
