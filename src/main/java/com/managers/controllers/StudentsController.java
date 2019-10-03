package com.managers.controllers;

import com.managers.models.Classes;
import com.managers.models.Students;
import com.managers.services.ClassesService;
import com.managers.services.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;


@Controller
public class StudentsController {

    @Autowired
    private StudentsService studentsService;

    @Autowired
    private ClassesService classesService;

    @GetMapping("/students")
    public ModelAndView listStudents(@RequestParam("s") Optional<String> s,@PageableDefault(value = 10, sort = "fullname") Pageable pageable){
        Page<Students> students;
        if (s.isPresent()){
            students = studentsService.findAllByClasses_Name(s.get(), pageable);
        }else {
            students = studentsService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("/student/list");
        modelAndView.addObject("students", students);
        return modelAndView;
    }

    @GetMapping("/create-student")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/student/create");
        modelAndView.addObject("student", new Students());
        return modelAndView;
    }

    @PostMapping("/create-student")
    public ModelAndView saveStudent(@ModelAttribute("student") Students students, BindingResult bindingResult){
        ModelAndView modelAndView = new ModelAndView("/student/create");
        if (bindingResult.hasErrors()){
            modelAndView.addObject("message", "New student created error");
            return modelAndView;
        }else {
            studentsService.save(students);
            modelAndView.addObject("student", new Students());
            modelAndView.addObject("message", "New student created successfully");
            return modelAndView;
        }
    }

    @GetMapping("/edit-student/{id}")
    public ModelAndView showEditForm(@PathVariable long id){
        Students students = studentsService.findById(id);
        if (students != null){
            ModelAndView modelAndView = new ModelAndView("/student/edit");
            modelAndView.addObject("student", students);
            return modelAndView;
        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-student")
    public ModelAndView updateStudent(@ModelAttribute("student") Students students){
        studentsService.save(students);
        ModelAndView modelAndView = new ModelAndView("/student/edit");
        modelAndView.addObject("student", students);
        modelAndView.addObject("message", "Student update successfully");
        return modelAndView;
    }

    @GetMapping("/delete-student/{id}")
    public ModelAndView showDeleteForm(@PathVariable long id){
        Students students = studentsService.findById(id);
        if (students != null){
            ModelAndView modelAndView = new ModelAndView("/student/delete");
            modelAndView.addObject("student", students);
            return modelAndView;
        }else{
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-student")
    public String deleteStudent(@ModelAttribute("student") Students students){
        studentsService.delete(students.getId());
        return "redirect:students";
    }

    @ModelAttribute("classes")
    public Iterable<Classes> provinces(){
        return classesService.findAll();
    }
}
