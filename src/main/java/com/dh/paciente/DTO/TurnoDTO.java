package com.dh.paciente.DTO;

import com.dh.paciente.entity.Odontologo;
import com.dh.paciente.entity.Paciente;
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
