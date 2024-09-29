package ru.interpol.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.interpol.domain.dto.DescriptionDto;
import ru.interpol.domain.entity.DescriptionEntity;
import ru.interpol.domain.entity.DeveloperEntity;
import ru.interpol.mappers.DescriptionMapper;
import ru.interpol.repositories.DescriptionRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DescriptionServiceImpl implements DescriptionService {
    private final DescriptionRepository descriptionRepository;
    private final DescriptionMapper descriptionMapper;

    @Override
    public DescriptionDto createDescription(DescriptionDto descriptionDto) {
        DescriptionEntity descriptionEntity = descriptionMapper.dtoToDescriptionEntity(descriptionDto);
        descriptionEntity = descriptionRepository.saveAndFlush(descriptionEntity);
        return descriptionMapper.descriptionEntityToDto(descriptionEntity);
    }

    @Override
    public DescriptionDto updateDescription(DescriptionDto descriptionDto) {
        DescriptionEntity descriptionEntity = descriptionMapper.dtoToDescriptionEntity(descriptionDto);
        descriptionEntity = descriptionRepository.saveAndFlush(descriptionEntity);
        return descriptionMapper.descriptionEntityToDto(descriptionEntity);
    }

    @Override
    public DescriptionDto getDescription(Long id) {
        Optional<DescriptionEntity> descriptionEntity = descriptionRepository.findById(id);
        if (descriptionEntity.isPresent()) {
            return descriptionMapper.descriptionEntityToDto(descriptionEntity.get());
        }
        return null;
    }

    @Override
    public List<DescriptionDto> getAllDescriptions() {
        return descriptionMapper.descriptionsEntityToDto(descriptionRepository.findAll().stream().sorted(Comparator.comparing(DescriptionEntity::getId)).toList());
    }

    @Override
    public void deleteDescription(Long id) {
        descriptionRepository.deleteById(id);
    }
}
