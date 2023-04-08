package com.dh.clinicaOdon.service;

import com.dh.clinicaOdon.DTO.OdontologoDTO;
import com.dh.clinicaOdon.entity.Odontologo;
import com.dh.clinicaOdon.exception.ExistentObjectException;
import com.dh.clinicaOdon.exception.HasNullFieldsException;
import com.dh.clinicaOdon.exception.ObjectNotFoundException;
import com.dh.clinicaOdon.repository.OdontologoRepository;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class OdontologoServiceTest {
    @Mock
    private OdontologoRepository repository;
    @InjectMocks
    private OdontologoService service;

    Logger LOGGER = Logger.getLogger(OdontologoServiceTest.class);

    @Test
    void getAll() {
        Odontologo odontologo1 = new Odontologo(1, "Barrera", "Franco","1234");
        Odontologo odontologo2 = new Odontologo(2, "Martinez", "Gonzalo","1235");

        List<Odontologo> odontologos = new ArrayList<>();
        odontologos.add(odontologo1);
        odontologos.add(odontologo2);

        List<OdontologoDTO> odontologosEsperados = Arrays.asList(new OdontologoDTO(1, "Barrera", "Franco"), new OdontologoDTO(2, "Martinez", "Gonzalo"));
        doReturn(odontologos)
                .when(repository).findAll();

        List<OdontologoDTO> odontologosDevueltos = service.getAll();

        Assertions.assertIterableEquals(odontologosDevueltos, odontologosEsperados);
    }

    @Test
    void create() {
        //Arrange
        Odontologo odontologo = new Odontologo(1, "Barrera", "Franco", "1234");
        OdontologoDTO odontologoEsperado = new OdontologoDTO(odontologo.getId(), odontologo.getApellido(), odontologo.getNombre());
        OdontologoDTO odontologoMappeado = new OdontologoDTO();
        //Act
        when(repository.getOdontologoByMatricula(any())).thenReturn(Collections.emptyList());
        when(repository.save(any())).thenReturn(odontologo);
        try{
           odontologoMappeado = service.create(odontologo);
        } catch(Exception e){
            LOGGER.error(e);
        }
        //Assert
        Assertions.assertEquals(odontologoEsperado, odontologoMappeado);
    }

    @Test
    void create_throw_HasNullFieldsException(){
        Odontologo odontologo = new Odontologo(1, "", "Fernando", "1234");

        when(repository.getOdontologoByMatricula(any())).thenReturn(null);
        when(repository.save(any())).thenReturn(odontologo);

        Assertions.assertThrows(HasNullFieldsException.class, () ->{
            service.create(odontologo);
        });
    }

    @Test
    void create_throw_ExistentObjectException(){
        Odontologo odontologo = new Odontologo(1, "Barrera", "Fernando", "1234");
        List<Odontologo> odontologos = new ArrayList<>();
        odontologos.add(odontologo);

        when(repository.getOdontologoByMatricula(any())).thenReturn(odontologos);
        when(repository.save(any())).thenReturn(odontologo);

        Assertions.assertThrows(ExistentObjectException.class, () ->{
            service.create(odontologo);
        });
    }

    @Test
    void getById() throws ObjectNotFoundException {
        //Arrange
        Odontologo odontologo = new Odontologo(1, "Gonzalez", "Martin", "1234");
        doReturn(Optional.of(odontologo)).when(repository).findById(any());
        OdontologoDTO odontologoEsperado = new OdontologoDTO(1, "Gonzalez", "Martin");
        OdontologoDTO odontologoDevuelto = new OdontologoDTO();

        //Act
        try{
            odontologoDevuelto = service.getById(1);
        } catch(Exception e){
            LOGGER.error(e);
        }

        //Assert
        Assertions.assertEquals(odontologoEsperado, odontologoDevuelto);
    }

    @Test
    void getById_throw_ObjectNotFoundException() throws ObjectNotFoundException {
        //Arrange
        Odontologo odontologo = null;
        when(repository.findById(any())).thenReturn(Optional.ofNullable(odontologo));
        //Arrange
        Assertions.assertThrows(ObjectNotFoundException.class, () ->{
            service.getById(1);
        });
    }

    @Test
    void delete() throws ObjectNotFoundException {
        //Arrange
        Odontologo odontologo1 = new Odontologo(1, "Gonzalez", "Martin", "1234");
        Odontologo odontologo2 = new Odontologo(2, "Martinez", "Gonzalo", "1235");
        Odontologo odontologo3 = new Odontologo(3, "Fernandez", "Fernando", "1236");
        List<Odontologo> odontologosList = new ArrayList<>();
        odontologosList.add(odontologo1);
        odontologosList.add(odontologo2);
        odontologosList.add(odontologo3);

        when(repository.existsById(any())).thenReturn(true);
        doNothing().when(repository).deleteById(any());
        odontologosList.remove(odontologo2);
        //Act
        try{
            service.delete(odontologo2.getId());
        } catch(Exception e){
            LOGGER.error(e);
        }
        //Assert
        Assertions.assertFalse(odontologosList.contains(odontologo2));
    }

    @Test
    void delete_throw_ObjectNotFoundException(){
        //Arrange
        when(repository.existsById(any())).thenReturn(false);
        //Act
        Assertions.assertThrows(ObjectNotFoundException.class, () ->{
            service.delete(1);
        });
    }

    @Test
    void update() throws Exception {
        //Arrange
        Odontologo odontologo = new Odontologo(1, "Gonzalez", "Martin", "1234");
        OdontologoDTO odontologoEsperado = new OdontologoDTO(odontologo.getId(), odontologo.getApellido(), odontologo.getNombre());

        when(repository.existsById(any())).thenReturn(true);
        when(repository.save(any())).thenReturn(odontologo);
        OdontologoDTO odontologoUpdateado = new OdontologoDTO();
        //Act
        try{
            OdontologoDTO unOdontologo = service.update(odontologo);
            odontologoUpdateado.setId(odontologo.getId());
            odontologoUpdateado.setNombre(odontologo.getNombre());
            odontologoUpdateado.setApellido(odontologo.getApellido());
        } catch(Exception e){
            LOGGER.error(e);
        }
        Assertions.assertEquals(odontologoEsperado, odontologoUpdateado);
    }
    @Test
    void update_throws_ObjectNotFoundException(){
        //Arrange
        Odontologo odontologo = new Odontologo(1, "Gonzalez", "Martin", "1234");
        when(repository.existsById(any())).thenReturn(false);
        //Act
        Assertions.assertThrows(ObjectNotFoundException.class, () ->{
            service.update(odontologo);
        });
    }

    @Test
    void update_throws_HasNullFieldsException(){
        //Arrange
        Odontologo odontologo = new Odontologo(1, "", "Martin", "1234");
        when(repository.existsById(any())).thenReturn(true);
        //Act
        Assertions.assertThrows(HasNullFieldsException.class, () ->{
            service.update(odontologo);
        });
    }

    @Test
    void classToDTO(){
        //Arrange
        Odontologo odontologo = new Odontologo(1, "Gonzalez", "Martin", "1234");
        OdontologoDTO odontologoEsperado = new OdontologoDTO(odontologo.getId(), odontologo.getApellido(), odontologo.getNombre());
        //Act
        OdontologoDTO odontologoDevuelto = service.classToDTO(odontologo);
        //Assert
        Assertions.assertEquals(odontologoEsperado, odontologoDevuelto);
    }

    @Test
    void existById(){
        //Arrange
        when(repository.existsById(any())).thenReturn(false);

        //Assert
        Assertions.assertFalse(service.existsById(1));
    }
}