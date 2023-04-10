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
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String apellido;
    private String nombre;
    private String email;
    @Column(unique = true)
    private String dni;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "domicilio_id")
    private Domicilio domicilio;
}
