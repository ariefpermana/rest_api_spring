package com.ariefpermana.user_management.controllers;

import com.ariefpermana.user_management.entities.User;
import com.ariefpermana.user_management.repositories.UserRepository;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserControllers {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public ResponseEntity<List<User>> findAll(
            @RequestParam(name = "name",
                    required = false,
                    defaultValue = "") String name) {
        try {
            List<User> users;
            if (StringUtils.hasText(name)) {
                users = userRepository.findByNameContaining(name);
            } else {
                users = userRepository.findAll();
            }

            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> findById(
            @PathVariable("id") String id) {

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
            newUser.setUsername(user.getUsername());
            newUser.setName(user.getName());
            newUser.setPassword(user.getPassword());
            newUser.setEmail(user.getEmail());
            newUser.setPrivelege(user.getPrivelege());
            System.out.println("masuk sini");
            newUser.setCreated_on(user.getCreated_on());
            System.out.println(newUser);
            return new ResponseEntity<>(userRepository.save(newUser), HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @PutMapping("/customers/{id}")
//    public ResponseEntity<Customer> update(
//            @PathVariable("id") String id,
//            @RequestBody Customer customer) {
//
//        Optional<Customer> customerData = customerRepository.findById(id);
//
//        if (customerData.isPresent()) {
//            Customer updatedCustomer = customerData.get();
//            updatedCustomer.setName(customer.getName());
//            updatedCustomer.setAddress(customer.getAddress());
//            updatedCustomer.setEmail(customer.getEmail());
//            updatedCustomer.setPhone(customer.getPhone());
//            return new ResponseEntity<>(customerRepository.save(updatedCustomer), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> delete(
            @PathVariable("user_id") String id) {
        try {
            userRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/customers")
    public ResponseEntity<HttpStatus> deleteAll() {
        try {
            userRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}