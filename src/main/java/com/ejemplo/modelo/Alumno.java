package com.ejemplo.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Alumno {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;

    @ManyToOne
    private Tutor tutor;

    public Alumno() {
    }

    public Alumno(String nombre, Tutor tutor) {
        this.nombre = nombre;
        this.tutor = tutor;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }
}
