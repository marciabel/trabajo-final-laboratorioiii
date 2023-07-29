package ar.utn.frbb.tup.controller;

import ar.utn.frbb.tup.business.CarreraService;
import ar.utn.frbb.tup.dto.CarreraDTO;
import ar.utn.frbb.tup.dto.CarreraParcialDTO;
import ar.utn.frbb.tup.model.Carrera;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("carrera")
public class CarreraController {

    @Autowired
    CarreraService carreraService;

    @PostMapping
    public Carrera crearCarrera(@RequestBody CarreraDTO carreraDTO) {
        return carreraService.crearCarrera(carreraDTO);
    }

    @PatchMapping("/{idCarrera}")
    public Carrera modificarCarrera(@PathVariable Integer idCarrera, @RequestBody Map<String,Object> campos) {
        return carreraService.modificarCarrera(idCarrera, campos);
    }

    @DeleteMapping("/{idCarrera}")
    public void eliminarCarrera(@PathVariable Integer idCarrera) {
        carreraService.eliminarCarrera(idCarrera);
        return ;

    }
}
