package com.autoscolombia.parqueadero.repository;

import com.autoscolombia.parqueadero.model.Ingreso;
import com.autoscolombia.parqueadero.model.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IngresoRepository extends JpaRepository<Ingreso, Long> {
    Optional<Ingreso> findTopByPlacaOrderByHoraIngresoDesc(String placa);

}
