package com.example.demo.controller;

import com.example.demo.model.Curso;
import com.example.demo.model.Estudiante;
import com.example.demo.model.EstudianteCurso;
import com.example.demo.repository.CursoRepository;
import com.example.demo.repository.EstudianteCursoRepository;
import com.example.demo.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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

        List<EstudianteCurso> estudianteCurso = StreamSupport.stream(estudianteCursoRepository.findAll().spliterator(), false)
                        .collect(Collectors.toList());
        for (EstudianteCurso estCur: estudianteCurso){
            Estudiante estudiante = estudianteRepository.findById(estCur.getIdEstudiante()).orElse(null);
            estCur.setEstudiante(estudiante);
            Curso curso = cursoRepository.findById(estCur.getIdCurso()).orElse(null);
            estCur.setCurso(curso);
        }

        model.addAttribute("estudiantesCursos", estudianteCurso);
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
