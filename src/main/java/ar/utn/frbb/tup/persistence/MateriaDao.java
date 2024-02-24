package ar.utn.frbb.tup.persistence;

import ar.utn.frbb.tup.model.Materia;
import ar.utn.frbb.tup.persistence.exception.MateriaAlreadyExistsException;

import java.util.List;

public interface MateriaDao {
    void createMateria(Materia m) throws MateriaAlreadyExistsException;

    Materia updateMateria(Integer idMateria, Materia materia);

    void deleteMateria(Integer idMateria);

    Materia getMateriaById(Integer idMateria);

    List<Materia> getMateriaByName(String nombre);

    List<Materia> getAllMaterias();
}
