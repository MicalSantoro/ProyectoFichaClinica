package com.test.prueba.service;

import com.test.prueba.entidades.Paciente;
import com.test.prueba.repo.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;
    private Paciente paciente;


    public void AgregarPaciente(Paciente paciente) {
        pacienteRepository.save(paciente);

    }

    public List<Paciente> listarPacientes() {

        return pacienteRepository.findAll();
    }

    public Paciente buscarPacienteId(Integer id) {
        return pacienteRepository.findById(id).orElse(null);
    }

    public void delete(Integer id) {
        pacienteRepository.deleteById(id);
    }
}