package ru.interpol.controllers.swagger;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.interpol.domain.dto.CriminalPersonDto;
import ru.interpol.domain.dto.InterpolDto;

import java.util.List;

@Tag(name = "1. Контроллер - приступники")
public interface InterpolApi {
    @Operation(
            summary = "Добавление приступника",
            operationId = "createInterpol")
    ResponseEntity<InterpolDto> createInterpol(InterpolDto interpolDto);

    @Operation(
            summary = "Изменение приступника",
            operationId = "updateInterpol")
    ResponseEntity<InterpolDto> updateInterpol(@Valid @RequestBody InterpolDto interpolDto);

    @Operation(
            summary = "Поиск приступника",
            operationId = "getInterpol")
    ResponseEntity<InterpolDto> getInterpol(@PathVariable Long id);

    @Operation(
            summary = "Поиск всех приступников",
            operationId = "getAllInterpols")
    ResponseEntity<List<InterpolDto>> getAllInterpols();

    @Operation(
            summary = "Удаление приступника",
            operationId = "deleteInterpol")
    ResponseEntity deleteInterpol(@PathVariable Long id);

    @Operation(
            summary = "Перемещение приступника в архив",
            operationId = "moveToInterpolArchive")
    ResponseEntity<InterpolDto> moveToInterpolArchive(@PathVariable Long id);

    @Operation(
            summary = "Поиск всех приступников в архиве",
            operationId = "getAllInterpolsArchive")
    ResponseEntity<List<InterpolDto>> getAllInterpolsArchive();

    @Operation(
            summary = "Поиск всех приступников по параметрам",
            operationId = "searchCriminalPerson")
    ResponseEntity<List<InterpolDto>> searchCriminalPerson(CriminalPersonDto criminalPersonDto, Boolean searchInArchive);
}
