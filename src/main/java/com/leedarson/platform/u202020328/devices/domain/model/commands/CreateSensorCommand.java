package com.leedarson.platform.u202020328.devices.domain.model.commands;

public record CreateSensorCommand(String name, String description, String sensorType, int project) {
}