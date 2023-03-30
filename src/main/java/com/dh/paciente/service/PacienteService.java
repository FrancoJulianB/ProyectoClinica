package com.dh.paciente.service;

import com.dh.paciente.DTO.PacienteDTO;
import com.dh.paciente.entity.Paciente;
import com.dh.paciente.entity.Turno;
import com.dh.paciente.repository.DomicilioRepository;
import com.dh.paciente.repository.PacienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class PacienteService implements ServiceInterface<PacienteDTO, Paciente>{
    @Autowired
    PacienteRepository repository;
    @Autowired
    DomicilioRepository domicilioRepository;
    @Autowired
    ObjectMapper mapper;

    public PacienteDTO create(Paciente paciente){
        domicilioRepository.save(paciente.getDomicilio());
        Paciente pacienteDataBase = repository.save(paciente);

        PacienteDTO pacienteDTO = mapper.convertValue(pacienteDataBase, PacienteDTO.class);


        return pacienteDTO;
    }
    public Set<PacienteDTO> getAll(){
        Set<PacienteDTO> pacientes = new HashSet<>();
        for (Paciente pacienteDataBase: repository.findAll()){
            PacienteDTO pacienteDTO = mapper.convertValue(pacienteDataBase, PacienteDTO.class);
            pacientes.add(pacienteDTO);
        }
        return pacientes;
        };

    public PacienteDTO getById(Integer id) throws Exception{
        Paciente pacienteDataBase = repository.findById(id).orElse(null);
        if(pacienteDataBase == null){
            throw new Exception("No se encontro paciente con el id indicado");
        }

        PacienteDTO pacienteDTO = mapper.convertValue(pacienteDataBase, PacienteDTO.class);
        return pacienteDTO;
    }

    public PacienteDTO update(Paciente paciente) throws Exception{

        if(!exists(paciente.getId())){
            throw new Exception("No se encontro paciente con el ID indicado para modificar.");
        }
        //Debemos guardar el domicilio primero, ya que JPA busca en la base de datos que exista ese domicilio. El metodo .save
        //lo creara en caso de que no exista el domicilio, o lo fusionara si ya existe.
        domicilioRepository.save(paciente.getDomicilio());
        Paciente pacienteDataBase = repository.save(paciente);

        //Setteamos el pacienteDTO para devolver este por consola, tambien elegimos que info queremos mostrar realmente.
        PacienteDTO pacienteDTO = mapper.convertValue(pacienteDataBase, PacienteDTO.class);

        return pacienteDTO;
    }

    public void delete(Integer id) throws Exception{
        if(exists(id)){
          repository.deleteById(id);
        } else{
          throw new Exception("No se encontro paciente para eliminar con el ID indicado.");
        }
    }

    public boolean exists(Integer id){
        return repository.existsById(id);
    }

}
