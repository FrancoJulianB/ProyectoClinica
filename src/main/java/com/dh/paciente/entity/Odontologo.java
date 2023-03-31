package com.dh.paciente.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity

@Table(name = "ODONTOLOGOS", indexes = {@Index(name = "matricula_unique_index", columnList = "matricula", unique = true)})
public class Odontologo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String apellido;
    private String nombre;
    private String matricula;

    //El mappedby hace referencia a quien se encarga de definir por donde se que columne se unen las tablas.
    //En este caso, va a buscar a la clase turnos si existe un atributo "odontologo" que tenga la etiqueta @JoinColumn.

    //El fetch se encarga de definir cuando deben cargarse en memoria los datos. Lazy define que hasta que no se
    //soliciten los datos, no los carga.

    //Cascade define que paso con las entidades cuando se encuentran ante un nuevo evento.
    //REMOVE define que pasa cuando se borra la entidad que posea la propiedad cascade.
    //Dicha entidad es la entidad padre, y la acicon de REMOVE se aplica sobre aquellas en las que tenga relacion.
    //En este caso, cuando se borra una entidad odontologo, se elimina tambien el turno que tenia en su relacion.
/*    @OneToMany(mappedBy = "odontologo", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JsonIgnore
    private Set<Turno> turnos = new HashSet<>();*/

    public Odontologo() {
    }

    public Odontologo(Integer id, String apellido, String nombre, String matricula) {
        this.id = id;
        this.apellido = apellido;
        this.nombre = nombre;
        this.matricula = matricula;
    }
}
