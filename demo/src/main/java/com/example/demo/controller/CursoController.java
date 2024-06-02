package com.example.demo.controller;

import com.example.demo.model.Curso;
import com.example.demo.model.Profesor;
import com.example.demo.repository.CursoRepository;
import com.example.demo.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private ProfesorRepository profesorRepository;

    @GetMapping
    public String listarCursos(Model model) {

        List<Curso> cursos = StreamSupport.stream(cursoRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        for (Curso curso : cursos) {
            Profesor profesor = profesorRepository.findById(curso.getIdProfesor()).orElse(null);
            curso.setProfesor(profesor);
        }
        model.addAttribute("cursos", cursos);
        return "cursos";
    }

    @GetMapping("/nuevo")
    public String nuevoCurso(Model model) {
        model.addAttribute("curso", new Curso());
        model.addAttribute("profesores", profesorRepository.findAll());
        return "formularioCurso";
    }

    @PostMapping
    public String guardarCurso(@ModelAttribute Curso curso) {
        cursoRepository.save(curso);
        return "redirect:/cursos";
    }

    @GetMapping("/editar/{id}")
    public String editarCurso(@PathVariable Integer id, Model model) {
        Curso curso = cursoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ID inválido:" + id));
        model.addAttribute("curso", curso);
        model.addAttribute("profesores", profesorRepository.findAll());
        return "formularioCurso";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarCurso(@PathVariable Integer id, @ModelAttribute Curso curso) {
        curso.setIdCurso(id);
        cursoRepository.save(curso);
        return "redirect:/cursos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCurso(@PathVariable Integer id) {
        cursoRepository.deleteById(id);
        return "redirect:/cursos";
    }
}
