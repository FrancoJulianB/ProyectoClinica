package com.dh.paciente.service;

import java.util.Set;

public interface ServiceInterface<DTO, Clase> {
    DTO create(Clase t) throws Exception;
    void delete(Integer id) throws Exception;
    DTO update(Clase t) throws Exception;
    DTO getById(Integer id) throws Exception;
    Set<DTO> getAll();
    boolean exists(Integer id);
}
