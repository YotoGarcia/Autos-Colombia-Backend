package com.autoscolombia.parqueadero.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vehiculo_id", nullable = false)
    private Vehiculo vehiculo;

    @ManyToOne
    @JoinColumn(name = "celda_id", nullable = false)
    private Celda celda;

    @Column(nullable = false)
    private LocalDateTime horaIngreso;

    @Column(nullable = false)
    private LocalDateTime horaSalida;

    @Column(nullable = false)
    private double valorPagado;

    public Pago() {
    }

    public Pago(Vehiculo vehiculo, Celda celda, LocalDateTime horaIngreso, LocalDateTime horaSalida, double valorPagado) {
        this.vehiculo = vehiculo;
        this.celda = celda;
        this.horaIngreso = horaIngreso;
        this.horaSalida = horaSalida;
        this.valorPagado = valorPagado;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Celda getCelda() {
        return celda;
    }

    public void setCelda(Celda celda) {
        this.celda = celda;
    }

    public LocalDateTime getHoraIngreso() {
        return horaIngreso;
    }

    public void setHoraIngreso(LocalDateTime horaIngreso) {
        this.horaIngreso = horaIngreso;
    }

    public LocalDateTime getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(LocalDateTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    public double getValorPagado() {
        return valorPagado;
    }

    public void setValorPagado(double valorPagado) {
        this.valorPagado = valorPagado;
    }
}

