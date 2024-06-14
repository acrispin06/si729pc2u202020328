package com.leedarson.platform.u202020328.devices.application.internal.commandservices;

import com.leedarson.platform.u202020328.devices.domain.model.aggregates.Sensor;
import com.leedarson.platform.u202020328.devices.domain.model.commands.CreateSensorCommand;
import com.leedarson.platform.u202020328.devices.domain.model.commands.DeleteSensorCommand;
import com.leedarson.platform.u202020328.devices.domain.model.commands.UpdateSensorCommand;
import com.leedarson.platform.u202020328.devices.domain.services.SensorCommandService;
import com.leedarson.platform.u202020328.devices.infrastructure.persistence.jpa.repositories.SensorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SensorCommandServiceImpl implements SensorCommandService {
    private final SensorRepository sensorRepository;

    public SensorCommandServiceImpl(SensorRepository sensorRepository){
        this.sensorRepository = sensorRepository;
    }

    @Override
    public Long handle(CreateSensorCommand command){
        var projectId = new com.leedarson.platform.u202020328.devices.domain.model.valueobjects.Sensor(command.project());
        if (sensorRepository.existsByNameAndSensorType(command.name(), command.sensorType())){
            throw new IllegalArgumentException("Sensor with the same name already exists of this sensor type");
        }
        var document = new Sensor(command);
        try{
            sensorRepository.save(document);
        }catch (Exception e) {
            throw new IllegalArgumentException("Error while saving document: " + e.getMessage());
        }
        return document.getId();
    }

    @Override
    public Optional<Sensor> handle(UpdateSensorCommand command){
        if(sensorRepository.existsByNameAndIdIsNot(command.name(), command.id())){
            throw new IllegalArgumentException("Sensor with same name already exists");
        }
        var result = sensorRepository.findById(command.id());
        if (result.isEmpty()){
            throw new IllegalArgumentException("Sensor does not exists");
        }
        var sensorToUpdate = result.get();
        try{
            var updatedSensor = sensorRepository.save(sensorToUpdate.updateInformation(command.name(), command.description(), command.sensorType()));
            return Optional.of(updatedSensor);
        }catch (Exception e){
            throw new IllegalArgumentException("Error while updating sensor: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteSensorCommand command){
        if(!sensorRepository.existsById(command.sensorId())){
            throw new IllegalArgumentException("Sensor does not exist");
        }
        try {
            sensorRepository.deleteById(command.sensorId());
        }catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting document: " + e.getMessage());
        }
    }
}