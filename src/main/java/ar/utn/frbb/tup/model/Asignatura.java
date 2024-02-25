package ar.utn.frbb.tup.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Asignatura {
    private Materia materia;
    private EstadoAsignatura estado;
    private Integer nota;


    public Asignatura(Materia materia, EstadoAsignatura estado, Integer nota) {
        this.materia = materia;
        this.estado = estado;
        this.nota = nota;
    }

    public Asignatura() {
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
        return "Asignatura{" +
                "materia=" + materia.getNombre() +
                ", estado=" + estado +
                ", nota=" + nota +
                '}';
    }
}
