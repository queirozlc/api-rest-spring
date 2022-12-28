package com.api.springrest.controller;

import com.api.springrest.dto.UserPostRequest;
import com.api.springrest.exception.BadRequestException;
import com.api.springrest.model.entity.User;
import com.api.springrest.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {

    private UserService service;

    @GetMapping("/{id}")
    public ResponseEntity<?> findUserById(@PathVariable(value = "id") Long id) {
        try {
            User user = service.findById(id).get();
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> listUsers() {
        try {
            List<User> listUsers = service.findAll();
            return new ResponseEntity<>(listUsers, HttpStatus.OK);

        } catch (BadRequestException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<User> save(@RequestBody UserPostRequest dto) {
        User userCreated = service.save(dto);
        return new ResponseEntity<>(userCreated, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody UserPostRequest dto) {
        try {
            User userUpdated = service.update(dto);
            return new ResponseEntity<>(userUpdated, HttpStatus.OK);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return new ResponseEntity<>("User deleted with success.", HttpStatus.OK);

        } catch (BadRequestException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
