package com.managers.controllers;

import com.managers.models.Classes;
import com.managers.models.Students;
import com.managers.services.ClassesService;
import com.managers.services.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ClassesController {

    @Autowired
    private ClassesService classesService;

    @Autowired
    private StudentsService studentsService;

    @GetMapping("/classes")
    public ModelAndView listClasses(){
        Iterable<Classes> classes = classesService.findAll();
        ModelAndView modelAndView = new ModelAndView("/class/list");
        modelAndView.addObject("classes", classes);
        return modelAndView;
    }

    @GetMapping("/create-class")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/class/create");
        modelAndView.addObject("class", new Classes());
        return  modelAndView;
    }

    @PostMapping("/create-class")
    public ModelAndView saveClass(@ModelAttribute("class") Classes classes){
        classesService.save(classes);
        ModelAndView modelAndView = new ModelAndView("/class/create");
        modelAndView.addObject("class", new Classes());
        modelAndView.addObject("message", "New class created successfully");
        return modelAndView;
    }

    @GetMapping("/edit-class/{id}")
    public ModelAndView showEditForm(@PathVariable long id){
        Classes classes = classesService.findById(id);
        if (classes != null){
            ModelAndView modelAndView = new ModelAndView("/class/edit");
            modelAndView.addObject("class", classes);
            return modelAndView;
        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-class/")
    public ModelAndView updateClass(@ModelAttribute("class") Classes classes){
        classesService.save(classes);
        ModelAndView modelAndView = new ModelAndView("/class/edit");
        modelAndView.addObject("class", classes);
        modelAndView.addObject("message", "Class update successfully");
        return modelAndView;
    }

    @GetMapping("/delete-class/{id}")
    public ModelAndView showDeleteForm(@PathVariable long id){
        Classes classes = classesService.findById(id);
        if (classes != null){
            ModelAndView modelAndView = new ModelAndView("/class/delete");
            modelAndView.addObject("class", classes);
            return modelAndView;
        }else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-class")
    public String deleteClass(@ModelAttribute("class") Classes classes){
        classesService.delete(classes.getId());
        return "redirect:classes";
    }

    @GetMapping("/view-class/{id}")
    public ModelAndView viewProvince(@PathVariable("id") long id){
        Classes classes = classesService.findById(id);
        if (classes == null){
            return new ModelAndView("/error.404");
        }

        Iterable<Students> students = studentsService.findAllByClasses(classes);

        ModelAndView modelAndView = new ModelAndView("/class/view");
        modelAndView.addObject("class", classes);
        modelAndView.addObject("students", students);
        return modelAndView;
    }
}
