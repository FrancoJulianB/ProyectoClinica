package com.dh.clinicaOdon.DTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

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
