package com.dh.paciente.DTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include. NON_NULL)
public class OdontologoDTO {
    private Integer id;
    private String apellido;
    private String nombre;
    //Creo que no es ideal que se muestren los turnos del odontologo en el DTO.
    //private Set<Turno> turnos = new HashSet<>();

    public OdontologoDTO() {
    }

    public OdontologoDTO(Integer id, String apellido, String nombre) {
        this.id = id;
        this.apellido = apellido;
        this.nombre = nombre;
    }
}
