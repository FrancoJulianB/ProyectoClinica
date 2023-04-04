package com.dh.clinicaOdon.service;

import com.dh.clinicaOdon.DTO.TurnoDTO;
import com.dh.clinicaOdon.entity.Turno;
import com.dh.clinicaOdon.repository.TurnoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TurnoService implements IService<TurnoDTO, Turno> {
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
        boolean existeOdontologo = repository.existsById(turno.getOdontologo().getId());
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

    public List<TurnoDTO> getTurnosByPacienteId(Integer id){
        List<Turno> turnos = repository.findTurnoByPacienteId(id);
        List<TurnoDTO> turnosDTO = new ArrayList<>();
        for(Turno t:turnos){
            TurnoDTO turno = classToDTO(t);
            turnosDTO.add(turno);
        }
        return turnosDTO;
    }

    public boolean exists(Integer id){
        return repository.existsById(id);
    }

    public TurnoDTO classToDTO(Turno turno){
        TurnoDTO turnoDTO = new TurnoDTO();
        turnoDTO.setId(turno.getId());
        turnoDTO.setDia(turno.getDia());
        turnoDTO.setHora(turno.getHora());
        turnoDTO.setPaciente(pacienteService.classToDTO(turno.getPaciente()));
        turnoDTO.setOdontologo(odontologoService.classToDTO(turno.getOdontologo()));

        return turnoDTO;
    }
}
