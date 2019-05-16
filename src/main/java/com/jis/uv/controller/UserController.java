package com.jis.uv.controller;

import com.jis.uv.model.User;
import com.jis.uv.model.enums.RoleEnum;
import com.jis.uv.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    ResponseEntity<List<User>> findAll() {
        List<User> users = userService.findAll();
        if (users.isEmpty() || users == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/all", params = {"page", "size"})
    ResponseEntity<Page<User>> findAll(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        Page<User> users = userService.findAll(PageRequest.of(page, size));
        if (users.isEmpty() || users == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/role", params = {"page", "size", "value"})
    ResponseEntity<Page<User>> findAllByRole(@RequestParam("value") RoleEnum role, @RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        Page<User> users = userService.findAllByRole(role, PageRequest.of(page, size));
        if (users.isEmpty() || users == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<User> findById(@PathVariable(value = "id") Long id) {
        User user = userService.findById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(params = {"email"})
    ResponseEntity<User> findByEMail(@RequestParam("email") String eMail) {
        User user = userService.findByEMail(eMail);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.findByUsername(user.getUsername());
        if (createdUser != null) {
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
        }
        createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable(value = "id") Long id) {
        User updatedUser = userService.findById(id);
        if (updatedUser == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        updatedUser = userService.updateUser(user, id);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @PutMapping(value = "/delete/{id}")
    private ResponseEntity<User> deleteUser(@PathVariable(value = "id") Long id) {
        User deletedUser = userService.findById(id);
        if (deletedUser == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        deletedUser = userService.deleteUser(deletedUser);
        return new ResponseEntity<>(deletedUser, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/deletePermanently/{id}")
    private ResponseEntity<Void> deleteUserPermanently(@PathVariable(value = "id") Long id) {
        User deletedUser = userService.findById(id);
        if (deletedUser == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        userService.deletePermanentlyUser(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


}