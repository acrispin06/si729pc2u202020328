package com.leedarson.platform.u202020328.devices.domain.services;

import com.leedarson.platform.u202020328.devices.domain.model.aggregates.Sensor;
import com.leedarson.platform.u202020328.devices.domain.model.commands.CreateSensorCommand;
import com.leedarson.platform.u202020328.devices.domain.model.commands.DeleteSensorCommand;
import com.leedarson.platform.u202020328.devices.domain.model.commands.UpdateSensorCommand;

import java.util.Optional;

public interface SensorCommandService {
    Long handle(CreateSensorCommand command);
    Optional<Sensor> handle(UpdateSensorCommand command);
    void handle(DeleteSensorCommand command);
}
