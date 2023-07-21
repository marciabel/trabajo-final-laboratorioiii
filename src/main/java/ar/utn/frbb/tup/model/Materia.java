package ar.utn.frbb.tup.model;

import java.util.List;

public class Materia {
    private Integer materiaId;
    private String nombre;
    private Profesor profesor;
    private Carrera carrera;
    private Integer anio;
    private Integer cuatrimestre;
    private List<Materia> correlatividades;

    public Materia(Integer materiaId, String nombre, Profesor profesor, Carrera carrera, Integer anio, Integer cuatrimestre, List<Materia> correlatividades) {
        this.materiaId = materiaId;
        this.nombre = nombre;
        this.profesor = profesor;
        this.carrera = carrera;
        this.anio = anio;
        this.cuatrimestre = cuatrimestre;
        this.correlatividades = correlatividades;
    }

    public Materia() {
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "Materia{" +
                "materiaId=" + materiaId +
                ", nombre='" + nombre + '\'' +
                ", profesor=" + profesor +
                ", carrera=" + carrera +
                ", anio=" + anio +
                ", cuatrimestre=" + cuatrimestre +
                ", correlatividades=" + correlatividades +
                '}';
    }

    public Integer getMateriaId() {
        return materiaId;
    }

    public void setMateriaId(Integer materiaId) {
        this.materiaId = materiaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Integer getCuatrimestre() {
        return cuatrimestre;
    }

    public void setCuatrimestre(Integer cuatrimestre) {
        this.cuatrimestre = cuatrimestre;
    }

    public List<Materia> getCorrelatividades() {
        return correlatividades;
    }

    public void setCorrelatividades(List<Materia> correlatividades) {
        this.correlatividades = correlatividades;
    }
}
