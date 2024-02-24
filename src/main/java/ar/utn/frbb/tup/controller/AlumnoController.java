package ar.utn.frbb.tup.controller;

import ar.utn.frbb.tup.business.AlumnoService;
import ar.utn.frbb.tup.business.MateriaService;
import ar.utn.frbb.tup.dto.AlumnoDto;
import ar.utn.frbb.tup.dto.MateriaDTO;
import ar.utn.frbb.tup.model.Alumno;
import ar.utn.frbb.tup.model.Materia;
import ar.utn.frbb.tup.persistence.exception.CarreraNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("alumno")
public class AlumnoController {
    @Autowired
    AlumnoService alumnoService;
    @PostMapping("/")
    public Alumno crearAlumno(@RequestBody AlumnoDto alumnoDTO) {
        return alumnoService.crearAlumno(alumnoDTO);
    }

    @PatchMapping("/{idAlumno}")
    public Alumno modificarAlumno(@PathVariable Integer idAlumno, @RequestBody Map<String,Object> campos) {
        return alumnoService.modificarAlumno(idAlumno, campos);
    }

    @DeleteMapping("/idAlumno")
    public void eliminarAlumno(@PathVariable Integer idAlumno) {
        alumnoService.eliminarAlumno(idAlumno);
    }
}
