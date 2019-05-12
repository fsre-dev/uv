package com.jis.uv.service;

import com.jis.uv.model.User;
import com.jis.uv.model.enums.RoleEnum;
import com.jis.uv.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private List<User> findAll() {
        return userRepository.findAll();
    }

    private Page<User> findAll(Pageable pageRequest) {
        return userRepository.findAll(pageRequest);
    }

    private Page<User> findAllByUsername(String username, Pageable pageRequest) {
        return userRepository.findAllByUsername(username, pageRequest);
    }

    private Page<User> findAllByEMail(String eMail, Pageable pageRequest) {
        return userRepository.findAllByEMail(eMail, pageRequest);
    }

    private Page<User> findAllByRole(RoleEnum role, Pageable pageRequest) {
        return userRepository.findAllByRole(role, pageRequest);
    }

    private User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    private User createUser(User user){
        return userRepository.save(user);
    }

    private User updateUser(User user, Long id){
        user.setId(id);
        return userRepository.save(user);
    }

    private User deleteUser(User user){
        user.setDeleted(true);
        return userRepository.save(user);
    }

    private void deletePermanentlyUser(Long id){
        userRepository.deleteById(id);
    }
}
