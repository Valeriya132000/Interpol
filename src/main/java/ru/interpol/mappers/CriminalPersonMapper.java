package ru.interpol.mappers;

import org.mapstruct.Mapper;
import ru.interpol.domain.dto.CriminalPersonDto;
import ru.interpol.domain.model.CriminalPerson;

@Mapper(componentModel = "spring")
public interface CriminalPersonMapper {
    CriminalPersonDto modelToDto(CriminalPerson criminalPerson);
    CriminalPerson dtoToModel(CriminalPersonDto criminalPersonDto);
}
