package com.leedarson.platform.u202020328.devices.interfaces.rest.transform;

import com.leedarson.platform.u202020328.devices.domain.model.commands.UpdateSensorCommand;
import com.leedarson.platform.u202020328.devices.interfaces.rest.resources.UpdateSensorResource;

public class UpdateSensorCommandFromResourceAssembler {
    public static UpdateSensorCommand toCommandFromResource(Long documentId, UpdateSensorResource resource){
        return new UpdateSensorCommand(documentId, resource.name(), resource.description(), resource.sensorType());
    }
}
