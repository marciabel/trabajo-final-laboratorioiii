package ar.utn.frbb.tup.dto;

import ar.utn.frbb.tup.model.EstadoAsignatura;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AsignaturaDTO {
    private EstadoAsignatura estado;
    private Integer nota;

    public AsignaturaDTO(EstadoAsignatura estado, Integer nota) {
        this.estado = estado;
        this.nota = nota;
    }

    public AsignaturaDTO() {
    }
}
