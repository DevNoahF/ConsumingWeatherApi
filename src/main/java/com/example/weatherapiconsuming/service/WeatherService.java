package com.example.weatherapiconsuming.service;

import com.example.weatherapiconsuming.controller.dto.WeatherRequestDTO;
import com.example.weatherapiconsuming.controller.dto.WeatherResponseDTO;
import com.example.weatherapiconsuming.infra.exceptions.ErrorJsonApiResponseException;
import com.example.weatherapiconsuming.utility.WeatherFormatterUrl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Service
@Slf4j
@RequiredArgsConstructor
public class WeatherService {
    private final WeatherFormatterUrl weatherFormatterUrl;
    private final ObjectMapper objectMapper;


    // 'value' é o nome do "balde" no Redis
    // 'key' define como identificar cada busca (ex: pelo nome da cidade)
    @Cacheable( value = "weatherCache", key = "#dto")
    public WeatherResponseDTO buscarClima(WeatherRequestDTO dto) {
        var apiResponse = weatherFormatterUrl.formatUrl(dto);
        try {
            return objectMapper.readValue(apiResponse, WeatherResponseDTO.class);
        } catch (ErrorJsonApiResponseException e) {
            log.error("Erro ao processar a API Response: {}", e.getMessage());
            throw new ErrorJsonApiResponseException("Erro ao processar a API Response: " + e.getMessage());
        }
    }

}
