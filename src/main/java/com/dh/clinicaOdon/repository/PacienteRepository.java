package com.dh.clinicaOdon.repository;

import com.dh.clinicaOdon.entity.Odontologo;
import com.dh.clinicaOdon.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
    @Query("FROM Paciente p WHERE p.dni = ?1")
    List<Paciente> getPacienteByDNI(String dni);
}
