package com.dh.clinicaOdon.service;

import java.util.List;

public interface IService<DTO, Clase> {
    DTO create(Clase t) throws Exception;
    void delete(Integer id) throws Exception;
    DTO update(Clase t) throws Exception;
    DTO getById(Integer id) throws Exception;
    List<DTO> getAll();
}
