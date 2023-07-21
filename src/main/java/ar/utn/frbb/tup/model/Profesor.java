package ar.utn.frbb.tup.model;

import java.util.List;

public class Profesor {
    private Integer idProfesor;
    private String nombre;
    private String apellido;
    private String titulo;
    private List<Materia> materiasDictadas;

    public Profesor(Integer idProfesor, String nombre, String apellido, String titulo, List<Materia> materiasDictadas) {
        this.idProfesor = idProfesor;
        this.nombre = nombre;
        this.apellido = apellido;
        this.titulo = titulo;
        this.materiasDictadas = materiasDictadas;
    }

    public Profesor() {
    }

    @Override
    public int hashCode() {
        return idProfesor.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "Profesor{" +
                "idProfesor=" + idProfesor +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", titulo='" + titulo + '\'' +
                ", materiasDictadas=" + materiasDictadas +
                '}';
    }

    public Integer getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(Integer idProfesor) {
        this.idProfesor = idProfesor;
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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Materia> getMateriasDictadas() {
        return materiasDictadas;
    }

    public void setMateriasDictadas(List<Materia> materiasDictadas) {
        this.materiasDictadas = materiasDictadas;
    }
}
