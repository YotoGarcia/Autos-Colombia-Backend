package com.autoscolombia.parqueadero.service;

import com.autoscolombia.parqueadero.model.Celda;
import com.autoscolombia.parqueadero.repository.CeldaRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CeldaService {

    @Autowired
    private CeldaRepository celdaRepository;

    @PostConstruct
    public void init() {
        if (celdaRepository.count() == 0) {
            for (int i = 1; i <= 20; i++) {
                Celda celda = new Celda();
                celda.setNombre("A" + i);
                celda.setOcupada(false);
                celdaRepository.save(celda);
            }
        }
    }

    public List<Celda> obtenerTodasLasCeldas() {
        return celdaRepository.findAll();
    }

    public List<Celda> obtenerCeldasDisponibles() {
        return celdaRepository.findByOcupadaFalse();
    }

    public void marcarCeldaComoOcupada(Celda celda) {
        celda.setOcupada(true);
        celdaRepository.save(celda);
    }

    public void liberarCelda(Celda celda) {
        celda.setOcupada(false);
        celdaRepository.save(celda);
    }

    public Optional<Celda> buscarPorNombre(String nombre) {
        return celdaRepository.findByNombre(nombre);
    }
}


