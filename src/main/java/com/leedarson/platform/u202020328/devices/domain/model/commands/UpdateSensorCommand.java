package com.leedarson.platform.u202020328.devices.domain.model.commands;

public record UpdateSensorCommand(Long id, String name, String description, String sensorType) {
}
