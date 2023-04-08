package com.dh.clinicaOdon.controller;

import com.dh.clinicaOdon.DTO.OdontologoDTO;
import com.dh.clinicaOdon.entity.Odontologo;
import com.dh.clinicaOdon.exception.ExistentObjectException;
import com.dh.clinicaOdon.exception.HasNullFieldsException;
import com.dh.clinicaOdon.exception.ObjectNotFoundException;
import com.dh.clinicaOdon.service.OdontologoService;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class OdontologoControllerTest {

    @Mock
    private OdontologoService service;
    @InjectMocks
    private OdontologoController controller;

    Logger LOGGER = Logger.getLogger(OdontologoControllerTest.class);


    @Test
    void crearOdontologo() throws HasNullFieldsException, ExistentObjectException {
        //Arrange
        OdontologoDTO odontologoDTO = new OdontologoDTO(1, "Barrera", "Franco");
        Odontologo odontologo = new Odontologo(1, "Barrera", "Franco", "1234");
        when(service.create(any())).thenReturn(odontologoDTO);

        //Act
        ResponseEntity response = controller.crearOdontologo(odontologo);

        //Assert
        Assertions.assertEquals(HttpStatus.CREATED ,response.getStatusCode());
        Assertions.assertEquals(odontologoDTO, response.getBody());
    }

    @Test
    void crearOdontologo_catch_HasNullFieldsException() throws HasNullFieldsException, ExistentObjectException {
        //Arrange
        HasNullFieldsException odontologoDTO_Exception = new HasNullFieldsException("Alguno de los campos esta vacio");
        Odontologo odontologo = new Odontologo(1, "", "Franco", "1234");
        when(service.create(any())).thenThrow(odontologoDTO_Exception);

        //Act
        ResponseEntity response = controller.crearOdontologo(odontologo);

        //Assert
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void crearOdontologo_catch_ExistentObjectException() throws HasNullFieldsException, ExistentObjectException {
        //Arrange
        ExistentObjectException odontologoDTO_Exception = new ExistentObjectException("Ya existe un odontologo con la matricula indicada.");
        Odontologo odontologo = new Odontologo(1, "Barrera", "Franco", "1234");
        when(service.create(any())).thenThrow(odontologoDTO_Exception);

        //Act
        ResponseEntity response = controller.crearOdontologo(odontologo);

        //Assert
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void buscarOdontologo() throws ObjectNotFoundException {
        //Arrange
        OdontologoDTO odontologoDTO = new OdontologoDTO(1, "Barrera", "Franco");
        Odontologo odontologo = new Odontologo(1, "Barrera", "Franco", "1234");
        when(service.getById(any())).thenReturn(odontologoDTO);

        //Act
        ResponseEntity response = controller.buscarOdontologo(odontologo.getId());

        //Assert
        Assertions.assertEquals(HttpStatus.OK ,response.getStatusCode());
        Assertions.assertEquals(odontologoDTO, response.getBody());
    }
    @Test
    void buscarOdontologo_catch_ObjectNotFoundException() throws ObjectNotFoundException {
        //Arrange
        ObjectNotFoundException odontologoDTO_Exception = new ObjectNotFoundException("No se encontro odontologo con el ID indicado.");
        Odontologo odontologo = new Odontologo(1, "Barrera", "Franco", "1234");
        when(service.getById(any())).thenThrow(odontologoDTO_Exception);

        //Act
        ResponseEntity response = controller.buscarOdontologo(1);

        //Assert
        Assertions.assertEquals(HttpStatus.NOT_FOUND ,response.getStatusCode());
    }

    @Test
    void listarOdontologo() {
        //Arrange
/*        OdontologoDTO odontologo = new OdontologoDTO(1, "Barrera", "Franco");
        OdontologoDTO odontologo2 = new OdontologoDTO(2, "Perez", "Ramon");*/
        List<OdontologoDTO> odontologos = new ArrayList<>();
/*        odontologos.add(odontologo);
        odontologos.add(odontologo2);*/

        when(service.getAll()).thenReturn(odontologos);
        //Act
        ResponseEntity response = controller.listarOdontologo();

        //Arrange
        Assertions.assertEquals(HttpStatus.OK ,response.getStatusCode());
    }

    @Test
    void eliminarOdontologoId() throws ObjectNotFoundException {
        //Arrange
        doNothing().when(service).delete(any());

        //Act
        ResponseEntity response = controller.eliminarOdontologoId(1);

        //Assert
        Assertions.assertEquals(HttpStatus.OK ,response.getStatusCode());
    }

    @Test
    void eliminarOdontologoId_catch_ObjectNotFoundException() throws ObjectNotFoundException {
        //Arrange
        doThrow(ObjectNotFoundException.class).when(service).delete(any());

        //Act
        ResponseEntity response = controller.eliminarOdontologoId(1);

        //Assert
        Assertions.assertEquals(HttpStatus.NOT_FOUND ,response.getStatusCode());
    }

    @Test
    void actualizarOdontologo() throws ObjectNotFoundException, HasNullFieldsException {
        //Arrange
        OdontologoDTO odontologoDTO = new OdontologoDTO(1, "Barrera", "Franco");
        when(service.update(any())).thenReturn(odontologoDTO);

        Odontologo odontologo = new Odontologo(1, "Barrera", "Franco", "1234");
        //Act
        ResponseEntity response = controller.actualizarOdontologo(odontologo);

        //Assert
        Assertions.assertEquals(HttpStatus.OK ,response.getStatusCode());
    }

    @Test
    void actualizarOdontologo_catch_ObjectNotFoundException() throws ObjectNotFoundException, HasNullFieldsException {
        //Arrange
        when(service.update(any())).thenThrow(ObjectNotFoundException.class);

        Odontologo odontologo = new Odontologo(1, "Barrera", "Franco", "1234");
        //Act
        ResponseEntity response = controller.actualizarOdontologo(odontologo);

        //Assert
        Assertions.assertEquals(HttpStatus.NOT_FOUND ,response.getStatusCode());
    }

    @Test
    void actualizarOdontologo_catch_HasNullFieldsException() throws ObjectNotFoundException, HasNullFieldsException {
        //Arrange
        when(service.update(any())).thenThrow(HasNullFieldsException.class);

        Odontologo odontologo = new Odontologo(1, "Barrera", "Franco", "1234");
        //Act
        ResponseEntity response = controller.actualizarOdontologo(odontologo);

        //Assert
        Assertions.assertEquals(HttpStatus.BAD_REQUEST ,response.getStatusCode());
    }
}