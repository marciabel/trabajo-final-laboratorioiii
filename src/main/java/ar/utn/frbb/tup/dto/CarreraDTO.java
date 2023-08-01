package ar.utn.frbb.tup.dto;

import ar.utn.frbb.tup.model.Materia;

import java.util.ArrayList;
import java.util.List;

public class CarreraDTO {
    private String nombre;
    private Integer idCarrera;
    private Integer departamento;
    private Integer cantidadCuatrimestres;


    @Override
    public String toString() {
        return "CarreraDTO{" +
                "nombre='" + nombre + '\'' +
                ", idCarrera=" + idCarrera +
                ", departamento=" + departamento +
                ", cantidadCuatrimestres=" + cantidadCuatrimestres +
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

}
