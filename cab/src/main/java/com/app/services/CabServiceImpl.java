package com.app.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.exceptions.CabException;
import com.app.models.Cab;
import com.app.models.Driver;
import com.app.repositories.CabRepository;
import com.app.repositories.DriverRepository;

@Service
public class CabServiceImpl implements CabService {

    @Autowired
    private CabRepository cabRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Override
    public Cab insertCab(Cab cab, Long driverId) throws CabException {
        try {
        	 
      Driver driver = driverRepository.findById(driverId)
                    .orElseThrow(() -> new CabException("Driver not found by driverId: " + driverId));

            if (driverRepository.existsById(driverId)) {
                cab.setDriver(driver);
                return cabRepository.save(cab);
            } else {
                throw new CabException("Driver not found");
            }
        } catch (Exception e) {
            throw new CabException("Failed to insert Cab");
        }
    }

    @Override
    public Cab updateCab(Long driverId, Long cabId, Cab updatedCab) throws CabException {
        try {
            if (driverRepository.existsById(driverId)) {
                Optional<Cab> cabOptional = cabRepository.findById(cabId);
                if (cabOptional.isPresent()) {
                    Cab existingCab = cabOptional.get();
                   
                    if (existingCab.getDriver().getDriverId().equals(driverId)) {
                      
                        existingCab.setCabRegistrationNumber(updatedCab.getCabRegistrationNumber());
                        return cabRepository.save(existingCab);
                    } else {
                        throw new CabException("Cab is not owned by the specified driver");
                    }
                } else {
                    throw new CabException("Cab not found");
                }
            } else {
                throw new CabException("Driver not found");
            }
        } catch (Exception e) {
            throw new CabException("Failed to update Cab");
        }
    }


    @Override
    public Cab deleteCab(Long driverId, Long cabId) throws CabException {
        try {
            
        	if (driverRepository.existsById(driverId) && cabRepository.existsById(cabId)) {
                Cab cab = cabRepository.findById(cabId).get();
                cabRepository.delete(cab);
                return cab;
            } else {
                throw new CabException("Driver or Cab not found");
            }
        } catch (Exception e) {
            throw new CabException("Failed to delete Cab");
        }
    }
}

