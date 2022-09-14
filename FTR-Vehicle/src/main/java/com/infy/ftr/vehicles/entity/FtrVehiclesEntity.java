package com.infy.ftr.vehicles.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ftr_vehicle")
public class FtrVehiclesEntity {
	
	@Id
	private String vehicleNumber;
	private String vehicleName;
	private Double maxLiftingCapacity;
	private Date retireDate;
	private String vehicleStatus;
	private String country;
	private String harborLocation;
	public FtrVehiclesEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FtrVehiclesEntity(String vehicleNumber, String vehicleName, Double maxLiftingCapacity, Date retireDate,
			String vehicleStatus, String country, String harborLocation) {
		super();
		this.vehicleNumber = vehicleNumber;
		this.vehicleName = vehicleName;
		this.maxLiftingCapacity = maxLiftingCapacity;
		this.retireDate = retireDate;
		this.vehicleStatus = vehicleStatus;
		this.country = country;
		this.harborLocation = harborLocation;
	}
	public String getVehicleNumber() {
		return vehicleNumber;
	}
	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}
	public String getVehicleName() {
		return vehicleName;
	}
	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}
	public Double getMaxLiftingCapacity() {
		return maxLiftingCapacity;
	}
	public void setMaxLiftingCapacity(Double maxLiftingCapacity) {
		this.maxLiftingCapacity = maxLiftingCapacity;
	}
	public Date getRetireDate() {
		return retireDate;
	}
	public void setRetireDate(Date retireDate) {
		this.retireDate = retireDate;
	}
	public String getVehicleStatus() {
		return vehicleStatus;
	}
	public void setVehicleStatus(String vehicleStatus) {
		this.vehicleStatus = vehicleStatus;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getHarborLocation() {
		return harborLocation;
	}
	public void setHarborLocation(String harborLocation) {
		this.harborLocation = harborLocation;
	}
	
	

}
