package ru.interpol.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CriminalPersonDto {
    private String firstName; // Имя
    private String lastName; // Фамилия
    private String nickName; // Кличка
    private Integer growth; // Рост
    private String hairColor; // Цвет волос
    private String eyeColor; // Цвет глаз
    private String specialSings; //
    private String nationality; // Национальность
    private LocalDate dateBirth; // Дата рождения
    private String placeBirth; // Место рождения
    private String lastResidence; // Последняя резиденция
    private String languages; // Языки
    private String criminalProfession; // Криминальная профессия
}
