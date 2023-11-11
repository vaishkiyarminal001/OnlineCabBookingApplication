package com.app.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.app.exceptions.DriverException;
import com.app.models.Driver;

@Service
public interface DriverService {
	

	 Driver insertDriver(Driver driver) throws DriverException;
	    Driver updateDriver(Long driverId, Driver driver) throws DriverException;
	    Driver deleteDriver(Long driverId) throws DriverException;
	   
	    Driver viewDriver(Long driverId) throws DriverException;
	    Optional<Driver> findByEmail(String Email) throws DriverException;
	

}
