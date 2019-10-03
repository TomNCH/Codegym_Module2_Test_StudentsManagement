package com.managers.services.Impl;

import com.managers.models.Classes;
import com.managers.repositories.ClassesRepository;
import com.managers.services.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;

public class ClassesServiceImpl implements ClassesService {

    @Autowired
    private ClassesRepository classesRepository;

    @Override
    public Iterable<Classes> findAll() {
        return classesRepository.findAll();
    }

    @Override
    public Classes findById(Long id) {
        return classesRepository.findOne(id);
    }

    @Override
    public void save(Classes classes) {
        classesRepository.save(classes);
    }

    @Override
    public void delete(Long id) {
        classesRepository.delete(id);
    }
}
