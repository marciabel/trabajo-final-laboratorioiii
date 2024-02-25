package ar.utn.frbb.tup.business;

import ar.utn.frbb.tup.business.exception.CantidadCuatrimestresInvalidException;
import ar.utn.frbb.tup.business.exception.MateriaNoExisteException;
import ar.utn.frbb.tup.business.exception.NombreInvalidoException;
import ar.utn.frbb.tup.business.exception.ValorInvalidoException;
import ar.utn.frbb.tup.dto.MateriaDTO;
import ar.utn.frbb.tup.model.Materia;
import ar.utn.frbb.tup.persistence.exception.CarreraNotFoundException;
import ar.utn.frbb.tup.persistence.exception.MateriaAlreadyExistsException;

import java.util.List;
import java.util.Map;

public interface MateriaService {
    Materia crearMateria(MateriaDTO materiaDTO) throws CarreraNotFoundException, NombreInvalidoException, ValorInvalidoException, MateriaAlreadyExistsException, CantidadCuatrimestresInvalidException, MateriaNoExisteException;

    String eliminarMateria(Integer idMateria) throws MateriaNoExisteException;

    Materia modificarMateria(Integer idMateria, Map<String, Object> campos) throws CarreraNotFoundException;

    List<Materia> buscarPorNombre(String nombre) throws MateriaNoExisteException;

    List<Materia> listarTodasLasMaterias();

    List<Materia> listarMateriasOrdenadas(String order);

    Materia getMateriaById(Integer idMateria) throws ValorInvalidoException;
}
