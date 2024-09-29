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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.interpol.domain.dto.CriminalPersonDto;
import ru.interpol.domain.dto.InterpolDto;
import ru.interpol.services.InterpolService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/interpol")
public class InterpolController implements InterpolApi {
    private final InterpolService interpolService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<InterpolDto> createInterpol(@Valid @RequestBody InterpolDto interpolDto) {
        return ResponseEntity.ok(interpolService.createInterpol(interpolDto));
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<InterpolDto> updateInterpol(@Valid @RequestBody InterpolDto interpolDto) {
        return ResponseEntity.ok(interpolService.updateInterpol(interpolDto));
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<InterpolDto> getInterpol(@PathVariable Long id) {
        return ResponseEntity.ok(interpolService.getInterpol(id));
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<InterpolDto>> getAllInterpols() {
        return ResponseEntity.ok(interpolService.getAllInterpols());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity deleteInterpol(@PathVariable Long id) {
        interpolService.deleteInterpol(id);
        return ResponseEntity.ok("");
    }

    @DeleteMapping("/move-to-archive/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<InterpolDto> moveToInterpolArchive(@PathVariable Long id) {
        return ResponseEntity.ok(interpolService.moveToInterpolArchive(id));
    }

    @GetMapping("/archive")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<InterpolDto>> getAllInterpolsArchive() {
        return ResponseEntity.ok(interpolService.getAllInterpolsArchive());
    }

    @PostMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<InterpolDto>> searchCriminalPerson(@Valid @RequestBody CriminalPersonDto criminalPersonDto,
                                                            @RequestParam(value = "searchInArchive", defaultValue = "false") Boolean searchInArchive) {
        return ResponseEntity.ok(interpolService.searchCriminalPerson(criminalPersonDto, searchInArchive));
    }

}
