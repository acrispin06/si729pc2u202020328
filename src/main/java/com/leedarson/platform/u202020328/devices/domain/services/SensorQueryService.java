package com.leedarson.platform.u202020328.devices.domain.services;

import com.leedarson.platform.u202020328.devices.domain.model.aggregates.Sensor;
import com.leedarson.platform.u202020328.devices.domain.model.queries.GetAllSensorsBySensorIdQuery;
import com.leedarson.platform.u202020328.devices.domain.model.queries.GetSensorByIdQuery;

import java.util.List;
import java.util.Optional;

public interface SensorQueryService {

    Optional<Sensor> handle(GetSensorByIdQuery query);
    List<Sensor> handle(GetAllSensorsBySensorIdQuery query);
}
