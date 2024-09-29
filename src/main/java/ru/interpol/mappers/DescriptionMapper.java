package ru.interpol.mappers;

import org.mapstruct.Mapper;
import ru.interpol.domain.dto.DescriptionDto;
import ru.interpol.domain.entity.DescriptionEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DescriptionMapper {
    DescriptionDto descriptionEntityToDto(DescriptionEntity descriptionEntity);
    List<DescriptionDto> descriptionsEntityToDto(List<DescriptionEntity> descriptionsEntity);
    DescriptionEntity dtoToDescriptionEntity(DescriptionDto descriptionDto);
}
