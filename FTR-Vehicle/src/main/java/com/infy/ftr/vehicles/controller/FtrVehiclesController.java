package com.infy.ftr.vehicles.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.infy.ftr.vehicles.dto.FtrVehiclesDTO;
import com.infy.ftr.vehicles.dto.Message;
import com.infy.ftr.vehicles.exception.VEHICLE_ALREADY_EXISTS_Exception;
import com.infy.ftr.vehicles.exception.VEHICLE_NOT_FOUND_Exception;
import com.infy.ftr.vehicles.exception.VEHICLE_UPDATE_ALREADY_EXISTS_Exception;
import com.infy.ftr.vehicles.service.FtrVehiclesService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/ftr")
public class FtrVehiclesController {

	@Autowired
	private FtrVehiclesService service;
	
	@Autowired
	private RestTemplate template;
	
	private Logger logger = Logger.getLogger(FtrVehiclesController.class.getName());
	
	
	@PostMapping("/vehicles")
	@CircuitBreaker(name = "service", fallbackMethod = "fallBack")
	public ResponseEntity<String> insertNewVehicle(@Valid @RequestBody FtrVehiclesDTO vehicleDTO) throws VEHICLE_ALREADY_EXISTS_Exception{
		String response = service.insertNewVehicle(vehicleDTO);
		return new ResponseEntity<String>(response, HttpStatus.ACCEPTED); 
	}
	
	@GetMapping("/vehicles")
	@CircuitBreaker(name = "service", fallbackMethod = "fallBack1")
	public ResponseEntity<List<FtrVehiclesDTO>> fetchAvailableVehicles() throws VEHICLE_NOT_FOUND_Exception{
		List<FtrVehiclesDTO> availableVehicles = service.fetchAvailableVehicles();
		return new ResponseEntity<List<FtrVehiclesDTO>>(availableVehicles, HttpStatus.OK);
	}
	
	@PutMapping("/vehicles/{vehicleNumber}")
	@CircuitBreaker(name = "service", fallbackMethod = "fallBack2")
	public ResponseEntity<String> updateVehicleStatus(@PathVariable("vehicleNumber") String vehicleNumber,@RequestBody FtrVehiclesDTO dto) throws VEHICLE_NOT_FOUND_Exception, VEHICLE_UPDATE_ALREADY_EXISTS_Exception {
		String response = service.updateVehicleStatus(vehicleNumber, dto);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	
	
	//FALL BACK METHODS
	
	public ResponseEntity<String> fallBack(FtrVehiclesDTO vehicleDTO, Throwable throwable){
		logger.info("====IN FALLBACK METHOD====");
		return new ResponseEntity<String>("Invalid Data", HttpStatus.BAD_REQUEST);
	}
	
	public ResponseEntity<List<FtrVehiclesDTO>> fallBack1(Throwable throwable){
		logger.info("====IN FALLBACK METHOD====");
		return new ResponseEntity<List<FtrVehiclesDTO>>(HttpStatus.BAD_REQUEST);
	}
	
	public ResponseEntity<String> fallBack2(String vehicleNumber, FtrVehiclesDTO dto, Throwable throwable){
		logger.info("====IN FALLBACK METHOD====");
		return new ResponseEntity<String>("Invalid Data", HttpStatus.BAD_REQUEST); 
	}
	
}
