package com.autoscolombia.parqueadero.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Ingreso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String placa;

    @Column(nullable = false)
    private LocalDateTime horaIngreso;

    @ManyToOne
    @JoinColumn(name = "celdaId")
    private Celda celda;

    @ManyToOne
    @JoinColumn(name = "vehiculo_id")
    private Vehiculo vehiculo;

    public Ingreso() {
    }

    public Ingreso(Long id, String placa, LocalDateTime horaIngreso, Celda celda) {
        this.id = id;
        this.placa = placa;
        this.horaIngreso = horaIngreso;
        this.celda = celda;


    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }
}
