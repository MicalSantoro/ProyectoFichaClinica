package com.test.prueba.controller;


import com.test.prueba.entidades.*;
import com.test.prueba.repo.UserRepository;
import com.test.prueba.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Controller
public class AdminController {


    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private UserRepository userRepository;







    @GetMapping("/adminDashboard")
    public String adminDashboard(Model model) {
        List<Paciente> paciente = pacienteService.listarPacientes(); // Obtén el paciente de alguna forma
        model.addAttribute("paciente", paciente);
        return "adminDashboard";
    }

//    @PostMapping("/admin/ingresar-paciente")//funciona
//    public void agregarPaciente(@RequestBody Paciente paciente) {
//        pacienteService.AgregarPaciente(paciente);
//    }

    @GetMapping("/admin/registros")
    public String showForm(Model model) {
        model.addAttribute("paciente", new Paciente());
        return "RegistroPaciente";
    }

    @PostMapping("/admin/ingresar-paciente")
    public String addPaciente(@ModelAttribute Paciente paciente) {
        pacienteService.AgregarPaciente(paciente);
        return "redirect:/adminDashboard"; // Redirige a una página de éxito o a cualquier otra página después de guardar
    }

    @PostMapping("/ver")
    public ResponseEntity<String> actualizarPaciente(@PathVariable("id") Integer id, @ModelAttribute Paciente paciente) {
        Paciente pacienteExistente = pacienteService.buscarPacienteId(id);

        if (pacienteExistente != null) {
            // Actualiza los campos necesarios del paciente
            pacienteExistente.setNombre(paciente.getNombre());
            pacienteExistente.setApellido(paciente.getApellido());
            pacienteExistente.setSexo(paciente.getSexo());
            pacienteExistente.setEdad(paciente.getEdad());
            pacienteExistente.setTelefono(paciente.getTelefono());
            pacienteExistente.setDireccion(paciente.getDireccion());
            pacienteExistente.setFechaNacimiento(paciente.getFechaNacimiento());
            pacienteExistente.setFechaRegistro(paciente.getFechaRegistro());

            pacienteService.AgregarPaciente(pacienteExistente);
            return ResponseEntity.ok("Paciente actualizado con éxito");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente no encontrado");
        }
    }


    @GetMapping("admin/ver-paciente/{id}") //funciona
    @ResponseBody
    public Paciente buscarPacienteId(@PathVariable Integer id) {
        return pacienteService.buscarPacienteId(id);

    }

//    @GetMapping("/admin")
//    public String adminPage (Model model, Principal principal) {
//        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
//        model.addAttribute("user", userDetails);
//        return "adminDashboard";
//    }

     @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDto userDto){

        Set<RoleEntity> roles = userDto.getRoles().stream()
                .map(role -> RoleEntity.builder()
                        .name(Role.valueOf(role))
                        .build())
                .collect(Collectors.toSet());

        User user = User.builder()
                .username(userDto.getUsername())
                .password((userDto.getPassword()))
                .email(userDto.getEmail())
                .roles(roles)
                .build();

        userRepository.save(user);

        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/deleteUser")
    public String deleteUser(@RequestParam String id){
        userRepository.deleteById(Long.parseLong(id));
        return "se ha borrado el usuario con id".concat(id);
    }


}





