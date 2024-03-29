package ar.utn.frbb.tup.controller;

import ar.utn.frbb.tup.business.AlumnoService;
import ar.utn.frbb.tup.business.exception.EstadoNoValidoException;
import ar.utn.frbb.tup.business.exception.NotaNoValidaException;
import ar.utn.frbb.tup.business.exception.ValorInvalidoException;
import ar.utn.frbb.tup.dto.AlumnoDto;
import ar.utn.frbb.tup.dto.AsignaturaDTO;
import ar.utn.frbb.tup.model.Alumno;
import ar.utn.frbb.tup.persistence.exception.AlumnoAlreadyExistsException;
import ar.utn.frbb.tup.persistence.exception.AlumnoNoExisteException;
import ar.utn.frbb.tup.persistence.exception.CarreraNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("alumno")
public class AlumnoController {
    @Autowired
    AlumnoService alumnoService;
    @PostMapping
    public Alumno crearAlumno(@RequestBody AlumnoDto alumnoDTO) throws CarreraNotFoundException, ValorInvalidoException, AlumnoAlreadyExistsException {
        return alumnoService.crearAlumno(alumnoDTO);
    }

    @PatchMapping("/{idAlumno}")
    public Alumno modificarAlumno(@PathVariable Integer idAlumno, @RequestBody Map<String,Object> campos) throws AlumnoNoExisteException, ValorInvalidoException, CarreraNotFoundException {
        return alumnoService.modificarAlumno(idAlumno, campos);
    }

    @DeleteMapping("/{idAlumno}")
    public String eliminarAlumno(@PathVariable Integer idAlumno) throws AlumnoNoExisteException {
        return alumnoService.eliminarAlumno(idAlumno);
    }

    @PutMapping("/{idAlumno}/asignatura/{nombreAsignatura}")
    public Alumno cambiarEstadoAsignatura(
            @PathVariable Integer idAlumno,
            @PathVariable String nombreAsignatura,
            @RequestBody AsignaturaDTO nuevoEstado) throws NotaNoValidaException, EstadoNoValidoException, AlumnoNoExisteException {
        return alumnoService.cambiarEstadoAsignatura(idAlumno, nombreAsignatura, nuevoEstado);
    }
}
