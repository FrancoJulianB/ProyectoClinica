package com.dh.clinicaOdon.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor


public class Odontologo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String apellido;
    private String nombre;
    @Column(unique = true)
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

}
