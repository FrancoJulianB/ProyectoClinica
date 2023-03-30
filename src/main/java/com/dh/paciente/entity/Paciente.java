package com.dh.paciente.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "PACIENTES", indexes = {@Index(name = "dni_unique_index", columnList = "dni", unique = true)})
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String apellido;
    private String nombre;
    private String email;
    private String dni;
    //private Date fechaIngreso;
    @OneToOne(cascade = {CascadeType.ALL})
    private Domicilio domicilio;
/*    @OneToMany(mappedBy = "paciente", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JsonIgnore
    private Set<Turno> turnos = new HashSet<>();*/

    public Paciente() {
    }
}
