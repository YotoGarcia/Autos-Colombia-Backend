package com.autoscolombia.parqueadero.service;

import com.autoscolombia.parqueadero.model.Celda;
import com.autoscolombia.parqueadero.model.Ingreso;
import com.autoscolombia.parqueadero.model.Pago;
import com.autoscolombia.parqueadero.model.Vehiculo;
import com.autoscolombia.parqueadero.repository.CeldaRepository;
import com.autoscolombia.parqueadero.repository.IngresoRepository;
import com.autoscolombia.parqueadero.repository.PagoRepository;
import com.autoscolombia.parqueadero.repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class IngresoService {

    @Autowired
    private IngresoRepository ingresoRepository;

    @Autowired
    private CeldaRepository celdaRepository;

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Autowired
    private PagoRepository pagoRepository;

    public Ingreso registrarIngreso(String placa) {
        // Verifica si el vehículo ya existe o crea uno nuevo
        Vehiculo vehiculo = vehiculoRepository.findByPlaca(placa)
                .orElseGet(() -> {
                    Vehiculo nuevo = new Vehiculo();
                    nuevo.setPlaca(placa);
                    nuevo.setHoraIngreso(LocalDateTime.now());
                    return vehiculoRepository.save(nuevo);
                });

        // Si no tiene hora de ingreso, la asigna
        if (vehiculo.getHoraIngreso() == null) {
            vehiculo.setHoraIngreso(LocalDateTime.now());
            vehiculoRepository.save(vehiculo);
        }

        // Obtiene una celda disponible
        Celda celdaDisponible = celdaRepository.findFirstByOcupadaFalse()
                .orElseThrow(() -> new RuntimeException("No hay celdas disponibles"));

        // Marca la celda como ocupada
        celdaDisponible.setOcupada(true);
        celdaRepository.save(celdaDisponible);

        // Crea un nuevo ingreso
        Ingreso ingreso = new Ingreso();
        ingreso.setPlaca(placa);
        ingreso.setCelda(celdaDisponible);
        ingreso.setHoraIngreso(LocalDateTime.now());

        // Guarda el ingreso y lo retorna
        return ingresoRepository.save(ingreso);
    }
}
