package com.autoscolombia.parqueadero.model;

import jakarta.persistence.*;

@Entity
public class Celda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;
    private boolean ocupada;

    public Celda() {
    }

    public Celda(Long id, String nombre, boolean ocupada) {
        this.id = id;
        this.nombre = nombre;
        this.ocupada = ocupada;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }
}
