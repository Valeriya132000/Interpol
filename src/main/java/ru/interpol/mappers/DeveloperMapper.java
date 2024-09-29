package ru.interpol.mappers;

import org.mapstruct.Mapper;
import ru.interpol.domain.dto.DeveloperDto;
import ru.interpol.domain.entity.DeveloperEntity;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CriminalPersonMapper.class})
public interface DeveloperMapper {
    DeveloperDto developerEntityToDto(DeveloperEntity developerEntity);
    List<DeveloperDto> developersEntityToDto(List<DeveloperEntity> developersEntity);
    DeveloperEntity dtoToDeveloperEntity(DeveloperDto developerDto);
}
