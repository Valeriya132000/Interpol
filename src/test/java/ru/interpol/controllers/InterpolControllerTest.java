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
import org.springframework.web.util.UriComponentsBuilder;
import ru.interpol.AbstractIT;
import ru.interpol.domain.dto.CriminalPersonDto;
import ru.interpol.domain.dto.InterpolDto;

import java.time.LocalDate;
import java.util.List;

class InterpolControllerTest extends AbstractIT {
    @BeforeEach
    public void setUp() {
        //
    }

    @AfterEach
    public void tearDown() {
        interpolRepository.deleteAll();
        interpolArchiveRepository.deleteAll();
    }

    @Test
    @DisplayName("createInterpol")
    void createInterpol() {
        InterpolDto interpolDto = createInterpolDto("firstName", "lastName");
        ResponseEntity<InterpolDto> response = testRestTemplate.exchange("/v1/interpol", HttpMethod.POST, new HttpEntity<>(interpolDto), InterpolDto.class);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(response.getBody().getCriminalPerson().getFirstName(), "firstName");
    }

    @Test
    @DisplayName("updateInterpol")
    void updateInterpol() {
        InterpolDto interpolDto = createInterpolDto("firstName", "lastName");

        ResponseEntity<InterpolDto> response = testRestTemplate.exchange("/v1/interpol", HttpMethod.POST, new HttpEntity<>(interpolDto), InterpolDto.class);
        interpolDto = response.getBody();
        interpolDto.getCriminalPerson().setFirstName("Измененное firstName");
        interpolDto.getCriminalPerson().setLastName("Измененное lastName");

        response = testRestTemplate.exchange("/v1/interpol", HttpMethod.PUT, new HttpEntity<>(interpolDto), InterpolDto.class);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(response.getBody().getCriminalPerson().getFirstName(), "Измененное firstName");
        Assertions.assertEquals(response.getBody().getCriminalPerson().getLastName(), "Измененное lastName");
    }

    @Test
    @DisplayName("getInterpol")
    void getInterpol() {
        InterpolDto interpolDto = createInterpolDto("firstName", "lastName");
        ResponseEntity<InterpolDto> response = testRestTemplate.exchange("/v1/interpol", HttpMethod.POST, new HttpEntity<>(interpolDto), InterpolDto.class);

        response = testRestTemplate.exchange("/v1/interpol/{id}", HttpMethod.GET, new HttpEntity<>(interpolDto), InterpolDto.class, response.getBody().getId());

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(response.getBody().getCriminalPerson().getFirstName(), "firstName");
    }

