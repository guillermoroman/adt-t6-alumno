package com.ejemplo;

import com.ejemplo.modelo.Alumno;
import com.ejemplo.modelo.Tutor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jdk.swing.interop.SwingInterOpUtils;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb-demo");
        EntityManager em = emf.createEntityManager();

        // pruebaInicial(em);

        // mostrarTodosLosAlumnos(em);

        // crearAlumnoConCorreo(em);

        // mostrarTodosLosAlumnos(em);

        // pruebaTransaccionFallida(em);

        // mostrarTodosLosAlumnos(em);

        // pruebaTransaccionExito(em);

        mostrarTodosLosAlumnos(em);

        // crearDatosDePrueba(em);

        // consultarAlumnosOrdenados(em);

        // consultarAlumnosConEmail(em);

        // buscarAlumnosPorNombre(em, "Lucía");

        // buscarAlumnosPorTutor (em, "Ana");

        // contarAlumnos(em);

        // mostrarAlumnosDeCadaTutor(em);

        mostrarPaginaAlumnos(em, 1, 4);


        em.close();
        emf.close();


    }

    private static void mostrarPaginaAlumnos(EntityManager em, int pagina, int tamanioPagina) {
        List<Alumno> alumnos = em.createQuery("SELECT a FROM Alumno a", Alumno.class)
                .setFirstResult(pagina*tamanioPagina)
                .setMaxResults(tamanioPagina)
                .getResultList();

        System.out.println("Páginas:" + pagina + ":");
        alumnos.forEach(System.out::println);
        System.out.println();
    }

    private static void mostrarAlumnosDeCadaTutor(EntityManager em) {
        List<Tutor> tutores = em.createQuery("SELECT t FROM Tutor t", Tutor.class).getResultList();

        System.out.println("Tutores y sus alumnos:");
        System.out.println();

        for (Tutor t: tutores){
            System.out.println("Tutor: " + t.getNombre());
            List<Alumno> alumnos = t.getAlumnos();
            for (Alumno a: alumnos){
                System.out.println("  -Alumno: "+ a.getNombre());
            }
        }


        System.out.println();
    }

    private static void contarAlumnos(EntityManager em) {
        Long total = em.createQuery("SELECT COUNT(a) FROM Alumno a", Long.class).getSingleResult();

        System.out.println("Total de alumnos: " + total);

    }

    private static void buscarAlumnosPorTutor(EntityManager em, String palabra) {
        List<Alumno> alumnos = em.createQuery("SELECT a FROM Alumno a WHERE LOWER(a.tutor.nombre) LIKE :nombreTutor", Alumno.class)
                .setParameter("nombreTutor", "%" + palabra.toLowerCase() + "%" )
                .getResultList();

        System.out.println("Alumnos del tutor:" + palabra + ":");
        alumnos.forEach(System.out::println);
        System.out.println();
    }

    private static void buscarAlumnosPorNombre(EntityManager em, String palabra) {
        List<Alumno> alumnos = em.createQuery("SELECT a FROM Alumno a WHERE LOWER(a.nombre) LIKE :nombre", Alumno.class)
                .setParameter("nombre", "%" + palabra.toLowerCase() + "%" )
                .getResultList();

        System.out.println("Alumnos que contienen:" + palabra + " en su nombre");
        alumnos.forEach(System.out::println);
        System.out.println();
    }

    private static void consultarAlumnosConEmail(EntityManager em) {
        List<Alumno> alumnos = em.createQuery("SELECT a FROM Alumno a WHERE a.email IS NOT NULL", Alumno.class).getResultList();

        System.out.println("Alumnos con correo:");
        alumnos.forEach(System.out::println);
        System.out.println();
    }

    private static void consultarAlumnosOrdenados(EntityManager em) {
        List<Alumno> alumnos = em.createQuery("SELECT a FROM Alumno a ORDER BY a.nombre ASC", Alumno.class).getResultList();

        System.out.println("Alumnos ordenadoS por nombe:");
        alumnos.forEach(System.out::println);
        System.out.println();
    }

    private static void crearDatosDePrueba(EntityManager em) {
        em.getTransaction().begin();
        // Crear tutores
        Tutor tutor1 = new Tutor("Ana Torres");
        Tutor tutor2 = new Tutor("Luis Fernández");

        // Persistir tutores
        em.persist(tutor1);
        em.persist(tutor2);

        // Crear alumnos
        Alumno alumno1 = new Alumno ("Paula Gómez", "paula@example.com", tutor1);
        Alumno alumno2 = new Alumno ("Mario Caldas", "mario@example.com", tutor1);

        Alumno alumno3 = new Alumno ("Lucía Navarro", "lucia@example.com", tutor2);
        Alumno alumno4 = new Alumno ("David Montes", "david@example.com", tutor2);
        Alumno alumno5 = new Alumno ("Sandra Blanco", "sandra@example.com", tutor2);

        //Persistir alumnos
        em.persist(alumno1);
        em.persist(alumno2);
        em.persist(alumno3);
        em.persist(alumno4);
        em.persist(alumno5);


        em.getTransaction().commit();

    }

    private static void pruebaInicial(EntityManager em) {
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
    }


    private static void crearAlumnoConCorreo(EntityManager em) {

        Alumno alumno = new Alumno("Fernando", "fer@gmail.com", null);

        em.getTransaction().begin();

        em.persist(alumno);

        em.getTransaction().commit();
    }

    public static void mostrarTodosLosAlumnos(EntityManager em) {
        List<Alumno> alumnos = em.createQuery("SELECT a FROM Alumno a", Alumno.class).getResultList();

        System.out.println("Lista de alumnos:");
        for (Alumno a: alumnos) {
            System.out.println(" - " + a);
        }
        System.out.println();
    }

    private static void pruebaTransaccionFallida(EntityManager em) {
        try{
            Tutor tutor = new Tutor("Mario Gómez");
            em.persist(tutor);

            Alumno alumno = new Alumno ("Jaime Salas", "jaime@ejemplo.com", tutor);
            em.persist(alumno);

            // Error intencional
            if (true) {
                throw new RuntimeException("Error simulando la transferencia");
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error capturado: " + e.getMessage());
            em.getTransaction().rollback();
            System.out.println("Transacción revertida correctamente.");
        }
    }

    private static void pruebaTransaccionExito(EntityManager em) {
        em.getTransaction().begin();

        Tutor tutor = new Tutor("Mario Gómez");
        em.persist(tutor);

        Alumno alumno = new Alumno ("Jaime Salas", "jaime@ejemplo.com", tutor);
        em.persist(alumno);

        em.getTransaction().commit();
        System.out.println("Transacción realizada con éxito");

    }
}
