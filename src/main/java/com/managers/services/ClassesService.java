package com.managers.services;

import com.managers.models.Classes;

public interface ClassesService {
    Iterable<Classes> findAll();

    Classes findById(Long id);

    void save(Classes classes);

    void delete(Long id);
}
