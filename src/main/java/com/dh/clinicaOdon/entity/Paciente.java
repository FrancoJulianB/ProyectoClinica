package com.dh.clinicaOdon.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "PACIENTES", indexes = {@Index(name = "dni_unique_index", columnList = "dni", unique = true)})
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String apellido;
    private String nombre;
    private String email;
    @Column(unique = true)
    private String dni;
    //private Date fechaIngreso;
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "domicilio_id")
    private Domicilio domicilio;
/*    @OneToMany(mappedBy = "paciente", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JsonIgnore
    private Set<Turno> turnos = new HashSet<>();*/

}
