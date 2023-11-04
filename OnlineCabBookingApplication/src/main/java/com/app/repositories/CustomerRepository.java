package com.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.models.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
