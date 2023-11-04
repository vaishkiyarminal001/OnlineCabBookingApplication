package com.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.models.Driver;

public interface DriverRepository extends JpaRepository<Driver, Long> {

}
