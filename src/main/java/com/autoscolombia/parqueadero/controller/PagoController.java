package com.autoscolombia.parqueadero.controller;

import com.autoscolombia.parqueadero.service.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/pagos")
@CrossOrigin(origins = "*")
public class PagoController {

    @Autowired
    private PagoService pagoService;


    @PostMapping("/registrarPago")
    public ResponseEntity<Double> registrarPago(@RequestParam String placa) {
        try {
            double valor = pagoService.registrarPago(placa);
            return ResponseEntity.ok(valor);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

}

