package com.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.app.exceptions.AdminException;
import com.app.models.Admin;
import com.app.models.Customer;
import com.app.models.Driver;

@Service
public interface AdminService {
	
	Admin createAdmin(Admin admin) throws AdminException;
    Admin updateAdmin(Long adminId, Admin admin) throws AdminException;
    Admin deleteAdmin(Long adminId) throws AdminException;
    Admin getAdminById(Long adminId) throws AdminException;
    List<Admin> getAllAdmins() throws AdminException;
    List<Customer> viewCustomers() throws AdminException;
    List<Driver> viewAllDrivers() throws AdminException;
    Optional<Admin> findByEmail(String Email) throws AdminException;

}
