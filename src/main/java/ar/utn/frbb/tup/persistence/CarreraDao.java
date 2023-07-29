package ar.utn.frbb.tup.persistence;

import ar.utn.frbb.tup.model.Carrera;

public interface CarreraDao {
    Carrera crearCarrera(Carrera carrera);

    Carrera getCarrera(Integer idCarrera);

    void deleteCarrera(Integer idCarrera);

    Carrera updateCarrera(Integer idCarrera, Carrera carrera);
}
