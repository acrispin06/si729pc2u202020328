package com.leedarson.platform.u202020328.devices.interfaces.rest.transform;

import com.leedarson.platform.u202020328.devices.domain.model.aggregates.Sensor;
import com.leedarson.platform.u202020328.devices.interfaces.rest.resources.SensorResource;

public class SensorResourceFromEntityAssembler {
    public static SensorResource toResourceFromEntity(Sensor entity){
        return new SensorResource(entity.getId(), entity.getName(), entity.getDescription(), entity.getSensorType(), entity.getProjectId());
    }
}
