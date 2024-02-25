package ar.utn.frbb.tup.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.function.Consumer;

@Setter
@Getter
public class Materia {
    private Integer materiaId;
    private String nombre;
    private Profesor profesor;
    private Carrera carrera;
    private Integer anio;
    private Integer cuatrimestre;
    @JsonIgnore
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
                ", anio=" + anio +
                ", cuatrimestre=" + cuatrimestre +
                ", correlatividades=" + correlatividades.size() +
                '}';
    }

}
