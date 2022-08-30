package com.api.gestaodepartamentos.controllers;


import com.api.gestaodepartamentos.entities.DepartmentEntity;
import com.api.gestaodepartamentos.entities.UserEntity;
import com.api.gestaodepartamentos.repositories.DepartmentRepository;
import com.api.gestaodepartamentos.services.DepartmentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/depts")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public ResponseEntity<Page<DepartmentEntity>> getAll(@PageableDefault(page = 0, size = 10, sort = "id",
                                                        direction = Sort.Direction.ASC)Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(departmentService.findAll(pageable));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getOne(@PathVariable(value = "id")Long id){
        Optional<DepartmentEntity> departmentEntityOptional = departmentService.findById(id);
        if(!departmentEntityOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Departamento não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(departmentEntityOptional.get());
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody DepartmentEntity departmentEntity){

        if(departmentService.existsByName(departmentEntity.getName())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Esse departamento já foi cadastrado");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(departmentService.save(departmentEntity));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id")Long id){
        Optional<DepartmentEntity> departmentEntityOptional = departmentService.findById(id);
        if(!departmentEntityOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Departamento não encontrado");
        }
        departmentService.delete(departmentEntityOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Departamento deletado com sucesso");
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") Long id,
                                         @RequestBody DepartmentEntity departmentEntity){
        Optional<DepartmentEntity> departmentEntityOptional = departmentService.findById(id);
        if(!departmentEntityOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Departamento não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(departmentService.save(departmentEntity));
    }

}
