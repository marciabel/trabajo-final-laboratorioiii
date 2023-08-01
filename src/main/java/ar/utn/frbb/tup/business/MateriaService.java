package ar.utn.frbb.tup.business;

import ar.utn.frbb.tup.dto.MateriaDTO;
import ar.utn.frbb.tup.model.Materia;
import ar.utn.frbb.tup.persistence.exception.CarreraNotFoundException;

public interface MateriaService {
    Materia crearMateria(MateriaDTO materiaDTO) throws CarreraNotFoundException;
}
