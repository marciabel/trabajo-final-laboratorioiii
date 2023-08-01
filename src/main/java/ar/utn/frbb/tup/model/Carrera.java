package ar.utn.frbb.tup.model;

import java.util.List;
import java.util.Objects;

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
        return Objects.equals(nombre, c.getNombre()) && Objects.equals(idCarrera, ((Carrera) obj).idCarrera);
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(Integer idCarrera) {
        this.idCarrera = idCarrera;
    }

    public Integer getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Integer departamento) {
        this.departamento = departamento;
    }

    public Integer getCantidadCuatrimestres() {
        return cantidadCuatrimestres;
    }

    public void setCantidadCuatrimestres(Integer cantidadCuatrimestres) {
        this.cantidadCuatrimestres = cantidadCuatrimestres;
    }

    public List<Materia> getMaterias() {
        return materias;
    }

    public void setMaterias(List<Materia> materias) {
        this.materias = materias;
    }


    public void agregarMateria(Materia materia) {
        if (materia instanceof Materia) {
            materias.add(materia);
        }
    }
}
