package ru.interpol.controllers.swagger;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.interpol.domain.dto.DescriptionDto;

import java.util.List;

@Tag(name = "3. Контроллер - описания сервиса")
public interface DescriptionApi {
    @Operation(
            summary = "Добавление описания",
            operationId = "createDescription")
    ResponseEntity<DescriptionDto> createDescription(DescriptionDto descriptionDto);

    @Operation(
            summary = "Изменение описания",
            operationId = "updateDescription")
    ResponseEntity<DescriptionDto> updateDescription(@Valid @RequestBody DescriptionDto descriptionDto);

    @Operation(
            summary = "Поиск описания",
            operationId = "getDescription")
    ResponseEntity<DescriptionDto> getDescription(@PathVariable Long id);

    @Operation(
            summary = "Поиск всех описаний",
            operationId = "getAllDescriptions")
    ResponseEntity<List<DescriptionDto>> getAllDescriptions();

    @Operation(
            summary = "Удаление описания",
            operationId = "deleteDescription")
    ResponseEntity deleteDescription(@PathVariable Long id);
}
