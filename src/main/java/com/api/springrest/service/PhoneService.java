package com.api.springrest.service;

import com.api.springrest.dto.PhoneRequestDto;
import com.api.springrest.exception.BadRequestException;
import com.api.springrest.model.entity.Phone;
import com.api.springrest.model.entity.User;
import com.api.springrest.model.repository.PhoneRepository;
import com.api.springrest.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
@RequiredArgsConstructor
public class PhoneService {

    private final UserRepository userRepository;
    private final PhoneRepository repository;

    public Phone save(@Valid PhoneRequestDto dto) {
        User user = userRepository.findById(dto.getIdUser())
                .orElseThrow(() -> new BadRequestException("User not found with this id."));
        Phone phone = dto.convert(dto);
        phone.setUser(user);
        return repository.save(phone);
    }

    public Phone update(Long id) {
        Phone phone = repository.findById(id).orElseThrow(() -> new BadRequestException("Phone not found with this id."));
        return phone;
    }
}
