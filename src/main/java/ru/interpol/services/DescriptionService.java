package ru.interpol.services;

import ru.interpol.domain.dto.DescriptionDto;

import java.util.List;

public interface DescriptionService {
    DescriptionDto createDescription(DescriptionDto descriptionDto);
    DescriptionDto updateDescription(DescriptionDto descriptionDto);
    DescriptionDto getDescription(Long id);
    List<DescriptionDto> getAllDescriptions();
    void deleteDescription(Long id);
}
