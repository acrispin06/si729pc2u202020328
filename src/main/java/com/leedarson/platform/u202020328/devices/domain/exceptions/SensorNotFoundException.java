package com.leedarson.platform.u202020328.devices.domain.exceptions;

public class SensorNotFoundException extends RuntimeException{
    public SensorNotFoundException(Long along) {
        super("Sensor with id " + along + " not found");
    }
}
