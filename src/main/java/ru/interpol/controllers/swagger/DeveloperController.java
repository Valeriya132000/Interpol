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
import ru.interpol.domain.dto.DeveloperDto;
import ru.interpol.services.DeveloperService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/developer")
public class DeveloperController implements DeveloperApi{
    private final DeveloperService developerService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<DeveloperDto> createDeveloper(@Valid @RequestBody DeveloperDto developerDto) {
        return ResponseEntity.ok(developerService.createDeveloper(developerDto));
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<DeveloperDto> updateDeveloper(@Valid @RequestBody DeveloperDto developerDto) {
        return ResponseEntity.ok(developerService.updateDeveloper(developerDto));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<DeveloperDto> getDeveloper(@PathVariable Long id) {
        return ResponseEntity.ok(developerService.getDeveloper(id));
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<DeveloperDto>> getAllDevelopers() {
        return ResponseEntity.ok(developerService.getAllDevelopers());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity deleteDeveloper(@PathVariable Long id) {
        developerService.deleteDeveloper(id);
        return ResponseEntity.ok("");
    }
}
