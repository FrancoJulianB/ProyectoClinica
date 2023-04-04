package com.dh.clinicaOdon.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TurnoDTO {
    private int id;
    private PacienteDTO paciente;
    private OdontologoDTO odontologo;
    private String hora;
    private String dia;


    public TurnoDTO() {
    }
}
