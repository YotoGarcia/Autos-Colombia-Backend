package com.autoscolombia.parqueadero.controller;

import com.autoscolombia.parqueadero.model.Vehiculo;
import com.autoscolombia.parqueadero.service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehiculos")
@CrossOrigin(origins = "*")
public class VehiculoController {

    @Autowired
    private VehiculoService vehiculoService;

    @GetMapping
    public ResponseEntity<List<Vehiculo>> getVehiculos() {
        List<Vehiculo> vehiculos = vehiculoService.obtenerVehiculos();
        return ResponseEntity.ok(vehiculos);
    }

}













