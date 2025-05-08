package com.autoscolombia.parqueadero.repository;

import com.autoscolombia.parqueadero.model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> {
    List<Pago> findByVehiculoId(Long vehiculoId);

}
