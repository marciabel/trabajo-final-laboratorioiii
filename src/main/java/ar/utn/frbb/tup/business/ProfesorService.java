package ar.utn.frbb.tup.business;

import ar.utn.frbb.tup.model.Profesor;

import java.util.Optional;

public interface ProfesorService {
    Profesor getProfesorById(Integer idPofesor);
}
