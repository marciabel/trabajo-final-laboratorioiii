package ar.utn.frbb.tup.business;

import ar.utn.frbb.tup.model.Asignatura;

import java.util.List;

public interface AsignaturaService {
    Asignatura getAsignaturaByName(List<Asignatura> listaAsignaturas, String nombreAsignatura);
}
