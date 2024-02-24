package ar.utn.frbb.tup.dto;

import ar.utn.frbb.tup.model.Carrera;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AlumnoDto {
    private Integer idAlumno;
    private String nombre;
    private String apellido;
    private Integer dni;
    private Carrera carrera;

    @Override
    public int hashCode() {
        return this.idAlumno;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "AlumnoDto{" +
                "idAlumno=" + idAlumno +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni=" + dni +
                ", carrera=" + carrera +
                '}';
    }
}
