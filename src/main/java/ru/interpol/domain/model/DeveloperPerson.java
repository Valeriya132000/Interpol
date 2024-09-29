package ru.interpol.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class DeveloperPerson {
    @NotNull
    private String firstName; // Имя
    @NotNull
    private String lastName; // Фамилия
    private String phone; // Телефон
    @Email
    private String email; // email
}
