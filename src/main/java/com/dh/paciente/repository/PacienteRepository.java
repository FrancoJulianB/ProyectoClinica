package com.dh.paciente.repository;

import com.dh.paciente.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer> {

}
