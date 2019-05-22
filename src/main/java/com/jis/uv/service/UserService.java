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

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Page<User> findAll(Pageable pageRequest) {
        return userRepository.findAll(pageRequest);
    }

    public Page<User> findAllByRole(RoleEnum role, Pageable pageRequest) {
        return userRepository.findAllByRole(role, pageRequest);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User findByEMail(String eMail) {
        return userRepository.findByEMail(eMail);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User createUser(User user) {
        user.setDeleted(false);
        return userRepository.save(user);
    }

    public User updateUser(User user, Long id) {
        user.setId(id);
        return userRepository.save(user);
    }

    public User deleteUser(User user) {
        user.setDeleted(true);
        return userRepository.save(user);
    }

    public void deletePermanentlyUser(Long id) {
        userRepository.deleteById(id);
    }
}
