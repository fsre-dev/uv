package com.jis.uv.controller;

import com.jis.uv.model.User;
import com.jis.uv.model.enums.RoleEnum;
import com.jis.uv.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<User>> findAll() {
        List<User> users = userService.findAll();
        if (users.isEmpty() || users == null) {
            logger.info("List of users not found");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        logger.info("Users found");
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/all", params = {"page", "size"})
    public ResponseEntity<Page<User>> findAll(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        Page<User> users = userService.findAll(PageRequest.of(page, size));
        if (users.isEmpty() || users == null) {
            logger.info("List of users not found");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        logger.info("Users found");
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/role", params = {"page", "size", "value"})
    public ResponseEntity<Page<User>> findAllByRole(@RequestParam("value") RoleEnum role, @RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        Page<User> users = userService.findAllByRole(role, PageRequest.of(page, size));
        if (users.isEmpty() || users == null) {
            logger.info("List of users with role {} not found", role);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        logger.info("List of users with role {} is found", role);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable(value = "id") Long id) {
        User user = userService.findById(id);
        if (user == null) {
            logger.info("User with id {} not found", id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        logger.info("User with id {} is founded found", id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(params = {"email"})
    public ResponseEntity<User> findByEmail(@RequestParam("email") String eMail) {
        User user = userService.findByEmail(eMail);
        if (user == null) {
            logger.info("User with email {} not found", eMail);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        logger.info("User with email {} is founded", eMail);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/superadmin/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.findByUsername(user.getUsername());
        if (createdUser != null) {
            logger.info("Username already exists: {}", user.getUsername());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        if (!userService.validateUser(user)) {
            logger.info("User credentials invalid, unable to create user {} :", user.getUsername());
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        createdUser = userService.createUser(user);
        logger.info("User created: {}", user);
        return new ResponseEntity<>(createdUser, HttpStatus.OK);
    }

    @PutMapping("/superadmin/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable(value = "id") Long id) {
        User updatedUser = userService.findById(id);
        if (updatedUser == null) {
            logger.info("Unable to find user with id {} :", id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        if (!userService.validateUser(user)) {
            logger.info("User credentials invalid, unable to update user with id {} :", id);
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        updatedUser = userService.updateUser(user, id);
        logger.info("User updated: {}", updatedUser.getUsername());
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @PostMapping("/authenticate")
    public Object loginUser() {
        String logedUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        User logedUser = userService.findByUsername(logedUserName);
        logger.info("User with {} username has loged in", logedUserName);

        return ResponseEntity.ok(logedUser);
    }

    @PutMapping("/changePassword/{id}")
    public ResponseEntity<User> changeUserPassword(@RequestBody User user, @PathVariable(value = "id") Long id) {
        User updatedUser = userService.findById(id);
        Authentication logedUser = SecurityContextHolder.getContext().getAuthentication();
        if (updatedUser == null) {
            logger.info("Unable to find user with id {} :", id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        if (logedUser instanceof AnonymousAuthenticationToken) {
            logger.error("Unable to change password, you are not loged on this machine");
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        if (logedUser instanceof UsernamePasswordAuthenticationToken) {
            org.springframework.security.core.userdetails.User logedUserDetails = ((org.springframework.security.core.userdetails.User) logedUser.getPrincipal());
            if (!logedUserDetails.getUsername().equals(updatedUser.getUsername())) {
                logger.error("Unable to change password, you are not loged as that user on this machine");
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        }
        try {
            updatedUser.setPassword(user.getPassword());
            userService.updateUserPassword(updatedUser, id);
            logger.info("User has {} changed password", updatedUser.getUsername());
        } catch (Exception e) {
            logger.error("Can't change invalid password for user {}", updatedUser.getUsername());
        }
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @PutMapping(value = "/superadmin/delete/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable(value = "id") Long id) {
        User deletedUser = userService.findById(id);
        if (deletedUser == null) {
            logger.info("Unable to find user with id {} :", id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        deletedUser = userService.deleteUser(deletedUser);
        logger.info("User deleted: {}", deletedUser);
        return new ResponseEntity<>(deletedUser, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/superadmin/deletePermanently/{id}")
    public ResponseEntity<Void> deleteUserPermanently(@PathVariable(value = "id") Long id) {
        User deletedUser = userService.findById(id);
        if (deletedUser == null) {
            logger.info("Unable to find user with id {} :", id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        userService.deletePermanentlyUser(id);
        logger.info("User deleted permanently: {}", deletedUser);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
