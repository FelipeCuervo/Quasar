package com.quasar.controller;

import com.quasar.entities.Position;
import com.quasar.entities.Satellite;
import com.quasar.entities.SatelliteList;
import com.quasar.entities.SatelliteOutput;
import com.quasar.services.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class QuasarController {

    @Autowired
    private Service service;


    @PostMapping(path = "/topsecret")
    public ResponseEntity topSecret(@RequestBody SatelliteList satelliteList) throws ResponseStatusException {
        try {
            if (satelliteList.getSatellites().size() < 3) {
                throw new Exception("Se necesitan al menos 3 satélites para determinar con precisión la ubicación");
            }
            Position position = service.getPosition(satelliteList.getSatellites());
            String message = service.getMessage(satelliteList.obtainMessages());
            SatelliteOutput satelliteOutput = new SatelliteOutput(position,message);
            return ResponseEntity.status(HttpStatus.OK).body(satelliteOutput);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se logró decodificar por completo el mensaje");
        }
    }

    @PostMapping("/topsecret_split")
    public void createTopSecretMessageSplit(@RequestBody Satellite satellite, @RequestParam(name = "satellite_name") String satelliteName) {

        Satellite satellite1 = new Satellite(satelliteName,satellite.getDistance(), satellite.getMessage());
        service.saveSatellite(satellite1);

    }



    @GetMapping("/topsecret_split")
    public ResponseEntity getSatelliteTopSplit(@RequestParam(name = "satellite_name") String satellite_name) {

        Satellite satellite = service.getSatellite(satellite_name);

        return ResponseEntity.status(HttpStatus.OK).body(satellite.toString());
    }









}
