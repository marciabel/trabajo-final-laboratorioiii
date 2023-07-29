package ar.utn.frbb.tup.persistence.implementation;

import ar.utn.frbb.tup.model.Materia;
import ar.utn.frbb.tup.persistence.MateriaDao;
import org.springframework.stereotype.Repository;

@Repository
public class MateriaDaoImplementation implements MateriaDao {

    @Override
    public void saveMateria(Materia m) {
        System.out.println("ok");
    }
}
