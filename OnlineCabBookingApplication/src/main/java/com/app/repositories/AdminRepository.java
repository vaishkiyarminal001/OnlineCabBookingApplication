package com.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.models.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {

}
