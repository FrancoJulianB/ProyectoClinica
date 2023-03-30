package com.dh.paciente.controller;

import com.dh.paciente.DTO.OdontologoDTO;
import com.dh.paciente.entity.Odontologo;
import com.dh.paciente.service.OdontologoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    @Autowired
    private OdontologoService service;
    Logger LOGGER = Logger.getLogger(OdontologoController.class);

    @PostMapping
    public ResponseEntity<OdontologoDTO> crearOdontologo(@RequestBody Odontologo odontologo) {
        OdontologoDTO odontologoDTO = service.create(odontologo);

        return ResponseEntity.status(HttpStatus.CREATED).body(odontologoDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OdontologoDTO> buscarOdontologo(@PathVariable Integer id) {
        ResponseEntity response;
        OdontologoDTO odontologoDTO;
        try{
            odontologoDTO = service.getById(id);
            response = ResponseEntity.status(HttpStatus.OK).body(odontologoDTO);
        } catch(Exception e){
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro ningun odontologo con el ID indicado.");
            LOGGER.error(e);
        }
        return response;
    }

    @GetMapping
    public ResponseEntity<Set<OdontologoDTO>> listarOdontologo(){
        ResponseEntity response = ResponseEntity.status(HttpStatus.OK).body(service.getAll());
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OdontologoDTO> eliminarOdontologoId(@PathVariable Integer id){
        ResponseEntity response;
        try{
            service.delete(id);
            response = ResponseEntity.status(HttpStatus.OK).body("Se elimino el odontologo solicitado");
        } catch(Exception e){
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro odontologo con el ID indicado. Error al eliminar.");
            LOGGER.error(e);
        }
        return response;
    }

    @PutMapping
    public ResponseEntity<OdontologoDTO> actualizarOdontologo(@RequestBody Odontologo odontologo){
        ResponseEntity response;
        OdontologoDTO odontologoDTO;
        try{
            odontologoDTO = service.update(odontologo);
            response = ResponseEntity.status(HttpStatus.OK).body(odontologoDTO);
        } catch(Exception e){
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro odontologo para modificar con el ID indicado.");
            LOGGER.error(e);
        }
        return response;
    }

}