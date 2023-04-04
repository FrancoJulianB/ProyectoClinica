package com.dh.clinicaOdon.service;

import com.dh.clinicaOdon.DTO.PacienteDTO;
import com.dh.clinicaOdon.entity.Paciente;
import com.dh.clinicaOdon.exception.ExistentObjectException;
import com.dh.clinicaOdon.exception.HasNullFieldsException;
import com.dh.clinicaOdon.repository.DomicilioRepository;
import com.dh.clinicaOdon.repository.PacienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService implements IService<PacienteDTO, Paciente> {
    @Autowired
    PacienteRepository repository;
    @Autowired
    DomicilioRepository domicilioRepository;
    @Autowired
    ObjectMapper mapper;

    public PacienteDTO create(Paciente paciente) throws HasNullFieldsException, ExistentObjectException {
        if(hasNullFields(paciente)){
            throw new HasNullFieldsException("Alguno de los campos esta vacio.");
        }
        if(dniExist(paciente.getDni())){
            throw new ExistentObjectException("Ya existe un paciente registrado con el DNI indicado.");
        } else{
            domicilioRepository.save(paciente.getDomicilio());
            Paciente pacienteDataBase = repository.save(paciente);

            PacienteDTO pacienteDTO = mapper.convertValue(pacienteDataBase, PacienteDTO.class);

            return pacienteDTO;
        }
    }
    public List<PacienteDTO> getAll(){
        List<PacienteDTO> pacientes = new ArrayList<>();
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

        PacienteDTO pacienteDTO = classToDTO(pacienteDataBase);
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

    public boolean dniExist (String dni){
        return repository.getPacienteByDNI(dni).size() >= 1;
    }

    public boolean hasNullFields(Paciente paciente){
        boolean nombre = paciente.getNombre() == null || paciente.getNombre() == "";
        boolean apellido = paciente.getApellido() == null || paciente.getApellido() == "";
        boolean email = paciente.getEmail() == null || paciente.getEmail() == "";
        boolean dni = paciente.getDni() == null || paciente.getDni() == "";

        boolean domicilio = paciente.getDomicilio().getCalle() == null ||
                paciente.getDomicilio().getCalle() == "" ||
                paciente.getDomicilio().getNumeroCalle() == null ||
                paciente.getDomicilio().getProvincia() == null ||
                paciente.getDomicilio().getProvincia() == "" ||
                paciente.getDomicilio().getLocalidad() == null ||
                paciente.getDomicilio().getLocalidad() == "";

        return (nombre || apellido || domicilio || email || dni);

    }

    public PacienteDTO classToDTO(Paciente paciente){
        PacienteDTO pacienteDTO = new PacienteDTO();
        pacienteDTO.setId(paciente.getId());
        pacienteDTO.setApellido(paciente.getApellido());
        pacienteDTO.setNombre(paciente.getNombre());

        return pacienteDTO;
    }

}
