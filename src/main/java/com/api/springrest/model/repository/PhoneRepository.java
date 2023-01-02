package com.api.springrest.model.repository;

import com.api.springrest.model.entity.Phone;
import com.api.springrest.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {
    List<Phone>  findPhonesByUser(User user);

}
