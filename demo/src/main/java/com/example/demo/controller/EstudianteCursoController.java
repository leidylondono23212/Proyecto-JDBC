package com.example.demo.controller;

import com.example.demo.model.EstudianteCurso;
import com.example.demo.repository.CursoRepository;
import com.example.demo.repository.EstudianteCursoRepository;
import com.example.demo.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/estudiantes_cursos")
public class EstudianteCursoController {

    @Autowired
    private EstudianteCursoRepository estudianteCursoRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public String listarEstudiantesCursos(Model model) {
        model.addAttribute("estudiantesCursos", estudianteCursoRepository.findAll());
        return "estudiantesCursos";
    }

    @GetMapping("/nuevo")
    public String nuevoEstudianteCurso(Model model) {
        model.addAttribute("estudianteCurso", new EstudianteCurso());
        model.addAttribute("estudiantes", estudianteRepository.findAll());
        model.addAttribute("cursos", cursoRepository.findAll());
        return "formularioEstudianteCurso";
    }

    @PostMapping
    public String guardarEstudianteCurso(@ModelAttribute EstudianteCurso estudianteCurso) {
        estudianteCursoRepository.save(estudianteCurso);
        return "redirect:/estudiantes_cursos";
    }

    @GetMapping("/editar/{id}")
    public String editarEstudianteCurso(@PathVariable Integer id, Model model) {
        EstudianteCurso estudianteCurso = estudianteCursoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ID inválido:" + id));
        model.addAttribute("estudianteCurso", estudianteCurso);
        model.addAttribute("estudiantes", estudianteRepository.findAll());
        model.addAttribute("cursos", cursoRepository.findAll());
        return "formularioEstudianteCurso";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarEstudianteCurso(@PathVariable Integer id, @ModelAttribute EstudianteCurso estudianteCurso) {
        estudianteCurso.setIdEstudianteCurso(id);
        estudianteCursoRepository.save(estudianteCurso);
        return "redirect:/estudiantes_cursos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarEstudianteCurso(@PathVariable Integer id) {
        estudianteCursoRepository.deleteById(id);
        return "redirect:/estudiantes_cursos";
    }
}
