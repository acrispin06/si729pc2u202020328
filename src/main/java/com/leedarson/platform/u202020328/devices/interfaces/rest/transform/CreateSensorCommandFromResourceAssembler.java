package com.leedarson.platform.u202020328.devices.interfaces.rest.transform;

import com.leedarson.platform.u202020328.devices.domain.model.commands.CreateSensorCommand;
import com.leedarson.platform.u202020328.devices.interfaces.rest.resources.CreateSensorResource;

public class CreateSensorCommandFromResourceAssembler {
    public static CreateSensorCommand toCommandFromResource(CreateSensorResource resource){
        return new CreateSensorCommand(resource.name(), resource.description(), resource.sensorType(), resource.sensorId());
    }
}
