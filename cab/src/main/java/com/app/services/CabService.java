package com.app.services;

import com.app.exceptions.CabException;
import com.app.models.Cab;

public interface CabService {
	
	 Cab insertCab(Cab cab, Long driverId) throws CabException;
	 Cab updateCab(Long driverId, Long cabId,  Cab cab) throws CabException;
	 Cab deleteCab(Long driverId, Long cabId) throws CabException;


}
