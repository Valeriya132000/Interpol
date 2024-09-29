package ru.interpol.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeveloperPersonDto {
    private String firstName; // Имя
    private String lastName; // Фамилия
    private String phone; // Телефон
    private String email; // email
}
