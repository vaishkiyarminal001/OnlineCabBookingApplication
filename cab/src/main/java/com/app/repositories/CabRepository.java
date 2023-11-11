package com.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.models.Cab;

public interface CabRepository extends JpaRepository<Cab, Long> {
	List<Cab> findByCabId(Long cabId);
	
}
