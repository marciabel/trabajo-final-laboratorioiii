package ar.utn.frbb.tup.business;

import ar.utn.frbb.tup.dto.MateriaDTO;
import ar.utn.frbb.tup.model.Materia;

public interface MateriaService {
    Materia crearMateria(MateriaDTO materiaDTO);
}
