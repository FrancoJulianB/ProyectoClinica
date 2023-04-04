package com.dh.clinicaOdon.controller;

import com.dh.clinicaOdon.DTO.OdontologoDTO;
import com.dh.clinicaOdon.entity.Odontologo;
import com.dh.clinicaOdon.exception.ExistentObjectException;
import com.dh.clinicaOdon.exception.HasNullFieldsException;
import com.dh.clinicaOdon.exception.ObjectNotFoundException;
import com.dh.clinicaOdon.service.OdontologoService;
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
        ResponseEntity response;
        try{
            OdontologoDTO odontologoDTO = service.create(odontologo);
            response = ResponseEntity.status(HttpStatus.CREATED).body(odontologoDTO);
        } catch(HasNullFieldsException e){
            LOGGER.error(e);
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch(ExistentObjectException e){
            LOGGER.error(e);
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<OdontologoDTO> buscarOdontologo(@PathVariable Integer id) {
        ResponseEntity response;
        OdontologoDTO odontologoDTO;
        try{
            odontologoDTO = service.getById(id);
            response = ResponseEntity.status(HttpStatus.OK).body(odontologoDTO);
        } catch(ObjectNotFoundException e){
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
        } catch(ObjectNotFoundException e){
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
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            LOGGER.error(e);
        }
        return response;
    }

}