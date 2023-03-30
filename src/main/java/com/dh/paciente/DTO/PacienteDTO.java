package com.dh.paciente.DTO;
import com.dh.paciente.entity.Turno;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PacienteDTO {
    private Integer id;
    private String apellido;
    private String nombre;

    //private Date fechaIngreso;


    public PacienteDTO() {
    }
}