    @Test
    @DisplayName("getAllInterpols")
    void getAllInterpols() {
        InterpolDto interpolDto;
        interpolDto = createInterpolDto("firstName1", "lastName1");
        testRestTemplate.exchange("/v1/interpol", HttpMethod.POST, new HttpEntity<>(interpolDto), InterpolDto.class);

        interpolDto = createInterpolDto("firstName3", "lastName2");
        testRestTemplate.exchange("/v1/interpol", HttpMethod.POST, new HttpEntity<>(interpolDto), InterpolDto.class);

        ResponseEntity<List<InterpolDto>> responses = testRestTemplate.exchange("/v1/interpol", HttpMethod.GET, null, new ParameterizedTypeReference<List<InterpolDto>>(){});

        Assertions.assertEquals(responses.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(responses.getBody().size(), 2);
    }

    @Test
    @DisplayName("deleteInterpol")
    void deleteInterpol() {
        InterpolDto interpolDto = createInterpolDto("firstName", "lastName");
        ResponseEntity<InterpolDto> response = testRestTemplate.exchange("/v1/interpol", HttpMethod.POST, new HttpEntity<>(interpolDto), InterpolDto.class);
        testRestTemplate.exchange("/v1/interpol/{id}", HttpMethod.DELETE, null, InterpolDto.class, response.getBody().getId());
        response = testRestTemplate.exchange("/v1/interpol/{id}", HttpMethod.GET, new HttpEntity<>(interpolDto), InterpolDto.class, response.getBody().getId());

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(response.getBody(), null);
    }

    @Test
    @DisplayName("moveToInterpolArchive")
    void moveToInterpolArchive() {
        InterpolDto interpolDto = createInterpolDto("firstName", "lastName");
        ResponseEntity<InterpolDto> response = testRestTemplate.exchange("/v1/interpol", HttpMethod.POST, new HttpEntity<>(interpolDto), InterpolDto.class);

        testRestTemplate.exchange("/v1/interpol/move-to-archive/{id}", HttpMethod.DELETE, null, InterpolDto.class, response.getBody().getId());
        response = testRestTemplate.exchange("/v1/interpol/{id}", HttpMethod.GET, new HttpEntity<>(interpolDto), InterpolDto.class, response.getBody().getId());

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(response.getBody(), null);

        ResponseEntity<List<InterpolDto>> responses = testRestTemplate.exchange("/v1/interpol/archive", HttpMethod.GET, null, new ParameterizedTypeReference<List<InterpolDto>>(){});

        Assertions.assertEquals(responses.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(responses.getBody().size(), 1);
    }

    @Test
    @DisplayName("getAllInterpolsArchive")
    void getAllInterpolsArchive() {
        InterpolDto interpolDto = createInterpolDto("firstName1", "lastName1");
        ResponseEntity<InterpolDto> response = testRestTemplate.exchange("/v1/interpol", HttpMethod.POST, new HttpEntity<>(interpolDto), InterpolDto.class);
        testRestTemplate.exchange("/v1/interpol/move-to-archive/{id}", HttpMethod.DELETE, null, InterpolDto.class, response.getBody().getId());

        interpolDto = createInterpolDto("firstName2", "lastName2");
        response = testRestTemplate.exchange("/v1/interpol", HttpMethod.POST, new HttpEntity<>(interpolDto), InterpolDto.class);
        testRestTemplate.exchange("/v1/interpol/move-to-archive/{id}", HttpMethod.DELETE, null, InterpolDto.class, response.getBody().getId());

        ResponseEntity<List<InterpolDto>> responses = testRestTemplate.exchange("/v1/interpol/archive", HttpMethod.GET, null, new ParameterizedTypeReference<List<InterpolDto>>(){});

        Assertions.assertEquals(responses.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(responses.getBody().size(), 2);
    }

    @Test
    @DisplayName("searchCriminalPerson")
    void searchCriminalPerson() {
        InterpolDto interpolDto = createInterpolDto("firstName1", "lastName1");
        ResponseEntity<InterpolDto> response = testRestTemplate.exchange("/v1/interpol", HttpMethod.POST, new HttpEntity<>(interpolDto), InterpolDto.class);
        testRestTemplate.exchange("/v1/interpol/move-to-archive/{id}", HttpMethod.DELETE, null, InterpolDto.class, response.getBody().getId());

        interpolDto = createInterpolDto("firstName2", "lastName2");
        testRestTemplate.exchange("/v1/interpol", HttpMethod.POST, new HttpEntity<>(interpolDto), InterpolDto.class);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(testRestTemplate.getRootUri() + "/v1/interpol/search")
                .queryParam("searchInArchive", true);

        ResponseEntity<List<InterpolDto>> responses = testRestTemplate.exchange(uriBuilder.toUriString(), HttpMethod.POST,
                new HttpEntity<>(CriminalPersonDto.builder().growth(18).build()),
                new ParameterizedTypeReference<List<InterpolDto>>(){});

        Assertions.assertEquals(responses.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(responses.getBody().size(), 2);
    }

    private InterpolDto createInterpolDto(String firstName, String lastName) {
        return InterpolDto.builder()
                .criminalPerson(CriminalPersonDto.builder()
                        .firstName(firstName)
                        .lastName(lastName)
                        .dateBirth(LocalDate.now())
                        .eyeColor("eyeColor")
                        .growth(18)
                        .languages("languages")
                        .nationality("nationality")
                        .nickName("nickName")
                        .build())
                .build();
    }
}