package com.example.demo.controller;

import com.example.demo.model.Especializacion;
import com.example.demo.model.Profesor;
import com.example.demo.repository.EspecializacionRepository;
import com.example.demo.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
@RequestMapping("/especializaciones")
public class EspecializacionController {

    @Autowired
    private EspecializacionRepository especializacionRepository;

    @Autowired
    private ProfesorRepository profesorRepository;

    @GetMapping
    public String listarEspecializaciones(Model model) {
        model.addAttribute("profesores", profesorRepository.findAll());
        return "especializaciones";
    }

    @GetMapping("/nuevo/{id}")
    public String nuevaEspecializacion(@PathVariable Integer id, Model model) {
        Especializacion especializacion =  new Especializacion();
        especializacion.setIdProfesor(id);
        model.addAttribute("especializacion", especializacion);
        return "formularioEspecializacion";
    }


    @GetMapping("/ver/{id}")
    public String verEspecializacion(@PathVariable Integer id, Model model) {
        Iterable<Especializacion> especializacion = especializacionRepository.findByProfesor(id);
        model.addAttribute("especializaciones", especializacion);
        return "verEspecializaciones";
    }

    @PostMapping
    public String guardarEspecializacion(@ModelAttribute Especializacion especializacion) {
        especializacionRepository.save(especializacion);
        return "redirect:/especializaciones";
    }

    @GetMapping("/editar/{id}")
    public String editarEspecializacion(@PathVariable Integer id, Model model) {
        Especializacion especializacion = especializacionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ID inválido:" + id));
        model.addAttribute("especializacion", especializacion);
        model.addAttribute("profesores", profesorRepository.findAll());
        return "formularioEspecializacion";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarEspecializacion(@PathVariable Integer id, @ModelAttribute Especializacion especializacion) {
        especializacion.setIdEspecializacion(id);
        especializacionRepository.save(especializacion);
        return "redirect:/especializaciones";
    }

    @GetMapping("/eliminar/{id}/{idProfesor}")
    public String eliminarEspecializacion(@PathVariable Integer id, @PathVariable Integer idProfesor) {
        especializacionRepository.deleteById(id);
        return "redirect:/especializaciones/ver/" + idProfesor;
    }
}
