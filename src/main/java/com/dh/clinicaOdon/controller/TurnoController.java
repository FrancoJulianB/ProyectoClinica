package com.dh.clinicaOdon.controller;

import com.dh.clinicaOdon.DTO.TurnoDTO;
import com.dh.clinicaOdon.entity.Turno;
import com.dh.clinicaOdon.service.TurnoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class  TurnoController {
    @Autowired
    private TurnoService service;
    Logger LOGGER = Logger.getLogger(TurnoController.class);


    @PostMapping
    public ResponseEntity<TurnoDTO> crearTurno(@RequestBody Turno turno){
        ResponseEntity response;
        TurnoDTO turnoDTO;
        try{
            turnoDTO = service.create(turno);
            response = ResponseEntity.status(HttpStatus.CREATED).body(turnoDTO);
        } catch (Exception e){
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            LOGGER.error(e);
        }
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TurnoDTO> deleteTurno(@PathVariable Integer id){
        ResponseEntity response;
        try {
            service.delete(id);
            response = ResponseEntity.status(HttpStatus.OK).body("Se elimino el turno exitosamente.");
        } catch(Exception e){
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            LOGGER.error(e);
        }
        return response;
    }

    @PutMapping
    public ResponseEntity<TurnoDTO> actualizarTurno(@RequestBody Turno turno) throws Exception{
        ResponseEntity response;
        TurnoDTO turnoDTO;
        try{
            turnoDTO = service.update(turno);
            response = ResponseEntity.status(HttpStatus.OK).body(turnoDTO);
        } catch(Exception e){
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            LOGGER.error(e);
        }
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurnoDTO> getTurno(@PathVariable Integer id){
        ResponseEntity response;
        TurnoDTO turnoDTO;
        try{
            turnoDTO = service.getById(id);
            response = ResponseEntity.status(HttpStatus.OK).body(turnoDTO);
        } catch(Exception e){
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            LOGGER.error(e);
        }
        return response;
    }

    @GetMapping
    public ResponseEntity<List<TurnoDTO>> getAll(){
        ResponseEntity response = ResponseEntity.status(HttpStatus.OK).body(service.getAll());
        return response;
    }

    @GetMapping("/pacientes/{id}")
    public ResponseEntity<List<TurnoDTO>> getAllByPacienteID(@PathVariable Integer id){
        ResponseEntity response = ResponseEntity.status(HttpStatus.OK).body(service.getTurnosByPacienteId(id));
        return response;
    }

}
