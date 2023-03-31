package com.dh.paciente.service;

import com.dh.paciente.DTO.OdontologoDTO;
import com.dh.paciente.entity.Odontologo;
import com.dh.paciente.repository.OdontologoRepository;
import net.bytebuddy.implementation.bytecode.Throw;
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
        Odontologo odontologo = new Odontologo(1, "Barrera", "Franco", "1234");
        OdontologoDTO odontologoEsperado = new OdontologoDTO(odontologo.getId(), odontologo.getApellido(), odontologo.getNombre());

        when(repository.save(any())).thenReturn(odontologo);
        OdontologoDTO odontologoMappeado = service.create(odontologo);
        String odontologoMappeadoString = odontologoMappeado.toString();
        String odontologoEsperadoString = odontologoEsperado.toString();
        Assertions.assertEquals(odontologoEsperado, odontologoMappeado);
    }

    @Test
    void getById() throws Exception {
        //Arrange
        Odontologo odontologo = new Odontologo(1, "Gonzalez", "Martin", "1234");
        doReturn(Optional.of(odontologo)).when(repository).findById(any());
        OdontologoDTO odontologoEsperado = new OdontologoDTO(1, "Gonzalez", "Martin");
        OdontologoDTO odontologoDevuelto;

        //Act
        try{
            odontologoDevuelto = service.getById(1);
        } catch(Exception e){
            throw new Exception("Fallo el test");
        }

        //Assert
        Assertions.assertEquals(odontologoEsperado, odontologoDevuelto);
    }

    @Test
    void getById_throw_exception() throws Exception {
        //Arrange
        when(repository.findById(any())).thenReturn(null);
        //Arrange
        Assertions.assertThrows(Exception.class, () ->{
            service.getById(1);
        });
    }

    @Test
    void delete() throws Exception {
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
            throw new Exception("Fallo el test.");
        }
        //Assert
        //Assertions.assertTrue(odontologosList.size() == 2);
        Assertions.assertFalse(odontologosList.contains(odontologo2));
    }

    @Test
    void delete_throw_exception(){
        //Arrange
        when(repository.existsById(any())).thenReturn(false);
        //Act
        Assertions.assertThrows(Exception.class, () ->{
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
        OdontologoDTO odontologoUpdateado;
        //Act
        try{
            odontologoUpdateado = service.update(odontologo);
        } catch(Exception e){
            throw new Exception("Fallo el test");
        }
        Assertions.assertEquals(odontologoEsperado, odontologoUpdateado);
    }
    @Test
    void update_throws_exception(){
        //Arrange
        Odontologo odontologo = new Odontologo(1, "Gonzalez", "Martin", "1234");
        when(repository.existsById(any())).thenReturn(false);
        //Act
        Assertions.assertThrows(Exception.class, () ->{
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
}