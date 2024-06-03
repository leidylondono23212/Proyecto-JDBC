package com.example.demo.controller;

import com.example.demo.model.Curso;
import com.example.demo.model.Profesor;
import com.example.demo.repository.CursoRepository;
import com.example.demo.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/profesores")
public class ProfesorController {

    @Autowired
    private ProfesorRepository profesorRepository;
    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public String listarProfesores(Model model) {
        model.addAttribute("profesores", profesorRepository.findAll());
        return "profesores";
    }

    @GetMapping("/nuevo")
    public String nuevoProfesor(Model model) {
        model.addAttribute("curso", new Curso());
        return "formularioProfesor";
    }

    @PostMapping
    public String guardarProfesor(@ModelAttribute Curso curso) {
        Profesor savedProfesor = profesorRepository.save(curso.getProfesor());
        curso.setIdProfesor(savedProfesor.getIdProfesor());
        cursoRepository.save(curso);
        return "redirect:/profesores";
    }

    @GetMapping("/editar/{id}")
    public String editarProfesor(@PathVariable Integer id, Model model) {
        model.addAttribute("curso", new Curso());
        Profesor profesor = profesorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ID inválido:" + id));
        model.addAttribute("profesor", profesor);
        return "formularioProfesor";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarProfesor(@PathVariable Integer id, @ModelAttribute Profesor profesor) {
        profesor.setIdProfesor(id);
        profesorRepository.save(profesor);
        return "redirect:/profesores";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarProfesor(@PathVariable Integer id) {
        profesorRepository.deleteById(id);
        return "redirect:/profesores";
    }
}
