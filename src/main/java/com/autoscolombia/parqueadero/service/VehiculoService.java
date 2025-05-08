package com.autoscolombia.parqueadero.service;

import com.autoscolombia.parqueadero.model.Vehiculo;
import com.autoscolombia.parqueadero.repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VehiculoService {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    // Obtener todos los vehículos
    public List<Vehiculo> obtenerVehiculos() {
        return vehiculoRepository.findAll();
    }

    // Registrar hora de ingreso
    public void registrarIngreso(String placa) {
        LocalDateTime horaIngreso = LocalDateTime.now();

        // Verificar si el vehículo ya existe
        Vehiculo vehiculo = vehiculoRepository.findByPlaca(placa)
                .orElse(new Vehiculo());

        vehiculo.setPlaca(placa);
        vehiculo.setHoraIngreso(horaIngreso);

        vehiculoRepository.save(vehiculo);
    }
}



