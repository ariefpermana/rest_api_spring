package com.ariefpermana.user_management.controllers;

import com.ariefpermana.user_management.entities.User;
import com.ariefpermana.user_management.entities.UserRole;
import com.ariefpermana.user_management.repositories.UserRepository;
import com.ariefpermana.user_management.repositories.UserRoleRepository;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class UserControllers {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;

    @GetMapping("/users")
    public ResponseEntity<List<User>> findAll() {
        try {
            List<User> users;

            users = userRepository.findAll();

            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/roleUser/{roleId}")
    public ResponseEntity<UserRole> findByIdRole(
            @PathVariable("roleId") Integer id) {

        Optional<UserRole> roleId = userRoleRepository.findById(id);

        if (roleId.isPresent()) {
            return new ResponseEntity<>(roleId.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> findById(
            @PathVariable("id") Long id) {

        Optional<User> customerData = userRepository.findById(id);

        if (customerData.isPresent()) {
            return new ResponseEntity<>(customerData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/users")
    public ResponseEntity<User> create(
            @RequestBody User user) {
        try {
            User newUser = new User();
            newUser.setUser_id(user.getUser_id());
            newUser.setName(user.getName());
            newUser.setJenis_kelamin(user.getJenis_kelamin());
            newUser.setEmail(user.getEmail());
            newUser.setAlamat(user.getAlamat());
            newUser.setRole_id(user.getRole_id());
            newUser.setCreated_on(user.getCreated_on());
            newUser.setLastupdated(user.getLastupdated());
            return new ResponseEntity<>(userRepository.save(newUser), HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/users/{user_id}")
    public ResponseEntity<User> update(
            @PathVariable("user_id") Long id,
            @RequestBody User user) {

        Optional<User> userData = userRepository.findById(id);

        if (userData.isPresent()) {
            User updateUser = userData.get();
            updateUser.setUser_id(user.getUser_id());
            updateUser.setName(user.getName());
            updateUser.setJenis_kelamin(user.getJenis_kelamin());
            updateUser.setEmail(user.getEmail());
            updateUser.setAlamat(user.getAlamat());
            updateUser.setRole_id(user.getRole_id());
            updateUser.setCreated_on(user.getCreated_on());
            updateUser.setLastupdated(user.getLastupdated());
            return new ResponseEntity<>(userRepository.save(updateUser), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/users/{user_id}")
    public ResponseEntity<HttpStatus> delete(
            @PathVariable("user_id") Long id) {
        try {
            userRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}