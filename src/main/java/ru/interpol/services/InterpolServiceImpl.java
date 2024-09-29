package ru.interpol.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.interpol.domain.dto.CriminalPersonDto;
import ru.interpol.domain.dto.InterpolDto;
import ru.interpol.domain.entity.InterpolArchiveEntity;
import ru.interpol.domain.entity.InterpolEntity;
import ru.interpol.mappers.InterpolMapper;
import ru.interpol.repositories.InterpolArchiveRepository;
import ru.interpol.repositories.InterpolRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class InterpolServiceImpl implements InterpolService {
    private final InterpolRepository interpolRepository;
    private final InterpolArchiveRepository interpolArchiveRepository;
    private final InterpolMapper interpolMapper;

    @Override
    public InterpolDto createInterpol(InterpolDto interpolDto) {
        InterpolEntity interpolEntity = interpolMapper.dtoToInterpolEntity(interpolDto);
        interpolEntity = interpolRepository.saveAndFlush(interpolEntity);
        return interpolMapper.interpolEntityToDto(interpolEntity);
    }

    @Override
    public InterpolDto updateInterpol(InterpolDto interpolDto) {
        InterpolEntity interpolEntity = interpolMapper.dtoToInterpolEntity(interpolDto);
        interpolEntity = interpolRepository.saveAndFlush(interpolEntity);
        return interpolMapper.interpolEntityToDto(interpolEntity);
    }

    @Override
    public InterpolDto getInterpol(Long id) {
        Optional<InterpolEntity> interpolEntity = interpolRepository.findById(id);
        if (interpolEntity.isPresent()) {
            return interpolMapper.interpolEntityToDto(interpolEntity.get());
        }
        return null;
    }

    @Override
    public List<InterpolDto> getAllInterpols() {
        return interpolMapper.interpolsEntityToDto(interpolRepository.findAll().stream().sorted(Comparator.comparing(InterpolEntity::getId)).toList());
    }

    @Override
    public void deleteInterpol(Long id) {
        interpolRepository.deleteById(id);
    }

    @Override
    public InterpolDto moveToInterpolArchive(Long id) {
        Optional<InterpolEntity> interpolEntity = interpolRepository.findById(id);
        if (interpolEntity.isPresent()) {
            InterpolArchiveEntity interpolArchiveEntity = interpolMapper.interpolEntityToInterpolArchiveEntity(interpolEntity.get());
            interpolMapper.interpolArchiveEntityToDto(interpolArchiveRepository.saveAndFlush(interpolArchiveEntity));
            interpolRepository.deleteById(id);
        }
        return null;
    }

    @Override
    public List<InterpolDto> getAllInterpolsArchive() {
        return interpolMapper.interpolsArchiveEntityToDto(interpolArchiveRepository.findAll().stream().sorted(Comparator.comparing(InterpolArchiveEntity::getId)).toList());
    }

    @Override
    public List<InterpolDto> searchCriminalPerson(CriminalPersonDto criminalPersonDto, Boolean searchInArchive) {
        List<InterpolDto> interpolsDto = new ArrayList<>();

        List<InterpolEntity> interpolsEntity = interpolRepository.searchCriminalPerson(criminalPersonDto.getFirstName(),
                criminalPersonDto.getLastName(), criminalPersonDto.getNickName(), criminalPersonDto.getGrowth(),
                criminalPersonDto.getHairColor(), criminalPersonDto.getEyeColor(), criminalPersonDto.getSpecialSings(),
                criminalPersonDto.getNationality(), criminalPersonDto.getDateBirth(), criminalPersonDto.getPlaceBirth(),
                criminalPersonDto.getLastResidence(), criminalPersonDto.getLanguages(), criminalPersonDto.getCriminalProfession());
        interpolsDto.addAll(interpolMapper.interpolsEntityToDto(interpolsEntity.stream().sorted(Comparator.comparing(InterpolEntity::getId)).toList()));

        if (searchInArchive) {
             List<InterpolArchiveEntity> interpolsArchiveEntity = interpolArchiveRepository.searchCriminalPerson(criminalPersonDto.getFirstName(),
                    criminalPersonDto.getLastName(), criminalPersonDto.getNickName(), criminalPersonDto.getGrowth(),
                    criminalPersonDto.getHairColor(), criminalPersonDto.getEyeColor(), criminalPersonDto.getSpecialSings(),
                    criminalPersonDto.getNationality(), criminalPersonDto.getDateBirth(), criminalPersonDto.getPlaceBirth(),
                    criminalPersonDto.getLastResidence(), criminalPersonDto.getLanguages(), criminalPersonDto.getCriminalProfession());
            interpolsDto.addAll(interpolMapper.interpolsArchiveEntityToDto(interpolsArchiveEntity.stream().sorted(Comparator.comparing(InterpolArchiveEntity::getId)).toList()));
        }

        return interpolsDto;
    }

    @Override
    public List<InterpolDto> searchWithFilter(String filterString) {
        if ((filterString == null) || filterString.isBlank()) {
            return getAllInterpols();
        } else {
            String filter = "%" + filterString + "%";
            CriminalPersonDto criminalPersonDto = CriminalPersonDto.builder()
                    .firstName(filter)
                    .lastName(filter)
                    .nickName(filter)
                    .hairColor(filter)
                    .eyeColor(filter)
                    .specialSings(filter)
                    .nationality(filter)
                    .placeBirth(filter)
                    .lastResidence(filter)
                    .languages(filter)
                    .criminalProfession(filter)
                    .build();
            return searchCriminalPerson(criminalPersonDto, false);
        }
    }

    @Override
    public List<InterpolDto> searchArchiveCriminalPerson(CriminalPersonDto criminalPersonDto) {
        List<InterpolDto> interpolsDto = new ArrayList<>();

        List<InterpolArchiveEntity> interpolsArchiveEntity = interpolArchiveRepository.searchCriminalPerson(criminalPersonDto.getFirstName(),
                criminalPersonDto.getLastName(), criminalPersonDto.getNickName(), criminalPersonDto.getGrowth(),
                criminalPersonDto.getHairColor(), criminalPersonDto.getEyeColor(), criminalPersonDto.getSpecialSings(),
                criminalPersonDto.getNationality(), criminalPersonDto.getDateBirth(), criminalPersonDto.getPlaceBirth(),
                criminalPersonDto.getLastResidence(), criminalPersonDto.getLanguages(), criminalPersonDto.getCriminalProfession());
        interpolsDto.addAll(interpolMapper.interpolsArchiveEntityToDto(interpolsArchiveEntity.stream().sorted(Comparator.comparing(InterpolArchiveEntity::getId)).toList()));

        return interpolsDto;
    }

    @Override
    public List<InterpolDto> searchArchiveWithFilter(String filterString) {
        if ((filterString == null) || filterString.isBlank()) {
            return getAllInterpolsArchive();
        } else {
            String filter = "%" + filterString + "%";
            CriminalPersonDto criminalPersonDto = CriminalPersonDto.builder()
                    .firstName(filter)
                    .lastName(filter)
                    .nickName(filter)
                    .hairColor(filter)
                    .eyeColor(filter)
                    .specialSings(filter)
                    .nationality(filter)
                    .placeBirth(filter)
                    .lastResidence(filter)
                    .languages(filter)
                    .criminalProfession(filter)
                    .build();
            return searchArchiveCriminalPerson(criminalPersonDto);
        }
    }
}
