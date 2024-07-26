package com.test.prueba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/paciente/dashboard")
    public String pascienteInicio() {
        return "pacienteDashboard";
    }

    @GetMapping("/paciente/info")
    public String infoPaciente() {
        return "fichaPaciente";
    }


    @GetMapping("/paciente/tratamientos")
    public String tratamientosPaciente() {
        return "tratamientosVistas";
    }

    @GetMapping("/paciente/")
    public String pacienteVista1() {
        return "informacion";
    }


}