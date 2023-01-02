package com.api.springrest.controller;

import com.api.springrest.dto.PhoneRequestDto;
import com.api.springrest.exception.BadRequestException;
import com.api.springrest.model.entity.Phone;
import com.api.springrest.service.PhoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/api/v1/phones")
@RequiredArgsConstructor
public class PhoneController {

    private final PhoneService service;

    @GetMapping("/")
    public ResponseEntity<?> findPhonesByUserId(@PathParam(value = "idUser") Long idUser) {
        try {
            return new ResponseEntity<List<Phone>>(service.findPhonesByUserId(idUser), HttpStatus.OK);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> save(@RequestBody @Valid PhoneRequestDto dto) {
        try {
            return new ResponseEntity<>(service.save(dto), HttpStatus.CREATED);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePhone(@PathVariable Long id) {
        try {
            service.deletePhone(id);
            return new ResponseEntity<>("Phone deleted with success.", HttpStatus.OK);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/")
    public ResponseEntity<?> updatePhone(@RequestBody @Valid PhoneRequestDto dto) {
        try {
            return new ResponseEntity<Phone>(service.update(dto), HttpStatus.OK);
        }catch (BadRequestException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
