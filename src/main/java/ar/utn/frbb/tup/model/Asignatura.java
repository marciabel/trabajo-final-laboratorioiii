package ar.utn.frbb.tup.model;

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
                "materia=" + materia +
                ", estado=" + estado +
                ", nota=" + nota +
                '}';
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public EstadoAsignatura getEstado() {
        return estado;
    }

    public void setEstado(EstadoAsignatura estado) {
        this.estado = estado;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }
}
