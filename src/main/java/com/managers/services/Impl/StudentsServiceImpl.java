package com.managers.services.Impl;

import com.managers.models.Classes;
import com.managers.models.Students;
import com.managers.repositories.StudentsRepository;
import com.managers.services.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class StudentsServiceImpl implements StudentsService {

    @Autowired
    private StudentsRepository studentsRepository;

    @Override
    public Page<Students> findAll(Pageable pageable) {
        return studentsRepository.findAll(pageable);
    }

    @Override
    public Students findById(Long id) {
        return studentsRepository.findOne(id);
    }

    @Override
    public void save(Students students) {
        studentsRepository.save(students);
    }

    @Override
    public void delete(Long id) {
        studentsRepository.delete(id);
    }

    @Override
    public Iterable<Students> findAllByClasses(Classes classes) {
        return studentsRepository.findAllByClasses(classes);
    }

    @Override
    public Page<Students> findAllByClasses_Name(String name, Pageable pageable) {
        return studentsRepository.findAllByClasses_Name(name, pageable);
    }

}
