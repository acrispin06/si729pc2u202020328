package com.leedarson.platform.u202020328.devices.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record SensorType(SensorTypeEnum fileType) {
    public SensorType {
        if (fileType == null) {
            throw new IllegalArgumentException("SensorType cannot be null");
        }
    }

}
