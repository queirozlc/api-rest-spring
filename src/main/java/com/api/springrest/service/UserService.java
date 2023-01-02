package com.api.springrest.service;

import com.api.springrest.dto.UserPostRequest;
import com.api.springrest.exception.BadRequestException;
import com.api.springrest.model.entity.User;
import com.api.springrest.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final PhoneService phoneService;


    public Optional<User> findById(Long id) {
        Optional<User> userFound = repository.findById(id);

        if (userFound.isEmpty()) {
            throw new BadRequestException("User not found.");
        }
        return userFound;
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        List<User> listUsers = repository.findAll();

        if (listUsers.isEmpty()) {
            throw new BadRequestException("Don't found any users registered on database.");
        }

        return listUsers;
    }

    @Transactional
    public User save(UserPostRequest dto) {
        User user = dto.convert(dto);
        return repository.save(user);
    }

    @Transactional
    public User update(UserPostRequest dto) {
        return findById(dto.getId()).map(entity -> {
            User user = dto.convert(dto);
            user.setId(entity.getId());
            user.setPhones(phoneService.findPhonesByUser(user));
            return repository.save(user);
        }).orElseThrow(() -> new BadRequestException("User not found."));
    }

    @Transactional
    public void delete(Long id) {
        User user = repository.findById(id).orElseThrow(() -> new BadRequestException("User not found to delete."));
        repository.delete(user);
    }
}
