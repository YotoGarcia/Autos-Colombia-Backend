package com.autoscolombia.parqueadero.repository;

import com.autoscolombia.parqueadero.model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> {
    List<Pago> findByHoraSalidaBetween(LocalDateTime inicio, LocalDateTime fin);
    Optional<Pago> findTopByVehiculoPlacaOrderByHoraSalidaDesc(String placa);
    @Query("SELECT p FROM Pago p WHERE p.horaIngreso = :horaIngreso AND p.vehiculo.placa = :placa")
    Optional<Pago> findByHoraIngresoAndPlaca(@Param("horaIngreso") LocalDateTime horaIngreso, @Param("placa") String placa);





}
