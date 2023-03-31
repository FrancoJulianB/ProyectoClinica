package com.dh.paciente.service;

import com.dh.paciente.DTO.OdontologoDTO;
import com.dh.paciente.entity.Odontologo;
import com.dh.paciente.repository.OdontologoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OdontologoService implements ServiceInterface<OdontologoDTO, Odontologo>{
    @Autowired
    private OdontologoRepository repository;
    @Autowired
    ObjectMapper mapper;


    public List<OdontologoDTO> getAll(){
        List<OdontologoDTO> odontologosDTO = new ArrayList<>();

        for (Odontologo odontologo: repository.findAll()){
            OdontologoDTO odontologoDTO = classToDTO(odontologo);

            odontologosDTO.add(odontologoDTO);
        }

        return odontologosDTO;
    }

    public OdontologoDTO create(Odontologo odontologo) {
        Odontologo odontologoClass = repository.save(odontologo);

        OdontologoDTO odontologoDTO = classToDTO(odontologo);

        return odontologoDTO;
    }

    public OdontologoDTO getById(Integer id) throws Exception{
        Odontologo odontologo = repository.findById(id).orElse(null);
        Odontologo odontologo1;
        if(odontologo == null){
            throw new Exception("No se encontro odontologo con el ID indicado.");
        }

        OdontologoDTO odontologoDTO = classToDTO(odontologo);

        return odontologoDTO;
    }

    public void delete(Integer id) throws Exception{
        if(!repository.existsById(id)){
            throw new Exception("No existe odontologo para eliminar con ese ID.");
        }
        repository.deleteById(id);
    }




    public OdontologoDTO update(Odontologo odontologo) throws Exception{
        if(!exists(odontologo.getId())){
            throw new Exception("No se encontro odontologo para actualizar con el id indicado.");
        }

        Odontologo unOdontologo = repository.save(odontologo);
        OdontologoDTO odontologoDTO = classToDTO(odontologo);


        return odontologoDTO;
    }

    public boolean exists(Integer id){
        return repository.existsById(id);
    }

    public OdontologoDTO classToDTO(Odontologo odontologo){
        OdontologoDTO odontologoDTO = new OdontologoDTO();

        odontologoDTO.setId(odontologo.getId());
        odontologoDTO.setApellido(odontologo.getApellido());
        odontologoDTO.setNombre(odontologo.getNombre());

        return odontologoDTO;
    }
}