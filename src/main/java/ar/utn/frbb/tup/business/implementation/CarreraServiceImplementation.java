package ar.utn.frbb.tup.business.implementation;

import ar.utn.frbb.tup.business.CarreraService;
import ar.utn.frbb.tup.business.exception.ValorInvalidoException;
import ar.utn.frbb.tup.business.exception.NombreInvalidoException;
import ar.utn.frbb.tup.dto.CarreraDTO;
import ar.utn.frbb.tup.model.Carrera;
import ar.utn.frbb.tup.model.Materia;
import ar.utn.frbb.tup.business.exception.CantidadCuatrimestresInvalidException;
import ar.utn.frbb.tup.persistence.CarreraDao;
import ar.utn.frbb.tup.persistence.exception.CarreraAlreadyExistsException;
import ar.utn.frbb.tup.persistence.exception.CarreraNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CarreraServiceImplementation implements CarreraService {

    @Autowired
    CarreraDao carreraDao;

    @Override
    public Carrera crearCarrera(CarreraDTO carreraDTO) throws CarreraAlreadyExistsException, CantidadCuatrimestresInvalidException, NombreInvalidoException, ValorInvalidoException {

        Carrera carrera = new Carrera();

        carrera.setNombre(validarNombre(carreraDTO.getNombre()));

        carrera.setIdCarrera(carreraDTO.getIdCarrera());
        carrera.setDepartamento(validarDepartamento(carreraDTO.getDepartamento()));
        carrera.setCantidadCuatrimestres(validarCantidadCuatrimestres(carreraDTO.getCantidadCuatrimestres()));

        //Al dar de alta una carrera, la lista de materias esta vacia.
        carrera.setMaterias(new ArrayList<>());

        return carreraDao.createCarrera(carrera);
    }

    @Override
    public Carrera modificarCarrera(Integer idCarrera, Map<String, Object> atributos) throws CarreraNotFoundException, CantidadCuatrimestresInvalidException, NombreInvalidoException {
        Carrera carrera = carreraDao.getCarrera(idCarrera);

        for (Map.Entry<String, Object> atributo : atributos.entrySet()) {
            String nombreAtributo = atributo.getKey();
            Object valor = atributo.getValue();
            modificarAtributos(nombreAtributo, valor, carrera);
        }

        carreraDao.updateCarrera(idCarrera, carrera);
        System.out.println(carrera);
        return carrera;
    }

    public void modificarAtributos(String nombreAtributo, Object value, Carrera carrera) throws CantidadCuatrimestresInvalidException, NombreInvalidoException {
        switch (nombreAtributo) {
            case "nombre" -> {
                if (value instanceof String) {
                    carrera.setNombre(validarNombre((String) value));
                }
            }
            case "departamento" -> {
                if (value instanceof Integer) {
                    carrera.setDepartamento((Integer) value);
                }
            }
            case "cantidadCuatrimestres" -> {
                if (value instanceof Integer) {
                    carrera.setCantidadCuatrimestres(validarCantidadCuatrimestres((Integer) value));
                }
            }
            case "materias" -> {
                if (value instanceof List) {
                    agregarMaterias(carrera, (List<Materia>) value);
                }
            }
        }

    }

    public void agregarMaterias(Carrera carrera, List<Materia> materias) {
        for (Materia materia: materias) {
            if (materia instanceof Materia) {
                carrera.agregarMateria(materia);
            }
        }
    }

    public String validarNombre(String nombre) throws NombreInvalidoException {
        if (nombre == null) {
            throw new NombreInvalidoException("El nombre no puede ser un valor nulo");
        }
        else if (nombre.length() < 5 || nombre.length() >=50) {
            throw new NombreInvalidoException("El nombre ingresado no es válido. Deberia tener entre 5 y 50 caracteres, y tiene: " + nombre.length() + " caracteres");
        }

        return nombre;
    }

    public Integer validarDepartamento (Integer departamento) throws ValorInvalidoException {
        if (departamento == null) {
            throw new ValorInvalidoException("El departamento no puede ser nulo.");
        }
        else if (departamento < 0) {
            throw new ValorInvalidoException("El id del departamento no puede ser negativo.");
        }
        return departamento;
    }

    public Integer validarCantidadCuatrimestres(Integer cuatrimestres) throws CantidadCuatrimestresInvalidException {
        //En esta universidad ninguna carrera puede durar mas de 6 anios o 12 cuatrimestres
        if  (cuatrimestres == null) {
            throw new CantidadCuatrimestresInvalidException("Cantidad de cuatrimestres no puede ser nulo");
        }
        else if (cuatrimestres <= 0 || cuatrimestres > 12) {
            throw new CantidadCuatrimestresInvalidException(cuatrimestres + " no es una cantidad valida de cuatrimestres");
        }
        return cuatrimestres;
    }

    @Override
    public void eliminarCarrera(Integer idCarrera) throws CarreraNotFoundException {
        carreraDao.deleteCarrera(idCarrera);
    }

    @Override
    public Carrera getCarreraById(Integer idCarrera) throws CarreraNotFoundException {
        return carreraDao.getCarrera(idCarrera);
    }


}
