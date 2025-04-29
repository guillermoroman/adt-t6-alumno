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

    private String email;

    @ManyToOne
    private Tutor tutor;

    public Alumno() {
    }

    public Alumno(String nombre, Tutor tutor) {
        this.nombre = nombre;
        this.tutor = tutor;
    }

    public Alumno(String nombre, String email, Tutor tutor) {
        this.nombre = nombre;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", tutor=" + tutor +
                '}';
    }
}
