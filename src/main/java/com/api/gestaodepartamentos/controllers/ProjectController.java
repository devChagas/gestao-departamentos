package com.api.gestaodepartamentos.controllers;


import com.api.gestaodepartamentos.entities.DepartmentEntity;
import com.api.gestaodepartamentos.entities.ProjectEntity;
import com.api.gestaodepartamentos.entities.UserEntity;
import com.api.gestaodepartamentos.services.ProjectService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/projs")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public ResponseEntity<Page<ProjectEntity>> getAll(@PageableDefault(page = 0, size = 10, sort = "id",
                                                direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(projectService.findAll(pageable));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getOne(@PathVariable(value = "id") Long id){
        Optional<ProjectEntity> projectEntityOptional = projectService.findById(id);
        if(!projectEntityOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Projeto não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(projectEntityOptional.get());
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody ProjectEntity projectEntity){

        if(projectService.existsByName(projectEntity.getName())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Esse projeto já foi cadastrado");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(projectService.save(projectEntity));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Long id){


        Optional<ProjectEntity> projectEntityOptional = projectService.findById(id);
        if(!projectEntityOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Projeto não encontrado");
        }

        return ResponseEntity.status(HttpStatus.OK).body("Projeto deletado com sucesso");
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") Long id,
                                         @RequestBody ProjectEntity projectEntity){
        Optional<ProjectEntity> projectEntityOptional = projectService.findById(id);
        if(!projectEntityOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Projeto não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(projectService.save(projectEntity));
    }


}
