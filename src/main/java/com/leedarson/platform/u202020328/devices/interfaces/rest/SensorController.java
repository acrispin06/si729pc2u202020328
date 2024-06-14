package com.leedarson.platform.u202020328.devices.interfaces.rest;

import com.leedarson.platform.u202020328.devices.domain.model.commands.DeleteSensorCommand;
import com.leedarson.platform.u202020328.devices.domain.model.queries.GetAllSensorsBySensorIdQuery;
import com.leedarson.platform.u202020328.devices.domain.model.queries.GetSensorByIdQuery;
import com.leedarson.platform.u202020328.devices.domain.model.valueobjects.Sensor;
import com.leedarson.platform.u202020328.devices.domain.services.SensorCommandService;
import com.leedarson.platform.u202020328.devices.domain.services.SensorQueryService;
import com.leedarson.platform.u202020328.devices.interfaces.rest.resources.CreateSensorResource;
import com.leedarson.platform.u202020328.devices.interfaces.rest.resources.SensorResource;
import com.leedarson.platform.u202020328.devices.interfaces.rest.resources.UpdateSensorResource;
import com.leedarson.platform.u202020328.devices.interfaces.rest.transform.CreateSensorCommandFromResourceAssembler;
import com.leedarson.platform.u202020328.devices.interfaces.rest.transform.SensorResourceFromEntityAssembler;
import com.leedarson.platform.u202020328.devices.interfaces.rest.transform.UpdateSensorCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/sensors", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Sensors", description = "Sensors Management Endpoints")
public class SensorController {

    private final SensorCommandService sensorCommandService;
    private final SensorQueryService sensorQueryService;

    public SensorController(SensorCommandService sensorCommandService, SensorQueryService sensorQueryService){
        this.sensorCommandService = sensorCommandService;
        this.sensorQueryService = sensorQueryService;
    }

    @PostMapping
    public ResponseEntity<SensorResource> createDocument(@RequestBody CreateSensorResource createSensorResource){
        var createDocumentCommand = CreateSensorCommandFromResourceAssembler.toCommandFromResource(createSensorResource);
        var documentId = sensorCommandService.handle(createDocumentCommand);
        if(documentId == 0L){
            return ResponseEntity.badRequest().build();
        }
        var getDocumentByIdQuery = new GetSensorByIdQuery(documentId);
        var document = sensorQueryService.handle(getDocumentByIdQuery);
        if(document.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        var documentResource = SensorResourceFromEntityAssembler.toResourceFromEntity(document.get());
        return new ResponseEntity<>(documentResource, HttpStatus.CREATED);
    }

    @GetMapping("/{sensorId}")
    public ResponseEntity<SensorResource> getDocument(@PathVariable Long sensorId){
        var getSensorByIdQuery = new GetSensorByIdQuery(sensorId);
        var sensor = sensorQueryService.handle(getSensorByIdQuery);
        if(sensor.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var sensorResource = SensorResourceFromEntityAssembler.toResourceFromEntity(sensor.get());
        return ResponseEntity.ok(sensorResource);
    }


    @PutMapping("/{sensorId}")
    public ResponseEntity<SensorResource> updateSensor(@PathVariable Long sensorId, @RequestBody UpdateSensorResource updateSensorResource){
        var updateSensorCommand = UpdateSensorCommandFromResourceAssembler.toCommandFromResource(sensorId, updateSensorResource);
        var updateSensor = sensorCommandService.handle(updateSensorCommand);
        if(updateSensor.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        var sensorResource = SensorResourceFromEntityAssembler.toResourceFromEntity(updateSensor.get());
        return ResponseEntity.ok(sensorResource);
    }

    @DeleteMapping("/{sensorId}")
    public ResponseEntity<?> deleteSensor(@PathVariable Long sensorId){
        var deleteSensorCommand = new DeleteSensorCommand(sensorId);
        sensorCommandService.handle(deleteSensorCommand);
        return ResponseEntity.ok("Sensor deleted successfully");
    }
}