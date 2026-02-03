package com.example.weatherapiconsuming.service;

import com.example.weatherapiconsuming.dto.RequestDTO;
import com.example.weatherapiconsuming.dto.ResponseDTO;
import com.example.weatherapiconsuming.infra.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tools.jackson.databind.ObjectMapper;

@Service
@Slf4j
@RequiredArgsConstructor
public class WeatherService {

    private final WeatherResponseService weatherResponseService;
    private final ObjectMapper objectMapper;



    public ResponseDTO buscarClima(RequestDTO dto) {
        var apiResponse = weatherResponseService.formatUrl(dto);
        log.info("API Response: {}", apiResponse);
        try {
            return objectMapper.readValue(apiResponse, ResponseDTO.class);
        } catch ( JsonProcessingException e) {
            log.error("Erro ao processar a API Response: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY,
                    "Nao foi possivel converter a resposta da API.", e);
        }
    }

}
