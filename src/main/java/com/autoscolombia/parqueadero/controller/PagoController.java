package com.autoscolombia.parqueadero.controller;

import com.autoscolombia.parqueadero.model.Pago;
import com.autoscolombia.parqueadero.service.FacturaService;
import com.autoscolombia.parqueadero.service.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pagos")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @Autowired
    private FacturaService facturaService;


    @PostMapping("/registrarPago")
    public ResponseEntity<Double> registrarPago(@RequestParam String placa) {
        try {
            double valor = pagoService.registrarPago(placa);
            return ResponseEntity.ok(valor);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/fecha")
    public List<Pago> obtenerPagosPorFecha(@RequestParam("fecha") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        return pagoService.obtenerPagosPorFecha(fecha);
    }

    @GetMapping("/factura")
    public ResponseEntity<byte[]> obtenerFactura(@RequestParam String placa) {
        Optional<Pago> ultimoPago = pagoService.obtenerUltimoPagoPorPlaca(placa);

        if (ultimoPago.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        byte[] pdf = facturaService.generarFacturaPDF(ultimoPago.get());

        return ResponseEntity.ok()
                .header("Content-Type", "application/pdf")
                .header("Content-Disposition", "inline; filename=factura.pdf")
                .body(pdf);
    }

}

