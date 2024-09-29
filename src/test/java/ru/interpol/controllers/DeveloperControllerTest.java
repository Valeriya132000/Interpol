package ru.interpol.controllers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.interpol.AbstractIT;
import ru.interpol.domain.dto.DeveloperDto;
import ru.interpol.domain.dto.DeveloperPersonDto;

import java.util.List;

class DeveloperControllerTest extends AbstractIT {
    @BeforeEach
    public void setUp() {
        //
    }

    @AfterEach
    public void tearDown() {
        developerRepository.deleteAll();
    }

    @Test
    @DisplayName("createDeveloper")
    void createDeveloper() {
        DeveloperDto developerDto = createDeveloperDto("firstName", "lastName");
        ResponseEntity<DeveloperDto> response = testRestTemplate.exchange("/v1/developer", HttpMethod.POST, new HttpEntity<>(developerDto), DeveloperDto.class);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(response.getBody().getDeveloperPerson().getFirstName(), "firstName");
    }

    @Test
    @DisplayName("updateDeveloper")
    void updateDeveloper() {
        DeveloperDto developerDto = createDeveloperDto("firstName", "lastName");

        ResponseEntity<DeveloperDto> response = testRestTemplate.exchange("/v1/developer", HttpMethod.POST, new HttpEntity<>(developerDto), DeveloperDto.class);
        developerDto = response.getBody();
        developerDto.getDeveloperPerson().setFirstName("Измененное firstName");
        developerDto.getDeveloperPerson().setLastName("Измененное lastName");

        response = testRestTemplate.exchange("/v1/developer", HttpMethod.PUT, new HttpEntity<>(developerDto), DeveloperDto.class);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(response.getBody().getDeveloperPerson().getFirstName(), "Измененное firstName");
        Assertions.assertEquals(response.getBody().getDeveloperPerson().getLastName(), "Измененное lastName");
    }

    @Test
    @DisplayName("getDeveloper")
    void getDeveloper() {
        DeveloperDto developerDto = createDeveloperDto("firstName", "lastName");
        ResponseEntity<DeveloperDto> response = testRestTemplate.exchange("/v1/developer", HttpMethod.POST, new HttpEntity<>(developerDto), DeveloperDto.class);

        response = testRestTemplate.exchange("/v1/developer/{id}", HttpMethod.GET, new HttpEntity<>(developerDto), DeveloperDto.class, response.getBody().getId());

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(response.getBody().getDeveloperPerson().getFirstName(), "firstName");
    }

    @Test
    @DisplayName("getAllDevelopers")
    void getAllDevelopers() {
        DeveloperDto developerDto;
        developerDto = createDeveloperDto("firstName1", "lastName1");
        testRestTemplate.exchange("/v1/developer", HttpMethod.POST, new HttpEntity<>(developerDto), DeveloperDto.class);

        developerDto = createDeveloperDto("firstName3", "lastName2");
        testRestTemplate.exchange("/v1/developer", HttpMethod.POST, new HttpEntity<>(developerDto), DeveloperDto.class);

        ResponseEntity<List<DeveloperDto>> responses = testRestTemplate.exchange("/v1/developer", HttpMethod.GET, null, new ParameterizedTypeReference<List<DeveloperDto>>(){});

        Assertions.assertEquals(responses.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(responses.getBody().size(), 2);
    }

    @Test
    @DisplayName("deleteDeveloper")
    void deleteDeveloper() {
        DeveloperDto developerDto = createDeveloperDto("firstName", "lastName");
        ResponseEntity<DeveloperDto> response = testRestTemplate.exchange("/v1/developer", HttpMethod.POST, new HttpEntity<>(developerDto), DeveloperDto.class);
        testRestTemplate.exchange("/v1/developer/{id}", HttpMethod.DELETE, null, DeveloperDto.class, response.getBody().getId());
        response = testRestTemplate.exchange("/v1/developer/{id}", HttpMethod.GET, new HttpEntity<>(developerDto), DeveloperDto.class, response.getBody().getId());

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(response.getBody(), null);
    }

    private DeveloperDto createDeveloperDto(String firstName, String lastName) {
        return DeveloperDto.builder()
                .developerPerson(DeveloperPersonDto.builder()
                        .firstName(firstName)
                        .lastName(lastName)
                        .build())
                .build();
    }
}