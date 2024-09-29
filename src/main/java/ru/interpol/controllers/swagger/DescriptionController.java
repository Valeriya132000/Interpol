package ru.interpol.controllers.swagger;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.interpol.domain.dto.DescriptionDto;
import ru.interpol.services.DescriptionService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/description")
public class DescriptionController implements DescriptionApi {
    private final DescriptionService descriptionService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<DescriptionDto> createDescription(@Valid @RequestBody DescriptionDto descriptionDto) {
        return ResponseEntity.ok(descriptionService.createDescription(descriptionDto));
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<DescriptionDto> updateDescription(@Valid @RequestBody DescriptionDto descriptionDto) {
        return ResponseEntity.ok(descriptionService.updateDescription(descriptionDto));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<DescriptionDto> getDescription(@PathVariable Long id) {
        return ResponseEntity.ok(descriptionService.getDescription(id));
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<DescriptionDto>> getAllDescriptions() {
        return ResponseEntity.ok(descriptionService.getAllDescriptions());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity deleteDescription(@PathVariable Long id) {
        descriptionService.deleteDescription(id);
        return ResponseEntity.ok("");
    }

}
