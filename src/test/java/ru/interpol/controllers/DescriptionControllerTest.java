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
import ru.interpol.domain.dto.DescriptionDto;

import java.util.List;

class DescriptionControllerTest extends AbstractIT {
    @BeforeEach
    public void setUp() {
        //
    }

    @AfterEach
    public void tearDown() {
        descriptionRepository.deleteAll();
    }

    @Test
    @DisplayName("createDescription")
    void createDescription() {
        DescriptionDto descriptionDto = DescriptionDto.builder().descriptionApp("Описание сервиса").build();
        ResponseEntity<DescriptionDto> response = testRestTemplate.exchange("/v1/description", HttpMethod.POST, new HttpEntity<>(descriptionDto), DescriptionDto.class);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(response.getBody().getDescriptionApp(), "Описание сервиса");
    }

    @Test
    @DisplayName("updateDescription")
    void updateDescription() {
        DescriptionDto descriptionDto = DescriptionDto.builder().descriptionApp("Описание сервиса").build();

        ResponseEntity<DescriptionDto> response = testRestTemplate.exchange("/v1/description", HttpMethod.POST, new HttpEntity<>(descriptionDto), DescriptionDto.class);
        descriptionDto = response.getBody();
        descriptionDto.setDescriptionApp("Измененное описание сервиса");

        response = testRestTemplate.exchange("/v1/description", HttpMethod.PUT, new HttpEntity<>(descriptionDto), DescriptionDto.class);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(response.getBody().getDescriptionApp(), "Измененное описание сервиса");
    }

    @Test
    @DisplayName("getDescription")
    void getDescription() {
        DescriptionDto descriptionDto = DescriptionDto.builder().descriptionApp("Описание сервиса").build();;
        ResponseEntity<DescriptionDto> response = testRestTemplate.exchange("/v1/description", HttpMethod.POST, new HttpEntity<>(descriptionDto), DescriptionDto.class);

        response = testRestTemplate.exchange("/v1/description/{id}", HttpMethod.GET, new HttpEntity<>(descriptionDto), DescriptionDto.class, response.getBody().getId());

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(response.getBody().getDescriptionApp(), "Описание сервиса");
    }

    @Test
    @DisplayName("getAllDescriptions")
    void getAllDescriptions() {
        DescriptionDto descriptionDto;
        descriptionDto = DescriptionDto.builder().descriptionApp("Описание сервиса 1").build();;
        testRestTemplate.exchange("/v1/description", HttpMethod.POST, new HttpEntity<>(descriptionDto), DescriptionDto.class);

        descriptionDto = DescriptionDto.builder().descriptionApp("Описание сервиса 2").build();;
        testRestTemplate.exchange("/v1/description", HttpMethod.POST, new HttpEntity<>(descriptionDto), DescriptionDto.class);

        ResponseEntity<List<DescriptionDto>> responses = testRestTemplate.exchange("/v1/description", HttpMethod.GET, null, new ParameterizedTypeReference<List<DescriptionDto>>(){});

        Assertions.assertEquals(responses.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(responses.getBody().size(), 2);
    }

    @Test
    @DisplayName("deleteDescription")
    void deleteDescription() {
        DescriptionDto descriptionDto = DescriptionDto.builder().descriptionApp("Описание сервиса").build();;
        ResponseEntity<DescriptionDto> response = testRestTemplate.exchange("/v1/description", HttpMethod.POST, new HttpEntity<>(descriptionDto), DescriptionDto.class);
        testRestTemplate.exchange("/v1/description/{id}", HttpMethod.DELETE, null, DescriptionDto.class, response.getBody().getId());
        response = testRestTemplate.exchange("/v1/description/{id}", HttpMethod.GET, new HttpEntity<>(descriptionDto), DescriptionDto.class, response.getBody().getId());

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(response.getBody(), null);
    }
}