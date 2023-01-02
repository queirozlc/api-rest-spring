package com.api.springrest.dto;

import com.api.springrest.model.entity.Phone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneRequestDto {
    private Long id;
    @NotBlank(message = "The 'number' field is required.")
    private String number;
    @NotBlank(message = "The 'type' field is required.")
    private String type;
    @NotNull(message = "User id cannot be empty.")
    private Long idUser;

    public Phone convert(PhoneRequestDto dto) {
        return Phone
                .builder()
                .id(dto.getId())
                .number(dto.getNumber())
                .type(dto.getType())
                .build();
    }
}
