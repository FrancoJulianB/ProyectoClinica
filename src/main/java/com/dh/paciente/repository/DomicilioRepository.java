package com.dh.paciente.repository;

import com.dh.paciente.entity.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Los atributos que se pasan entre <> en JpaRepostory corresponden a la clase que va a manejar, y al tipo de dato de ID que va a generar
@Repository
public interface DomicilioRepository extends JpaRepository<Domicilio, Integer> {

}
