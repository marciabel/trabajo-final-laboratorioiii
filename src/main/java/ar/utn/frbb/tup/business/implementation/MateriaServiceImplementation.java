package ar.utn.frbb.tup.business.implementation;

import ar.utn.frbb.tup.business.CarreraService;
import ar.utn.frbb.tup.business.MateriaService;
import ar.utn.frbb.tup.business.ProfesorService;
import ar.utn.frbb.tup.dto.MateriaDTO;
import ar.utn.frbb.tup.model.Carrera;
import ar.utn.frbb.tup.model.Materia;
import ar.utn.frbb.tup.model.Profesor;
import ar.utn.frbb.tup.persistence.MateriaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MateriaServiceImplementation implements MateriaService {

    @Autowired
    MateriaDao materiaDao;

    @Autowired
    ProfesorService profesorService;

    @Autowired
    CarreraService carreraService;

    @Override
    public Materia crearMateria(MateriaDTO materiaDTO) {
        Materia m = new Materia();
        m.setMateriaId(materiaDTO.getIdCarrera());
        m.setNombre(materiaDTO.getNombre());

        //-----------------------------------------//
        /* Podría utilizar otro metodo u otro service que busque el profesor a partir del id*/

        materiaDTO.getIdPofesor().ifPresent(id -> {
            Profesor profesor = profesorService.getProfesorById(id);
            m.setProfesor(profesor);
        });

        /*Lo mismo con la carrera*/
        m.setCarrera(carreraService.getCarreraById(materiaDTO.getIdCarrera()));
        //-----------------------------------------//

        m.setAnio(materiaDTO.getAnio());
        m.setCuatrimestre(materiaDTO.getCuatrimestre());

        materiaDao.saveMateria(m);

        return m;
    }
}
