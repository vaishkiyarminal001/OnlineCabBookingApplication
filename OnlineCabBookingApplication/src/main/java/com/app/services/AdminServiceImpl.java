package com.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.exceptions.AdminException;
import com.app.models.Admin;
import com.app.models.Customer;
import com.app.models.Driver;
import com.app.repositories.AdminRepository;
import com.app.repositories.CustomerRepository;
import com.app.repositories.DriverRepository;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired 
	private CustomerRepository customerRepository;
	
	@Autowired
	private DriverRepository driverRepository;

	@Override
	public Admin createAdmin(Admin admin) throws AdminException{
	return adminRepository.save(admin);
		
	}

	@Override
	public Admin getAdminById(Long adminId) throws AdminException {
		Optional<Admin> adminOptional = adminRepository.findById(adminId);
		 if (adminOptional.isPresent()) {
	            return adminOptional.get();
	        } else {
	        	 throw new AdminException("Admin not found by given adminId");
	        }
	}
	  @Override
	    public List<Admin> getAllAdmins() throws AdminException {
	        return adminRepository.findAll();
	    }
	
    @Override
    public Admin updateAdmin(Long adminId, Admin admin) throws AdminException {

        Optional<Admin> existingAdminOptional = adminRepository.findById(adminId);
        
        if (existingAdminOptional.isPresent()) {
            Admin existingAdmin = existingAdminOptional.get();
            existingAdmin.setAdminName(admin.getAdminName());
            existingAdmin.setEmail(admin.getEmail());
            existingAdmin.setAdminMobileNo(admin.getAdminMobileNo());
            return adminRepository.save(existingAdmin);
        } else {
            throw new AdminException("Admin not found by given adminId");
        }
    }

    @Override
    public Admin deleteAdmin(Long adminId) throws AdminException {
        
        Optional<Admin> adminOptional = adminRepository.findById(adminId);
        
        if (adminOptional.isPresent()) {
            adminRepository.deleteById(adminId);
            return adminOptional.get();
        } else {
        	 throw new AdminException("Admin not found by given adminId");
        }
    }

    @Override
    public List<Customer> viewCustomers() throws AdminException{
    	List<Customer> customers =   customerRepository.findAll();
    	if(customers.isEmpty()) {
    		throw new AdminException("No any Admin available");
    	}
    	return customers;

    }

    @Override
    public List<Driver> viewAllDrivers() throws AdminException{

    	List<Driver> drivers = driverRepository.findAll();
    	if(drivers.isEmpty()) {
    		throw new AdminException("No any driver available");
    	}
    	return drivers;
    }

	
	@Override
	public Optional<Admin> findByEmail(String Email) throws AdminException {
		Optional<Admin> user= adminRepository.findByEmail(Email);
		 if(user.isEmpty()) throw new AdminException("No admin found");
		 return user;
	}



}
