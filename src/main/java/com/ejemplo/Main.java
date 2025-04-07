package com.ejemplo;

import com.ejemplo.modelo.Alumno;
import com.ejemplo.modelo.Tutor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb-demo");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Tutor tutor = new Tutor("Ana Gómez");
        em.persist(tutor);

        Alumno alumno1 = new Alumno("Carlos Pérez", tutor);
        Alumno alumno2 = new Alumno("Lucía López", tutor);
        em.persist(alumno1);
        em.persist(alumno2);

        em.getTransaction().commit();

        // Recuperar y mostrar datos
        Alumno alumnoRecuperado = em.find(Alumno.class, alumno1.getId());
        System.out.println("Alumno: " + alumnoRecuperado.getNombre());
        System.out.println("Tutor: " + alumnoRecuperado.getTutor().getNombre());

        em.close();
        emf.close();
    }
}
