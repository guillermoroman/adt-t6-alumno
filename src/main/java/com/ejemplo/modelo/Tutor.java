package com.ejemplo.modelo;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Tutor {
    @Id
    @GeneratedValue
    private Long id;

    private String nombre;

    @OneToMany(mappedBy = "tutor", cascade = CascadeType.ALL)
    private List<Alumno> alumnos = new ArrayList<>();

    public Tutor() {
    }

    public Tutor(String nombre) {
        this.nombre = nombre;
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

    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    @Override
    public String toString() {
        return "Tutor{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
