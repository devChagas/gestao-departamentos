package com.api.gestaodepartamentos.services;

import com.api.gestaodepartamentos.entities.UserEntity;
import com.api.gestaodepartamentos.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }

    @Transactional
    public UserEntity save(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    public Page<UserEntity> findAll(Pageable pageable) {return userRepository.findAll(pageable);}

    public Optional<UserEntity> findById(Long id){return userRepository.findById(id);}

    @Transactional
    public void delete(UserEntity userEntity) {userRepository.delete(userEntity);}

}
