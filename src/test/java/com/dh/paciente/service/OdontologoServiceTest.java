package com.dh.paciente.service;

import com.dh.paciente.DTO.OdontologoDTO;
import com.dh.paciente.entity.Odontologo;
import com.dh.paciente.repository.OdontologoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class OdontologoServiceTest {
    @Mock
    OdontologoRepository repository;
    @InjectMocks
    OdontologoService service;

    @Test
    void getAll() {
    }

    @Test
    void create() {
        //Arrange
        Odontologo odontologo = new Odontologo(1, "Barrera", "Franco", "1234");
        OdontologoDTO odontologoAMano = new OdontologoDTO(odontologo.getId(), odontologo.getApellido(), odontologo.getNombre());
        OdontologoDTO odontologoJackson;
        //Act
        Mockito.when(repository.save(any()))
                .thenReturn(odontologoAMano);
        odontologoJackson = service.create(odontologo);
        //Assert
        Assertions.assertEquals(odontologoAMano, odontologoJackson);
    }

    @Test
    void getById() {
    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }

    @Test
    void exists() {
    }
}