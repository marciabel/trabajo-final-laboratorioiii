package ar.utn.frbb.tup.controller;

import ar.utn.frbb.tup.business.MateriaService;
import ar.utn.frbb.tup.business.exception.CantidadCuatrimestresInvalidException;
import ar.utn.frbb.tup.business.exception.MateriaNoExisteException;
import ar.utn.frbb.tup.business.exception.NombreInvalidoException;
import ar.utn.frbb.tup.business.exception.ValorInvalidoException;
import ar.utn.frbb.tup.dto.MateriaDTO;
import ar.utn.frbb.tup.model.Materia;
import ar.utn.frbb.tup.persistence.exception.CarreraNotFoundException;
import ar.utn.frbb.tup.persistence.exception.MateriaAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("materia")
public class MateriaController {

    @Autowired
    MateriaService materiaService;
    @PostMapping
    public Materia crearMateria(@RequestBody MateriaDTO materiaDTO) throws CarreraNotFoundException, NombreInvalidoException, ValorInvalidoException, MateriaAlreadyExistsException, CantidadCuatrimestresInvalidException, MateriaNoExisteException {
        return materiaService.crearMateria(materiaDTO);
    }

    @PatchMapping("/{idMateria}")
    public Materia modificarMateria(@PathVariable Integer idMateria, @RequestBody Map<String,Object> campos) {
        return materiaService.modificarMateria(idMateria, campos);
    }

    @DeleteMapping("/{idMateria}")
    public String eliminarMateria(@PathVariable Integer idMateria) throws MateriaNoExisteException {
        return materiaService.eliminarMateria(idMateria);
    }

    @GetMapping("/buscar")
    public List<Materia> buscarMateriasPorNombre(@RequestParam(required = false) String nombre) throws MateriaNoExisteException {
        if (nombre != null && !nombre.isEmpty()) {
            return materiaService.buscarPorNombre(nombre);
        } else {
            return materiaService.listarTodasLasMaterias();
        }
    }

    @GetMapping("/materias")
    public List<Materia> listarMateriasOrdenadas(@RequestParam(required = false) String order) {
        if (order != null && !order.isEmpty()) {
            return materiaService.listarMateriasOrdenadas(order);
        } else {
            return materiaService.listarTodasLasMaterias();
        }
    }

}
