package ru.interpol.controllers.swagger;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.interpol.domain.dto.DeveloperDto;

import java.util.List;

@Tag(name = "2. Контроллер - разработчика")
public interface DeveloperApi {
    @Operation(
            summary = "Добавление разработчика",
            operationId = "createDeveloper")
    ResponseEntity<DeveloperDto> createDeveloper(DeveloperDto developerDto);

    @Operation(
            summary = "Изменение разработчика",
            operationId = "updateDeveloper")
    ResponseEntity<DeveloperDto> updateDeveloper(@Valid @RequestBody DeveloperDto developerDto);

    @Operation(
            summary = "Поиск разработчика",
            operationId = "getDeveloper")
    ResponseEntity<DeveloperDto> getDeveloper(@PathVariable Long id);

    @Operation(
            summary = "Поиск всех разработчиков",
            operationId = "getAllDevelopers")
    ResponseEntity<List<DeveloperDto>> getAllDevelopers();

    @Operation(
            summary = "Удаление разработчика",
            operationId = "deleteDeveloper")
    ResponseEntity deleteDeveloper(@PathVariable Long id);
}
