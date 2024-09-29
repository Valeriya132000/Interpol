package ru.interpol.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class CriminalPerson  implements Serializable {
    @NotNull
    private String firstName; // Имя
    @NotNull
    private String lastName; // Фамилия
    private String nickName; // Кличка
    private Integer growth; // Рост
    private String hairColor; // Цвет волос
    private String eyeColor; // Цвет глаз
    private String specialSings; // Особые приметы
    private String nationality; // Национальность
    private LocalDate dateBirth; // Дата рождения
    private String placeBirth; // Место рождения
    private String lastResidence; // Последняя резиденция
    private String languages; // Языки
    private String criminalProfession; // Криминальная профессия
}
