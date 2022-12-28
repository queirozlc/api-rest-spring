package com.api.springrest.controller;

import com.api.springrest.dto.PhoneRequestDto;
import com.api.springrest.exception.BadRequestException;
import com.api.springrest.service.PhoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/phones")
@RequiredArgsConstructor
public class PhoneController {

    private final PhoneService service;

    @PostMapping("/")
    public ResponseEntity<?> save(@RequestBody @Valid PhoneRequestDto dto) {
        try {
            return new ResponseEntity<>(service.save(dto), HttpStatus.CREATED);
        }catch (BadRequestException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
