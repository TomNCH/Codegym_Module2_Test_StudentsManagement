package com.managers.repositories;

import com.managers.models.Classes;
import com.managers.models.Students;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StudentsRepository extends PagingAndSortingRepository<Students, Long> {
    Iterable<Students> findAllByClasses(Classes classes);

    Page<Students> findAllByClasses_Name(String name, Pageable pageable);
}
