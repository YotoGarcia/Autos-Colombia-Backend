package com.autoscolombia.parqueadero.repository;

import com.autoscolombia.parqueadero.model.Celda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CeldaRepository extends JpaRepository<Celda, Long> {
    Optional<Celda> findFirstByOcupadaFalse();
    List<Celda> findByOcupadaFalse();
    Optional<Celda> findByNombre(String nombre);


}
