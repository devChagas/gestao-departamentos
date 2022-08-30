package com.api.gestaodepartamentos.services;

import com.api.gestaodepartamentos.entities.DepartmentEntity;
import com.api.gestaodepartamentos.entities.UserEntity;
import com.api.gestaodepartamentos.repositories.DepartmentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public boolean existsByName(String name) {return departmentRepository.existsByName(name);}

    @Transactional
    public DepartmentEntity save(DepartmentEntity departmentEntity) {return departmentRepository.save(departmentEntity);}

    public Page<DepartmentEntity> findAll(Pageable pageable) {return departmentRepository.findAll(pageable);}

    public Optional<DepartmentEntity> findById(Long id) {return departmentRepository.findById(id);}

    @Transactional
    public void delete(DepartmentEntity departmentEntity) {departmentRepository.delete(departmentEntity);}

}
