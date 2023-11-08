package com.app.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.models.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	Optional<Customer> findByEmail(String Email);
}
