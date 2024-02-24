package ar.utn.frbb.tup.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Alumno {
    private Integer idAlumno;
    private String nombre;
    private String apellido;
    private Integer dni;
    private Carrera carrera;
    private List<Asignatura> asignaturas;

    public Alumno(Integer id, String nombre, String apellido, Integer dni, List<Asignatura> asignaturas) {
        this.idAlumno = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.asignaturas = asignaturas;
    }

    public Alumno(){};

    @Override
    public int hashCode() {
        return idAlumno.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "idAlumno=" + idAlumno +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni=" + dni +
                ", asignaturas=" + asignaturas +
                '}';
    }
}
