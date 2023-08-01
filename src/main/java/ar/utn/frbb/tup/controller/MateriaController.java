package ar.utn.frbb.tup.controller;

import ar.utn.frbb.tup.business.MateriaService;
import ar.utn.frbb.tup.dto.MateriaDTO;
import ar.utn.frbb.tup.model.Materia;
import ar.utn.frbb.tup.persistence.exception.CarreraNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("materia")
public class MateriaController {

    @Autowired
    MateriaService materiaService;
    @PostMapping("/")
    public Materia crearMateria(@RequestBody MateriaDTO materiaDTO) throws CarreraNotFoundException {
        return materiaService.crearMateria(materiaDTO);
    }
}
