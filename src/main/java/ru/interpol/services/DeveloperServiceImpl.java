package ru.interpol.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.interpol.domain.dto.DeveloperDto;
import ru.interpol.domain.entity.DeveloperEntity;
import ru.interpol.domain.entity.InterpolArchiveEntity;
import ru.interpol.mappers.DeveloperMapper;
import ru.interpol.repositories.DeveloperRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DeveloperServiceImpl implements DeveloperService {
    private final DeveloperRepository developerRepository;
    private final DeveloperMapper developerMapper;

    @Override
    public DeveloperDto createDeveloper(DeveloperDto developerDto) {
        DeveloperEntity developerEntity = developerMapper.dtoToDeveloperEntity(developerDto);
        developerEntity = developerRepository.saveAndFlush(developerEntity);
        return developerMapper.developerEntityToDto(developerEntity);
    }

    @Override
    public DeveloperDto updateDeveloper(DeveloperDto developerDto) {
        DeveloperEntity developerEntity = developerMapper.dtoToDeveloperEntity(developerDto);
        developerEntity = developerRepository.saveAndFlush(developerEntity);
        return developerMapper.developerEntityToDto(developerEntity);
    }

    @Override
    public DeveloperDto getDeveloper(Long id) {
        Optional<DeveloperEntity> developerEntity = developerRepository.findById(id);
        if (developerEntity.isPresent()) {
            return developerMapper.developerEntityToDto(developerEntity.get());
        }
        return null;
    }

    @Override
    public List<DeveloperDto> getAllDevelopers() {
        return developerMapper.developersEntityToDto(developerRepository.findAll().stream().sorted(Comparator.comparing(DeveloperEntity::getId)).toList());
    }

    @Override
    public void deleteDeveloper(Long id) {
        developerRepository.deleteById(id);
    }
}
