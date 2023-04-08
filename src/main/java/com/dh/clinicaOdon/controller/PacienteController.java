package com.dh.clinicaOdon.controller;

import com.dh.clinicaOdon.DTO.PacienteDTO;
import com.dh.clinicaOdon.entity.Paciente;
import com.dh.clinicaOdon.service.PacienteService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.web.bind.annotation.*;
import java.util.Set;



@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    @Autowired
    private PacienteService service;
    Logger LOGGER = Logger.getLogger(PacienteController.class);

    @PostMapping
    public ResponseEntity<PacienteDTO> crearPaciente(@RequestBody Paciente paciente) {
        ResponseEntity response;
        try{
            PacienteDTO pacienteDTO = service.create(paciente);
            response = ResponseEntity.status(HttpStatus.CREATED).body(pacienteDTO);
        } catch (Exception e){
            LOGGER.error(e);
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> buscarPaciente(@PathVariable Integer id) {
        ResponseEntity response;
        PacienteDTO pacienteDTO = new PacienteDTO();
        try {
            pacienteDTO = service.getById(id);
            response = ResponseEntity.status(HttpStatus.OK).body(pacienteDTO);
        } catch (Exception e) {
            LOGGER.error(e);
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            //response = new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @GetMapping
    public ResponseEntity<Set<PacienteDTO>> listarPacientes(){
        ResponseEntity response = ResponseEntity.status(HttpStatus.OK).body(service.getAll());
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PacienteDTO> eliminarPacienteId(@PathVariable Integer id){
        ResponseEntity response;
        try{
            service.delete(id);
            response = ResponseEntity.status(HttpStatus.OK).body("Se elimino el paciente exitosamente.");
        } catch(Exception e){
            LOGGER.error(e);
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        return response;
    }

    @PutMapping
    public ResponseEntity<PacienteDTO> actualizarPaciente(@RequestBody Paciente paciente){
        ResponseEntity response;
        PacienteDTO pacienteDTO;
        try{
            pacienteDTO = service.update(paciente);
            response = ResponseEntity.status(HttpStatus.OK).body("Se actualizo el paciente de manera exitosa.");
        } catch(Exception e){
            LOGGER.error(e);
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

        return response;
    }

}
