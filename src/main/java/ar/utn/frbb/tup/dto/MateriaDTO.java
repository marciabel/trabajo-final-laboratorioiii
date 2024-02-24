package ar.utn.frbb.tup.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

@Setter
@Getter
public class MateriaDTO {
    private Integer materiaId;
    private String nombre;
    private Integer idPofesor;
    private Integer idCarrera;
    private Integer anio;
    private Integer cuatrimestre;

    private List<Integer> idCorrelatividades;

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

    public Optional<Integer> getIdPofesor() {
        Integer id = this.idPofesor;
        return Optional.ofNullable(id);
    }

    public void setIdPofesor(Integer idPofesor) {
        this.idPofesor = idPofesor;
    }

    public Integer getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(Integer idCarrera) {
        this.idCarrera = idCarrera;
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

    public List<Integer> getIdCorrelatividades() {
        return idCorrelatividades;
    }

    public void setIdCorrelatividades(List<Integer> idCorrelatividades) {
        this.idCorrelatividades = idCorrelatividades;
    }
}
