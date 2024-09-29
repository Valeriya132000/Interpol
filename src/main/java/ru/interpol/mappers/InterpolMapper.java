package ru.interpol.mappers;

import org.mapstruct.Mapper;
import ru.interpol.domain.dto.InterpolDto;
import ru.interpol.domain.entity.InterpolArchiveEntity;
import ru.interpol.domain.entity.InterpolEntity;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CriminalPersonMapper.class})
public interface InterpolMapper {
    InterpolDto interpolEntityToDto(InterpolEntity interpolEntity);
    List<InterpolDto> interpolsEntityToDto(List<InterpolEntity> interpolsEntity);
    InterpolEntity dtoToInterpolEntity(InterpolDto interpolDto);
    InterpolArchiveEntity interpolEntityToInterpolArchiveEntity(InterpolEntity interpolEntity);
    InterpolDto interpolArchiveEntityToDto(InterpolArchiveEntity interpolArchiveEntity);
    List<InterpolDto> interpolsArchiveEntityToDto(List<InterpolArchiveEntity> interpolsArchiveEntity);
}
