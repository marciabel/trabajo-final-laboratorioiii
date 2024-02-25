package ar.utn.frbb.tup.controller;

import ar.utn.frbb.tup.business.CarreraService;
import ar.utn.frbb.tup.business.exception.ValorInvalidoException;
import ar.utn.frbb.tup.business.exception.NombreInvalidoException;
import ar.utn.frbb.tup.dto.CarreraDTO;
import ar.utn.frbb.tup.model.Carrera;
import ar.utn.frbb.tup.business.exception.CantidadCuatrimestresInvalidException;
import ar.utn.frbb.tup.persistence.exception.CarreraAlreadyExistsException;
import ar.utn.frbb.tup.persistence.exception.CarreraNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("carrera")
public class CarreraController {

    @Autowired
    CarreraService carreraService;

    @PostMapping
    public Carrera crearCarrera(@RequestBody CarreraDTO carreraDTO) throws CarreraAlreadyExistsException, CantidadCuatrimestresInvalidException, NombreInvalidoException, ValorInvalidoException {
        Carrera c = carreraService.crearCarrera(carreraDTO);
        System.out.println(c);
        return c;
    }

    @PatchMapping("/{idCarrera}")
    public Carrera modificarCarrera(@PathVariable Integer idCarrera, @RequestBody Map<String,Object> campos) throws CarreraNotFoundException, CantidadCuatrimestresInvalidException, NombreInvalidoException, ValorInvalidoException {
        return carreraService.modificarCarrera(idCarrera, campos);
    }

    @DeleteMapping("/{idCarrera}")
    public String eliminarCarrera(@PathVariable Integer idCarrera) throws CarreraNotFoundException {
        return carreraService.eliminarCarrera(idCarrera);
    }
}
