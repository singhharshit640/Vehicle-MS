package com.infy.ftr.vehicles.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.infy.ftr.vehicles.dto.FtrVehiclesDTO;
import com.infy.ftr.vehicles.entity.FtrVehiclesEntity;
import com.infy.ftr.vehicles.exception.VEHICLE_ALREADY_EXISTS_Exception;
import com.infy.ftr.vehicles.exception.VEHICLE_NOT_FOUND_Exception;
import com.infy.ftr.vehicles.exception.VEHICLE_UPDATE_ALREADY_EXISTS_Exception;
import com.infy.ftr.vehicles.repository.FtrVehiclesRepository;


@Service
public class FtrVehiclesService {
	private Logger logger = Logger.getLogger(FtrVehiclesService.class.getName());
	
	@Autowired
	private FtrVehiclesRepository repo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Value("{vehicle.create.success}")
	private String creationMessage;
	
	@Value("{vehicle.update.success}")
	private String updateMessage;
	
	
	//Service Method to insert a new vehicle
	public String insertNewVehicle(FtrVehiclesDTO vehicleDTO) throws VEHICLE_ALREADY_EXISTS_Exception {
		
		//Checking if vehicle already exists in the DB
		Optional<FtrVehiclesEntity> vehicle = repo.findById(vehicleDTO.getVehicleNumber());
		if(vehicle.isPresent()) {
			//Throw an exception that vehicle already exists in the DB
			throw new VEHICLE_ALREADY_EXISTS_Exception("Vehicle already exists");
		}
		else {
			repo.saveAndFlush(modelMapper.map(vehicleDTO, FtrVehiclesEntity.class));
			logger.info("Vehicle saved to DB!");
			return "Vehicle details are inserted successfully with vehicle number : " + vehicleDTO.getVehicleNumber();
		}
	}
	
	//Service Method to fetch all the vehicles from the DB
	public List<FtrVehiclesDTO> fetchAvailableVehicles() throws VEHICLE_NOT_FOUND_Exception{
		List<FtrVehiclesEntity> all = repo.findAll();
		List<FtrVehiclesDTO> list = new ArrayList<>();
		
		if(!(all.isEmpty())) {
			for(FtrVehiclesEntity e: all) {
				FtrVehiclesDTO dto = modelMapper.map(e, FtrVehiclesDTO.class);
				list.add(dto);
			}
			logger.info("Fetched all data from DB!");
			return list;
		}
		else {
			logger.info("Vehicle details not found");
			throw new VEHICLE_NOT_FOUND_Exception("Vehicle details not found!");
		}
		
		//For Testing resilience4j 
		//logger.info("====In fetchVehicles method====");
		//throw new RuntimeException();
	}
	
	//Service Method to update the status of the vehicle by getting vehicleNumber
	@Transactional
	public String updateVehicleStatus(String vehicleNumber, FtrVehiclesDTO dto) throws VEHICLE_NOT_FOUND_Exception, VEHICLE_UPDATE_ALREADY_EXISTS_Exception{
		Optional<FtrVehiclesEntity> vehicle = repo.findById(vehicleNumber);
		FtrVehiclesEntity entity;
		if(vehicle.get().equals(null)) {
			//Throw VEHICLE_NOT_FOUND exception
			throw new VEHICLE_NOT_FOUND_Exception("Vehicle details not found");
//			String response = "Invalid Data";
//			return response;
		}
		else {
			entity = vehicle.get();
			String entityStatus = entity.getVehicleStatus();
			
			logger.info("EntityStatus: " + entityStatus);
			
			String dtoStatus = dto.getVehicleStatus();
			
			logger.info("DTO Status: " + dtoStatus);
			if(entityStatus.equalsIgnoreCase(dtoStatus)) {
				//Throw VEHICLE_UPDATE_ALREADY_EXISTS exception
				throw new VEHICLE_UPDATE_ALREADY_EXISTS_Exception("Invalid Data");
//				String response = "Vehicle Update Already Exists";
//				return response;
			}
			else {
				entity.setVehicleStatus(dtoStatus);
				repo.saveAndFlush(entity);
			}
			modelMapper.map(entity, FtrVehiclesDTO.class);
			String response = "Status of vehicleNumber: " + vehicleNumber + " updated successfully";
			return response;
		}
	}

}
