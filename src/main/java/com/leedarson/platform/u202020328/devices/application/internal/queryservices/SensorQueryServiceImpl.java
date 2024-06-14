package com.leedarson.platform.u202020328.devices.application.internal.queryservices;

import com.leedarson.platform.u202020328.devices.domain.model.aggregates.Sensor;
import com.leedarson.platform.u202020328.devices.domain.model.queries.GetAllSensorsBySensorIdQuery;
import com.leedarson.platform.u202020328.devices.domain.model.queries.GetSensorByIdQuery;
import com.leedarson.platform.u202020328.devices.domain.services.SensorQueryService;
import com.leedarson.platform.u202020328.devices.infrastructure.persistence.jpa.repositories.SensorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SensorQueryServiceImpl implements SensorQueryService {
    private final SensorRepository sensorRepository;

    public SensorQueryServiceImpl(SensorRepository sensorRepository){
        this.sensorRepository = sensorRepository;
    }

    @Override
    public Optional<Sensor> handle(GetSensorByIdQuery query){
        return  sensorRepository.findById(query.sensorId());
    }

    @Override
    public List<Sensor> handle(GetAllSensorsBySensorIdQuery query){
        return sensorRepository.findAllBySensorType(query.sensor());
    }

}