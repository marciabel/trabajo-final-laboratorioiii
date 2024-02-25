package ar.utn.frbb.tup.business.implementation;

import ar.utn.frbb.tup.business.CarreraService;
import ar.utn.frbb.tup.business.MateriaService;
import ar.utn.frbb.tup.business.ProfesorService;
import ar.utn.frbb.tup.business.Validaciones;
import ar.utn.frbb.tup.business.exception.CantidadCuatrimestresInvalidException;
import ar.utn.frbb.tup.business.exception.MateriaNoExisteException;
import ar.utn.frbb.tup.business.exception.NombreInvalidoException;
import ar.utn.frbb.tup.business.exception.ValorInvalidoException;
import ar.utn.frbb.tup.dto.MateriaDTO;
import ar.utn.frbb.tup.model.Carrera;
import ar.utn.frbb.tup.model.Materia;
import ar.utn.frbb.tup.model.Profesor;
import ar.utn.frbb.tup.persistence.MateriaDao;
import ar.utn.frbb.tup.persistence.exception.CarreraNotFoundException;
import ar.utn.frbb.tup.persistence.exception.MateriaAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Service
public class MateriaServiceImplementation implements MateriaService {

    @Autowired
    MateriaDao materiaDao;

    @Autowired
    ProfesorService profesorService;

    @Autowired
    CarreraService carreraService;

    @Autowired
    Validaciones validaciones;

    @Override
    public Materia crearMateria(MateriaDTO materiaDTO) throws CarreraNotFoundException, NombreInvalidoException, ValorInvalidoException, MateriaAlreadyExistsException, CantidadCuatrimestresInvalidException, MateriaNoExisteException {
        Materia m = new Materia();

        //Idealmente este campo sería auto-incremental y no debería poder ingresarse a mano
        m.setMateriaId(validaciones.validarNumeroPositivo(materiaDTO.getMateriaId(), "Materia Id"));

        m.setNombre(validaciones.validarNombre(materiaDTO.getNombre()));

        //-----------------------------------------//
        /* Podría utilizar otro metodo u otro service que busque el profesor a partir del id*/

        materiaDTO.getIdPofesor().ifPresent(id -> {
            Profesor profesor = profesorService.getProfesorById(id);
            m.setProfesor(profesor);
        });

        /*Lo mismo con la carrera*/
        Carrera carrera = carreraService.getCarreraById((materiaDTO.getIdCarrera()));
        m.setCarrera(carrera);
        //-----------------------------------------//

        m.setAnio(validaciones.validarNumeroPositivo(materiaDTO.getAnio(), "Año"));

        //El cuatrimestre en que se dicta la materia debe ser positivo
        //y tiene que condecir con la cantidad de cuatrimestres que tiene la carrera
        m.setCuatrimestre(cuatrimestreValido(materiaDTO.getCuatrimestre(), carrera.getCantidadCuatrimestres()));

        //Inicializar la lista de correlatividades
        if (materiaDTO.getIdCorrelatividades().size() > 0) {
            List<Materia> materiasCorrelativas = new ArrayList<Materia>();
            for (Integer idMateria: materiaDTO.getIdCorrelatividades()) {
                Materia materiaCorrelativa = materiaDao.getMateriaById(idMateria);
                if (materiaCorrelativa == null) {
                    throw new MateriaNoExisteException("El id " + idMateria + " que se ingreso en las correlativas no existe");
                }
                materiasCorrelativas.add(materiaCorrelativa);
            }
            m.setCorrelatividades(materiasCorrelativas);
        }
        else {
            m.setCorrelatividades(new ArrayList<Materia>());
        }

        materiaDao.createMateria(m);

        return m;
    }

    @Override
    public Materia modificarMateria(Integer idMateria, Map<String, Object> atributos) {
        Materia materia = materiaDao.getMateriaById(idMateria);

        for (Map.Entry<String, Object> atributo : atributos.entrySet()) {
            String nombreAtributo = atributo.getKey();
            Object valor = atributo.getValue();
            //modificarAtributos(nombreAtributo, valor, carrera);
        }

        return null;
    }

    @Override
    public List<Materia> buscarPorNombre(String nombre) throws MateriaNoExisteException {
        if (materiaDao.getMateriaByName(nombre).isEmpty()) {
            throw new MateriaNoExisteException("No existen materias para el filtro ingresado");
        }
        return materiaDao.getMateriaByName(nombre);
    }

    @Override
    public List<Materia> listarTodasLasMaterias() {
        return materiaDao.getAllMaterias();
    }

    @Override
    public List<Materia> listarMateriasOrdenadas(String order) {
        List<Materia> listadoMaterias = materiaDao.getAllMaterias();
        if (order != null) {
            switch (order) {
                case "nombre_asc":
                    listadoMaterias.sort(Comparator.comparing(Materia::getNombre));
                    break;
                case "nombre_desc":
                    listadoMaterias.sort(Comparator.comparing(Materia::getNombre).reversed());
                    break;
                case "codigo_asc":
                    listadoMaterias.sort(Comparator.comparing(Materia::getMateriaId));
                    break;
                case "codigo_desc":
                    listadoMaterias.sort(Comparator.comparing(Materia::getMateriaId).reversed());
                    break;
            }
        }
        return listadoMaterias;
    }

    @Override
    public Materia getMateriaById(Integer idMateria) throws ValorInvalidoException {
        validaciones.validarNumeroPositivo(idMateria, "idMateria");
        return materiaDao.getMateriaById(idMateria);
    }

    @Override
    public String eliminarMateria(Integer idMateria) throws MateriaNoExisteException {
        return materiaDao.deleteMateria(idMateria);
    }

    private Integer cuatrimestreValido(Integer cuatrimestres, Integer cuatrimestresCarrera) throws CantidadCuatrimestresInvalidException {
        if (cuatrimestres > 0 && cuatrimestres <= cuatrimestresCarrera) {
            return cuatrimestres;
        }
        throw new CantidadCuatrimestresInvalidException("El numero de cuatrimestre ingresado no es valido");
    }
}
