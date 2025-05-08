package com.autoscolombia.parqueadero.controller;

import com.autoscolombia.parqueadero.model.Ingreso;
import com.autoscolombia.parqueadero.service.IngresoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ingresos")
@CrossOrigin(origins = "*")
public class IngresoController {

    @Autowired
    private IngresoService ingresoService;

    @PostMapping
    public ResponseEntity<Ingreso> registrarIngreso(@RequestParam String placa) {
        try {

            Ingreso ingreso = ingresoService.registrarIngreso(placa);


            return ResponseEntity.status(HttpStatus.CREATED).body(ingreso);
        } catch (RuntimeException e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}

