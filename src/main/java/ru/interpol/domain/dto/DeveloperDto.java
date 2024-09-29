package ru.interpol.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeveloperDto {
    private Long id;
    private DeveloperPersonDto developerPerson;
}
