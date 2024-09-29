package ru.interpol.mappers;

import org.mapstruct.Mapper;
import ru.interpol.domain.dto.DeveloperPersonDto;
import ru.interpol.domain.model.DeveloperPerson;

@Mapper(componentModel = "spring")
public interface DeveloperPersonMapper {
    DeveloperPersonDto modelToDto(DeveloperPerson developerPerson);
    DeveloperPerson dtoToModel(DeveloperPersonDto developerPersonDto);
}
