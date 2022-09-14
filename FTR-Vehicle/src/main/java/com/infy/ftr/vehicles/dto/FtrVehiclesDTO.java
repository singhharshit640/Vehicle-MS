package com.infy.ftr.vehicles.dto;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

public class FtrVehiclesDTO {

	@NotNull(message = "{vehicle.vehicleNumber.must}")
	@Pattern(regexp = "^[A-Za-z]{2}[0-9]{4}", message = "{vehicle.vehicleNumber.invalid}")
	private String vehicleNumber;
	
	@NotEmpty(message = "{vehicle.vehicleName.must}")
	@Size(max = 30, message = "{vehicle.vehicleName.invalid}")
	@Pattern(regexp = "(?i)(Tower crane|FirePlace Crane|Double treadwheel Crane|Crawler Crane|Aerial Crane|Deck Crane)", message = "{vehicle.vehicleName.invalid}")
	private String vehicleName;
	
	@NotNull(message = "{vehicle.maxLiftingCapacity.must}")
	private Double maxLiftingCapacity;
	
	@NotNull(message = "{vehicle.retireDate.must}")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy")
	private Date retireDate;
	
	@NotNull(message = "{vehicle.vehicleStatus.must}")
	@Pattern(regexp = "(?i)(Active|Retired|InProgress)", message = "{vehicle.vehicleStatus.invalid}")
	private String vehicleStatus;
	
	@NotNull(message = "{vehicle.country.must}")
	@Size(min = 5, max = 25, message = "{vehicle.country.invalid}")
	@Pattern(regexp = "^[A-Za-z][A-Za-z ]*$", message = "{vehicle.country.invalid}")
	private String country;
	
	@NotNull(message = "{vehicle.harborLocation.must}")
	@Size(min = 5, max = 25, message = "{vehicle.harborLocation.invalid}")
	@Pattern(regexp = "^[A-Za-z][A-Za-z ]*$", message = "{vehicle.harborLocation.invalidS}")
	private String harborLocation;
	public FtrVehiclesDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FtrVehiclesDTO(String vehicleNumber, String vehicleName, Double maxLiftingCapacity, Date retireDate,
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
