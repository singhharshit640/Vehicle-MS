package com.infy.ftr.vehicles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infy.ftr.vehicles.entity.FtrVehiclesEntity;

@Repository
public interface FtrVehiclesRepository extends JpaRepository<FtrVehiclesEntity, String>{

}
