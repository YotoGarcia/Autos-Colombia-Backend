package com.autoscolombia.parqueadero.service;

import com.autoscolombia.parqueadero.model.Celda;
import com.autoscolombia.parqueadero.model.Ingreso;
import com.autoscolombia.parqueadero.model.Pago;
import com.autoscolombia.parqueadero.repository.CeldaRepository;
import com.autoscolombia.parqueadero.repository.IngresoRepository;
import com.autoscolombia.parqueadero.repository.PagoRepository;
import com.autoscolombia.parqueadero.repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private IngresoRepository ingresoRepository;

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Autowired
    private CeldaRepository celdaRepository;

    public double registrarPago(String placa) {
        Ingreso ingreso = ingresoRepository.findTopByPlacaOrderByHoraIngresoDesc(placa)
                .orElseThrow(() -> new RuntimeException("No se encontró ingreso para la placa: " + placa));

        Optional<Pago> pagoExistente = pagoRepository.findByHoraIngresoAndPlaca(ingreso.getHoraIngreso(), placa);
        if (pagoExistente.isPresent()){
            throw new RuntimeException("Este vehiculo ya tiene un pago registrado para este ingreso");
        }

        LocalDateTime horaSalida = LocalDateTime.now();
        long horas = java.time.Duration.between(ingreso.getHoraIngreso(), horaSalida).toHours();
        if (horas == 0) horas = 1;

        double tarifaPorHora = 3000;
        double valor = horas * tarifaPorHora;

        Pago pago = new Pago();
        pago.setVehiculo((vehiculoRepository.findByPlaca(placa).orElse(null)));
        pago.setCelda(ingreso.getCelda());
        pago.setHoraIngreso(ingreso.getHoraIngreso());
        pago.setHoraSalida(horaSalida);
        pago.setValorPagado(valor);
        pagoRepository.save(pago);

        Celda celda = ingreso.getCelda();
        celda.setOcupada(false);
        celdaRepository.save(celda);

        return valor;
    }
    public List<Pago> obtenerPagosPorFecha(LocalDate fecha) {
        LocalDateTime inicio = fecha.atStartOfDay();
        LocalDateTime fin = fecha.atTime(23, 59, 59);
        return pagoRepository.findByHoraSalidaBetween(inicio, fin);
    }

    public Optional<Pago> obtenerUltimoPagoPorPlaca(String placa) {
        return pagoRepository.findTopByVehiculoPlacaOrderByHoraSalidaDesc(placa);
    }

}
