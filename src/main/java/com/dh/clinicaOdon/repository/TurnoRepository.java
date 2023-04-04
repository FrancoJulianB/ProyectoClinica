package com.dh.clinicaOdon.repository;

import com.dh.clinicaOdon.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Integer> {
    @Query("FROM Turno t WHERE t.paciente.id = ?1")
    List<Turno> findTurnoByPacienteId(Integer id);
}
