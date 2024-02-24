package ar.utn.frbb.tup.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Setter
@Getter
public class Carrera {
    private String nombre;
    private Integer idCarrera;
    private Integer departamento;
    private Integer cantidadCuatrimestres;
    private List<Materia> materias;

    public Carrera(String nombre, Integer idCarrera, Integer departamento, Integer cantidadCuatrimestres, List<Materia> materias) {
        this.nombre = nombre;
        this.idCarrera = idCarrera;
        this.departamento = departamento;
        this.cantidadCuatrimestres = cantidadCuatrimestres;
        this.materias = materias;
    }

    public Carrera() {
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Carrera c = (Carrera) obj;
        return Objects.equals(this.nombre, c.getNombre()) && Objects.equals(this.idCarrera, c.getIdCarrera()) && Objects.equals(this.departamento, c.getDepartamento()) && Objects.equals(this.cantidadCuatrimestres, c.getCantidadCuatrimestres());
    }

    @Override
    public String toString() {
        return "Carrera{" +
                "nombre='" + nombre + '\'' +
                ", idCarrera=" + idCarrera +
                ", departamento=" + departamento +
                ", cantidadCuatrimestres=" + cantidadCuatrimestres +
                ", materias=" + materias +
                '}';
    }

    public void agregarMateria(Materia materia) {
        if (materia instanceof Materia) {
            materias.add(materia);
        }
    }
}
