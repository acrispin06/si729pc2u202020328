package com.leedarson.platform.u202020328.devices.infrastructure.persistence.jpa.repositories;

import com.leedarson.platform.u202020328.devices.domain.model.aggregates.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long>{
    boolean existsByNameAndSensorType(String name, String sensorType);
    boolean existsByNameAndIdIsNot(String name, Long id);
    List<Sensor> findAllBySensorType(com.leedarson.platform.u202020328.devices.domain.model.valueobjects.Sensor sensor);
}
