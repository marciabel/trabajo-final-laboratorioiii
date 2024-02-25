package ar.utn.frbb.tup.persistence.implementation;

import ar.utn.frbb.tup.model.Materia;
import ar.utn.frbb.tup.persistence.CarreraDao;
import ar.utn.frbb.tup.persistence.MateriaDao;
import ar.utn.frbb.tup.persistence.exception.CarreraAlreadyExistsException;
import ar.utn.frbb.tup.persistence.exception.MateriaAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MateriaDaoImplementation implements MateriaDao {

    @Autowired
    CarreraDao carreraDao;

    private static final Map<Integer, Materia> repositorioMaterias = new HashMap<>();

    @Override
    public void createMateria(Materia m) throws MateriaAlreadyExistsException {
        if (repositorioMaterias.containsKey(m.getMateriaId())) {
            throw new MateriaAlreadyExistsException("El id ingresado ya pertenece a otra materia");
        }

        carreraDao.agregarMateriaACarrera(m.getCarrera(), m);

        repositorioMaterias.put(m.getMateriaId(), m);

        System.out.println(repositorioMaterias);
    }

    @Override
    public Materia updateMateria(Integer idMateria, Materia materia) {
        repositorioMaterias.remove(idMateria);
        System.out.println(repositorioMaterias);
        return materia;
    }


    @Override
    public void deleteMateria(Integer idMateria) {
        repositorioMaterias.remove(idMateria);
        System.out.println("Materia eliminada");
        System.out.println(repositorioMaterias);
    }

    @Override
    public Materia getMateriaById(Integer idMateria) {
        System.out.println(repositorioMaterias);
        return repositorioMaterias.get(idMateria);
    }

    @Override
    public List<Materia> getMateriaByName(String nombre) {

        List<Materia> materias = new ArrayList<>();

        for (Map.Entry<Integer, Materia> m : repositorioMaterias.entrySet()) {
            if (m.getValue().getNombre().toLowerCase().contains(nombre.toLowerCase())) {
                materias.add(m.getValue());
            }
        }

        return materias;
    }

    @Override
    public List<Materia> getAllMaterias() {
        List<Materia> todasMaterias = new ArrayList<>();

        for (Map.Entry<Integer, Materia> m : repositorioMaterias.entrySet()) {
            todasMaterias.add(m.getValue());
        }

        return todasMaterias;
    }


}
