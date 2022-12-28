package com.api.springrest.dto;

import com.api.springrest.model.entity.Phone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneRequestDto {
    private Long id;
    private String number;
    @NotNull(message = "User id cannot be empty.")
    private Long idUser;

    public Phone convert(PhoneRequestDto dto) {
        return Phone
                .builder()
                .id(dto.getId())
                .number(dto.getNumber())
                .build();
    }
}
