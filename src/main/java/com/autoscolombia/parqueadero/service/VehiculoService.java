package com.autoscolombia.parqueadero.service;

import com.autoscolombia.parqueadero.model.Vehiculo;
import com.autoscolombia.parqueadero.repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class VehiculoService {

    @Autowired
    private VehiculoRepository vehiculoRepository;


    public List<Vehiculo> obtenerVehiculos() {

        return vehiculoRepository.findAll();
    }


    public void registrarIngreso(String placa) {
        LocalDateTime horaIngreso = LocalDateTime.now();


        Vehiculo vehiculo = vehiculoRepository.findByPlaca(placa)
                .orElse(new Vehiculo());

        vehiculo.setPlaca(placa);
        vehiculo.setHoraIngreso(horaIngreso);

        vehiculoRepository.save(vehiculo);
    }

    public List<Vehiculo> obtenerVehiculosPorFecha(LocalDate fecha) {
        LocalDateTime inicioDelDia = fecha.atStartOfDay();
        LocalDateTime finDelDia = fecha.atTime(23, 59, 59);

        return vehiculoRepository.findByHoraIngresoBetween(inicioDelDia, finDelDia);
    }

}



