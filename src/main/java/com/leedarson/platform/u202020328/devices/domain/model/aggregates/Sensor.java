package com.leedarson.platform.u202020328.devices.domain.model.aggregates;

import com.leedarson.platform.u202020328.devices.domain.model.commands.CreateSensorCommand;
import com.leedarson.platform.u202020328.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Sensor extends AuditableAbstractAggregateRoot<Sensor> {

    @Embedded
    private com.leedarson.platform.u202020328.devices.domain.model.valueobjects.Sensor sensor;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String sensorType;


    public Sensor() {
        this.sensor = new com.leedarson.platform.u202020328.devices.domain.model.valueobjects.Sensor(0);
        this.name = "";
        this.description = "";
        this.sensorType = "";
    }

    public Sensor(int project, String name, String description, String sensorType){
        this();
        this.sensor = new com.leedarson.platform.u202020328.devices.domain.model.valueobjects.Sensor(project);
        this.name = name;
        this.description = description;
        this.sensorType = sensorType;
    }

    public Sensor(CreateSensorCommand command){
        this.name = command.name();
        this.description = command.description();
        this.sensorType = command.sensorType();
        this.sensor = new com.leedarson.platform.u202020328.devices.domain.model.valueobjects.Sensor(command.project());
    }

    public Sensor updateInformation(String name, String description, String sensorType){
        this.name = name;
        this.description = description;
        this.sensorType = sensorType;
        return this;
    }

    public int getProjectId(){
        return sensor.sensorEnt();
    }

}
