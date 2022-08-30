package com.api.gestaodepartamentos.repositories;

import com.api.gestaodepartamentos.entities.DepartmentEntity;
import com.api.gestaodepartamentos.entities.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {

    boolean existsByName(String name);

}
