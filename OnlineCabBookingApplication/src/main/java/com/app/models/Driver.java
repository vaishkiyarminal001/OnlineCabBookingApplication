package com.app.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long driverId;
    private String driverName;
    private String driverMobleNo;

    @OneToOne(mappedBy = "driver")
    private Cab cabs;

	public Driver(String driverName, String driverMobleNo, Cab cabs) {
		super();
		this.driverName = driverName;
		this.driverMobleNo = driverMobleNo;
		this.cabs = cabs;
	}

	public Driver() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getDriverId() {
		return driverId;
	}

	public void setDriverId(Long driverId) {
		this.driverId = driverId;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getDriverMobleNo() {
		return driverMobleNo;
	}

	public void setDriverMobleNo(String driverMobleNo) {
		this.driverMobleNo = driverMobleNo;
	}

	public Cab getCabs() {
		return cabs;
	}

	public void setCabs(Cab cabs) {
		this.cabs = cabs;
	}

	@Override
	public String toString() {
		return "Driver [driverId=" + driverId + ", driverName=" + driverName + ", driverMobleNo=" + driverMobleNo
				+ ", cabs=" + cabs + "]";
	}
    
    
    
    
}