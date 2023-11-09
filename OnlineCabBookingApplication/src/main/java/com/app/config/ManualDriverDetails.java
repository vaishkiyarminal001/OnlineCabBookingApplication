package com.app.config;

import org.springframework.security.core.userdetails.UserDetails;

import com.app.models.Driver;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
public class ManualDriverDetails implements UserDetails{
	
	private Driver driver;

	public ManualDriverDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ManualDriverDetails(Driver driver) {
		super();
		this.driver = driver;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return new ArrayList<>();
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return driver.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return driver.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
	

}
