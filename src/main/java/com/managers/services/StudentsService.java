package com.managers.services;

import com.managers.models.Classes;
import com.managers.models.Students;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudentsService {
    Page<Students> findAll(Pageable pageable);

    Students findById(Long id);

    void save(Students students);

    void delete(Long id);

    Iterable<Students> findAllByClasses(Classes classes);

    Page<Students> findAllByClasses_Name(String name, Pageable pageable);
}
