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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    @Autowired
    private OdontologoService service;
    Logger LOGGER = Logger.getLogger(OdontologoController.class);

    @RequestMapping(value="/list",method=RequestMethod.GET)
    public ModelAndView listar(Model model) {
        List<OdontologoDTO> odontologos = (List<OdontologoDTO>) listarOdontologo().getBody();
        model.addAttribute("odontologos", odontologos);
        ModelAndView mav = new ModelAndView("odontologo-list");
        return mav;
    }
    @RequestMapping(value="/registrar", method=RequestMethod.GET)
    public ModelAndView registro() {
        ModelAndView mav = new ModelAndView("odontologo-registro");
        return mav;
    }
    @RequestMapping(value="/modificar/{id}", method=RequestMethod.GET)
    public ModelAndView modificar() {
        ModelAndView mav = new ModelAndView("odontologo-modificar");
        return mav;
    }
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
        try{
            OdontologoDTO odontologoDTO = service.getById(id);
            response = ResponseEntity.status(HttpStatus.OK).body(odontologoDTO);
        } catch(ObjectNotFoundException e){
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
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
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
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
        } catch(ObjectNotFoundException e){
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            LOGGER.error(e);
        } catch(HasNullFieldsException e){
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            LOGGER.error(e);
        }
        return response;
    }

}