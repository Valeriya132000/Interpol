package ru.interpol.services;

import ru.interpol.domain.dto.CriminalPersonDto;
import ru.interpol.domain.dto.InterpolDto;

import java.util.List;

public interface InterpolService {
    InterpolDto createInterpol(InterpolDto interpolDto);
    InterpolDto updateInterpol(InterpolDto interpolDto);
    InterpolDto getInterpol(Long id);
    List<InterpolDto> getAllInterpols();
    void deleteInterpol(Long id);
    InterpolDto moveToInterpolArchive(Long id);
    List<InterpolDto> getAllInterpolsArchive();
    List<InterpolDto> searchCriminalPerson(CriminalPersonDto criminalPersonDto, Boolean searchInArchive);
    List<InterpolDto> searchWithFilter(String filterString);
    List<InterpolDto> searchArchiveCriminalPerson(CriminalPersonDto criminalPersonDto);
    List<InterpolDto> searchArchiveWithFilter(String filterString);
}
