package com.api.gestaodepartamentos.services;

import com.api.gestaodepartamentos.entities.DepartmentEntity;
import com.api.gestaodepartamentos.entities.ProjectEntity;
import com.api.gestaodepartamentos.repositories.DepartmentRepository;
import com.api.gestaodepartamentos.repositories.ProjectRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;


@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final DepartmentRepository departmentRepository;

    public ProjectService(ProjectRepository projectRepository, DepartmentRepository departmentRepository) {
        this.projectRepository = projectRepository;
        this.departmentRepository = departmentRepository;
    }

    public boolean existsByName(String name){return projectRepository.existsByName(name);}


    @Transactional
    public ProjectEntity save(ProjectEntity projectEntity) {return projectRepository.save(projectEntity);}

    public Page<ProjectEntity> findAll(Pageable pageable) {return projectRepository.findAll(pageable);}

    public Optional<ProjectEntity> findById(Long id){return projectRepository.findById(id);}

    @Transactional
    public void delete(ProjectEntity projectEntity) {projectRepository.delete(projectEntity);}


}
