package com.autoscolombia.parqueadero.service;

import com.autoscolombia.parqueadero.model.Celda;
import com.autoscolombia.parqueadero.model.Ingreso;
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

        Vehiculo vehiculo = vehiculoRepository.findByPlaca(placa)
                .orElseGet(() -> {
                    Vehiculo nuevo = new Vehiculo();
                    nuevo.setPlaca(placa);
                    nuevo.setHoraIngreso(LocalDateTime.now());
                    return vehiculoRepository.save(nuevo);
                });


        if (vehiculo.getHoraIngreso() == null) {
            vehiculo.setHoraIngreso(LocalDateTime.now());
            vehiculoRepository.save(vehiculo);
        }


        Celda celdaDisponible = celdaRepository.findFirstByOcupadaFalse()
                .orElseThrow(() -> new RuntimeException("No hay celdas disponibles"));


        celdaDisponible.setOcupada(true);
        celdaRepository.save(celdaDisponible);


        Ingreso ingreso = new Ingreso();
        ingreso.setPlaca(placa);
        ingreso.setCelda(celdaDisponible);
        ingreso.setHoraIngreso(LocalDateTime.now());


        return ingresoRepository.save(ingreso);
    }
}
