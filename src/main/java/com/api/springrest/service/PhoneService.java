package com.api.springrest.service;

import com.api.springrest.dto.PhoneRequestDto;
import com.api.springrest.exception.BadRequestException;
import com.api.springrest.model.entity.Phone;
import com.api.springrest.model.entity.User;
import com.api.springrest.model.repository.PhoneRepository;
import com.api.springrest.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PhoneService {

    private final UserRepository userRepository;
    private final PhoneRepository repository;

    @Transactional
    public Phone save(@Valid PhoneRequestDto dto) {
        User user = userRepository.findById(dto.getIdUser())
                .orElseThrow(() -> new BadRequestException("User not found with this id."));
        Phone phone = dto.convert(dto);
        phone.setUser(user);
        return repository.save(phone);
    }

    @Transactional
    public Phone update(PhoneRequestDto dto) {
        return repository.findById(dto.getId()).map(entity -> {
            Phone phone = dto.convert(dto);
            phone.setId(entity.getId());
            phone.setUser(userRepository.findById(dto.getIdUser()).orElseThrow(() -> new BadRequestException("Not found a user with this id.")));
            return repository.save(phone);
        }).orElseThrow(() -> new BadRequestException("Phone not found."));
    }

    @Transactional(readOnly = true)
    public List<Phone> findPhonesByUser(User user) {
        return repository.findPhonesByUser(user);
    }

    @Transactional(readOnly = true)
    public List<Phone> findPhonesByUserId(Long id) {
        if (id == null) {
            throw new BadRequestException("User id cannot be null or empty.");
        }

        User user = userRepository.findById(id).orElseThrow(() -> new BadRequestException("Not found any User with this id."));
        return this.findPhonesByUser(user);
    }

    @Transactional
    public void deletePhone(Long id) {
        Phone phone = repository.findById(id).orElseThrow(() -> new BadRequestException("Not found a phone with this id."));
        repository.delete(phone);
    }
}
