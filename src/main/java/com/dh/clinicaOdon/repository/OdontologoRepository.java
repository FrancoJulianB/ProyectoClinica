package com.dh.clinicaOdon.repository;

import com.dh.clinicaOdon.entity.Odontologo;
import com.dh.clinicaOdon.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OdontologoRepository extends JpaRepository<Odontologo, Integer>{
    @Query("FROM Odontologo o WHERE o.matricula = ?1")
    List<Odontologo> getOdontologoByMatricula(String matricula);
}
