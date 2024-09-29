package ru.interpol.services;

import ru.interpol.domain.dto.DeveloperDto;

import java.util.List;

public interface DeveloperService {
    DeveloperDto createDeveloper(DeveloperDto developerDto);
    DeveloperDto updateDeveloper(DeveloperDto developerDto);
    DeveloperDto getDeveloper(Long id);
    List<DeveloperDto> getAllDevelopers();
    void deleteDeveloper(Long id);
}
