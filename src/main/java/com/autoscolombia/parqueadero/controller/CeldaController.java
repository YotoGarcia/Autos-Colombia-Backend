package com.autoscolombia.parqueadero.controller;

import com.autoscolombia.parqueadero.model.Celda;
import com.autoscolombia.parqueadero.service.CeldaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/celdas")
@CrossOrigin(origins = "*")
public class CeldaController {

    @Autowired
    private CeldaService celdaService;

    @GetMapping
    public ResponseEntity<List<Celda>> obtenerCeldas() {
        List<Celda> celdas = celdaService.obtenerTodasLasCeldas();
        if (celdas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(celdas);
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<Celda>> obtenerCeldasDisponibles() {
        List<Celda> celdasDisponibles = celdaService.obtenerCeldasDisponibles();
        if (celdasDisponibles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(celdasDisponibles);
    }
}



