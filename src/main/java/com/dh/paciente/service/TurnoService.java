package com.dh.paciente.service;

import com.dh.paciente.DTO.TurnoDTO;
import com.dh.paciente.entity.Turno;
import com.dh.paciente.repository.DomicilioRepository;
import com.dh.paciente.repository.TurnoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TurnoService implements ServiceInterface<TurnoDTO, Turno>{
    @Autowired
    TurnoRepository repository;
    @Autowired
    PacienteService pacienteService;
    @Autowired
    OdontologoService odontologoService;
    @Autowired
    ObjectMapper mapper;

    public TurnoDTO create(Turno turno) throws Exception{
        boolean existePaciente = pacienteService.exists(turno.getPaciente().getId());
        boolean existeOdontologo = odontologoService.exists(turno.getOdontologo().getId());
        if(existePaciente && existeOdontologo){
            Turno turnoClass = repository.save(turno);
            TurnoDTO turnoDTO = mapper.convertValue(turnoClass, TurnoDTO.class);
            return turnoDTO;
        } else {
            throw new Exception("No se encontro paciente u odontologo con el ID indicado");
        }
    }
    public void delete(Integer id) throws Exception{
        if(exists(id)){
            repository.deleteById(id);
        } else{
            throw new Exception("No se encontro turno con el ID indicado");
        }
    }

    public TurnoDTO getById(Integer id) throws Exception{
        Turno turnoDataBase = repository.findById(id).orElse(null);

        if(turnoDataBase == null){
            throw new Exception("No se encontro ningun turno con el ID indicado.");
        }

        TurnoDTO turnoDTO = mapper.convertValue(turnoDataBase, TurnoDTO.class);
        return turnoDTO;
    }

    public List<TurnoDTO> getAll(){
        List turnosDTO = new ArrayList();
        for (Turno turnoDataBase : repository.findAll()){
            TurnoDTO turnoDTO = mapper.convertValue(turnoDataBase, TurnoDTO.class);

            turnosDTO.add(turnoDTO);
        }

        return turnosDTO;
    }
    public TurnoDTO update(Turno turno) throws Exception{
        if(!exists(turno.getId())){
            throw new Exception("No se encontro turno para actualizar con el ID indicado.");
        }
        Turno turnoDataBase = repository.save(turno);
        TurnoDTO turnoDTO = mapper.convertValue(turno, TurnoDTO.class);

        return turnoDTO;
    }
    public boolean exists(Integer id){
        return repository.existsById(id);
    }
}
