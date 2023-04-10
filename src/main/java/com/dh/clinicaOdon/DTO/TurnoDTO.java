package com.dh.clinicaOdon.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TurnoDTO {
    private int id;
    private PacienteDTO paciente;
    private OdontologoDTO odontologo;
    private LocalTime hora;
    private LocalDate fecha;


    public TurnoDTO() {
    }
}
