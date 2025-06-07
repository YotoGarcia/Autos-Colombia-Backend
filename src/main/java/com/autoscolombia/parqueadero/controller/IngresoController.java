package com.autoscolombia.parqueadero.controller;

import com.autoscolombia.parqueadero.model.Ingreso;
import com.autoscolombia.parqueadero.service.FacturaService;
import com.autoscolombia.parqueadero.service.IngresoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ingresos")
public class IngresoController {

    @Autowired
    private IngresoService ingresoService;

    @Autowired
    private FacturaService facturaService;

    @PostMapping
    public ResponseEntity<Ingreso> registrarIngreso(@RequestParam String placa) {
        try {

            Ingreso ingreso = ingresoService.registrarIngreso(placa);


            return ResponseEntity.status(HttpStatus.CREATED).body(ingreso);
        } catch (RuntimeException e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/factura")
    public ResponseEntity<byte[]> obtenerFacturaIngreso(@RequestParam String placa) {
        Ingreso ingreso = ingresoService.buscarIngresoPorPlaca(placa)
                .orElseThrow(() -> new RuntimeException("Ingreso no encontrado"));

        byte[] pdf = facturaService.generarFacturaIngresoPDF(ingreso);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.inline().filename("factura_ingreso.pdf").build());

        return new ResponseEntity<>(pdf, headers, HttpStatus.OK);
    }


}

