package ar.utn.frbb.tup.model;

import java.util.List;

public class Carrera {
    private Integer idCarrera;
    private Integer departamento;
    private Integer cantidadCuatrimestres;
    private List<Materia> materias;

    public Carrera(Integer idCarrera, Integer departamento, Integer cantidadCuatrimestres, List<Materia> materias) {
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
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "Carrera{" +
                "idCarrera=" + idCarrera +
                ", departamento=" + departamento +
                ", cantidadCuatrimestres=" + cantidadCuatrimestres +
                ", materias=" + materias +
                '}';
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

        //En esta universidad ninguna carrera puede durar mas de 6 anios o 12 cuatrimestres
        if (cantidadCuatrimestres <= 0 || cantidadCuatrimestres > 12) {
            //throw new CantidadCuatrimestresInvalidaException();
        }
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
