package com.example.weatherapiconsuming.controller;

import com.example.weatherapiconsuming.dto.RequestDTO;
import com.example.weatherapiconsuming.dto.ResponseDTO;
import com.example.weatherapiconsuming.service.WeatherService;
import lombok.RequiredArgsConstructor;
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

    @PostMapping
    public ResponseEntity<ResponseDTO> consultarDados(@RequestBody RequestDTO dto){
        return ResponseEntity.ok(weatherService.buscarClima(dto));
    }
}
