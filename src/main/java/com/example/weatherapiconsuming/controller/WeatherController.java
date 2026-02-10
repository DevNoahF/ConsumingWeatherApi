package com.example.weatherapiconsuming.controller;

import com.example.weatherapiconsuming.controller.dto.WeatherRequestDTO;
import com.example.weatherapiconsuming.controller.dto.WeatherResponseDTO;
import com.example.weatherapiconsuming.infra.exceptions.ErrorJsonApiResponseException;
import com.example.weatherapiconsuming.service.WeatherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
@RequiredArgsConstructor


public class WeatherController {
    private final WeatherService weatherService;

    @Operation(summary = "Send a request for api weather, and return a response by DTO")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok - return the specifics weather api by DTO"),
            @ApiResponse(responseCode = "404", description = "not found - the api weather have a error, please try change the api key")
    })
    @PostMapping
    public ResponseEntity<WeatherResponseDTO> consultarDados(@Parameter(name = "dto", description = "send the city, state, country, init date, final date") @RequestBody WeatherRequestDTO dto){
        try {
            return ResponseEntity.ok(weatherService.buscarClima(dto));
        } catch (ErrorJsonApiResponseException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
