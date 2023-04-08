package com.dh.clinicaOdon.service;

import com.dh.clinicaOdon.DTO.OdontologoDTO;
import com.dh.clinicaOdon.entity.Odontologo;
import com.dh.clinicaOdon.exception.ExistentObjectException;
import com.dh.clinicaOdon.exception.HasNullFieldsException;
import com.dh.clinicaOdon.exception.ObjectNotFoundException;
import com.dh.clinicaOdon.repository.OdontologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OdontologoService implements IService<OdontologoDTO, Odontologo> {
    @Autowired
    private OdontologoRepository repository;



    public List<OdontologoDTO> getAll(){
        List<OdontologoDTO> odontologosDTO = new ArrayList<>();

        for (Odontologo odontologo: repository.findAll()){
            OdontologoDTO odontologoDTO = classToDTO(odontologo);

            odontologosDTO.add(odontologoDTO);
        }

        return odontologosDTO;
    }

    public OdontologoDTO create(Odontologo odontologo) throws HasNullFieldsException, ExistentObjectException {
        if(hasNullField(odontologo)){
            throw  new HasNullFieldsException("Alguno de los campos esta vacio");
        }else if (matriculaExist(odontologo.getMatricula())){
            throw new ExistentObjectException("Ya existe un odontologo con la matricula indicada.");
        } else{
            OdontologoDTO odontologoDTO = classToDTO(odontologo);
            repository.save(odontologo);
            System.out.println(odontologo.getMatricula());
            return odontologoDTO;
        }
    }

    public OdontologoDTO getById(Integer id) throws ObjectNotFoundException {
        Optional<Odontologo> odontologo = repository.findById(id);
        if(!odontologo.isPresent()){
            throw new ObjectNotFoundException("No se encontro odontologo con el ID indicado.");
        }

        OdontologoDTO odontologoDTO = classToDTO(odontologo.get());

        return odontologoDTO;
    }

    public void delete(Integer id) throws ObjectNotFoundException{
        if(!repository.existsById(id)){
            throw new ObjectNotFoundException("No existe odontologo para eliminar con ese ID.");
        }
        repository.deleteById(id);
    }




    public OdontologoDTO update(Odontologo odontologo) throws ObjectNotFoundException, HasNullFieldsException{
        if(!repository.existsById(odontologo.getId())){
            throw new ObjectNotFoundException("No se encontro odontologo para actualizar con el id indicado.");
        } else if (hasNullField(odontologo)) {
            throw new HasNullFieldsException("Alguno de los campos esta vacio");
        }

        Odontologo unOdontologo = repository.save(odontologo);
        OdontologoDTO odontologoDTO = classToDTO(unOdontologo);


        return odontologoDTO;
    }


    public boolean hasNullField(Odontologo odontologo){
        boolean nombre = odontologo.getNombre() == null || odontologo.getNombre() == "";
        boolean apellido = odontologo.getApellido() == null || odontologo.getApellido() == "";
        boolean matricula = odontologo.getMatricula() == null || odontologo.getMatricula() == "";

        return (nombre || apellido || matricula);
    }

    public OdontologoDTO classToDTO(Odontologo odontologo){
        OdontologoDTO odontologoDTO = new OdontologoDTO();

        odontologoDTO.setId(odontologo.getId());
        odontologoDTO.setApellido(odontologo.getApellido());
        odontologoDTO.setNombre(odontologo.getNombre());

        return odontologoDTO;
    }

    public boolean existsById(Integer id){
        return repository.existsById(id);
    }
    public boolean matriculaExist(String matricula){
        return repository.getOdontologoByMatricula(matricula).size() >= 1;
    }
}