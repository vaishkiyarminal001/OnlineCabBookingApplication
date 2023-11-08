package com.app.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.exceptions.DriverException;
import com.app.models.Driver;
import com.app.repositories.DriverRepository;

@Service
public class DriverServiceImpl implements DriverService {
	
	@Autowired
	private DriverRepository driverRepository;

	@Override
	public Driver insertDriver(Driver driver) throws DriverException {
		try {
	
			return driverRepository.save(driver);
		} catch (Exception e) {
			throw new DriverException("Failed to insert Driver");
		}
	}

	@Override
	public Driver updateDriver(Long driverId, Driver driver) throws DriverException {
	    try {
	        Optional<Driver> driverOptional = driverRepository.findById(driverId);
	        if (driverOptional.isPresent()) {
	            Driver existingDriver = driverOptional.get();
	            existingDriver.setDriverName(driver.getDriverName());
	            existingDriver.setDriverMobileNo(driver.getDriverMobileNo());
	         
	            return driverRepository.save(existingDriver);
	        } else {
	            throw new DriverException("Driver not found");
	        }
	    } catch (Exception e) {
	        throw new DriverException("Failed to update Driver");
	    }
	}

	@Override
	public Driver deleteDriver(Long driverId) throws DriverException {
		try {
			
			Optional<Driver> driver = driverRepository.findById(driverId);
			if (driver.isPresent()) {
			
				driverRepository.deleteById(driverId);
				return driver.get();
			} else {
				throw new DriverException("Driver not found");
			}
		} catch (Exception e) {
			throw new DriverException("Failed to delete Driver");
		}
	}

	@Override
	public Driver viewDriver(Long driverId) throws DriverException {
		try {
			
			Optional<Driver> driver = driverRepository.findById(driverId);
			if (driver.isPresent()) {
				return driver.get();
			} else {
				throw new DriverException("Driver not found");
			}
		} catch (Exception e) {
			throw new DriverException("Failed to view Driver");
		}
	}
	
	@Override
	public Optional<Driver> findByEmail(String Email) throws DriverException {
		Optional<Driver> user= driverRepository.findByEmail(Email);
		 if(user.isEmpty()) throw new DriverException("No driver found");
		 return user;
	}


}
