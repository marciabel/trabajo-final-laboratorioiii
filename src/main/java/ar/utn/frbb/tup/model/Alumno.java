package ar.utn.frbb.tup.model;

import java.util.List;

public class Alumno {
    private Integer idAlumno;
    private String nombre;
    private String apellido;
    private Integer dni;
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

    public Integer getId() {
        return idAlumno;
    }

    public void setId(Integer id) {
        this.idAlumno = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }

    public List<Asignatura> getAsignaturas() {
        return asignaturas;
    }

    public void setAsignaturas(List<Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
    }
}
