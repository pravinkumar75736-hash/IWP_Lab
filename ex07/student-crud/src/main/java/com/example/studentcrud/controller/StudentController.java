package com.example.studentcrud.controller;

import com.example.studentcrud.model.Student;
import com.example.studentcrud.repo.StudentRepository;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentRepository repo;

    public StudentController(StudentRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("students", repo.findAll());
        return "list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("title", "Add Student");
        return "form";
    }

    @PostMapping
    public String save(
            @Valid @ModelAttribute("student") Student student,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute(
                "title",
                student.getId() == null ? "Add Student" : "Edit Student"
            );
            return "form";
        }

        repo.save(student);
        return "redirect:/students";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {

        Student student = repo.findById(id)
                .orElseThrow(() ->
                    new IllegalArgumentException("Invalid student id: " + id));

        model.addAttribute("student", student);
        model.addAttribute("title", "Edit Student");

        return "form";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        repo.deleteById(id);
        return "redirect:/students";
    }
}