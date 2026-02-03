package com.example.weatherapiconsuming.service;

import com.example.weatherapiconsuming.dto.RequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
@Slf4j
public class WeatherResponseService {

    private final RestTemplate restTemplate;

    private String apiKey;

    public WeatherResponseService(RestTemplate restTemplate, @Value("${API_KEY}") String apiKey) {
        this.restTemplate = restTemplate;
        this.apiKey = apiKey;
        log.info("API Key: {}", apiKey);
    }

    public String formatUrl(RequestDTO dto) {
        var cidade = URLEncoder.encode(dto.cidade(), StandardCharsets.UTF_8);
        var estado = URLEncoder.encode(dto.estado(), StandardCharsets.UTF_8); // integrar api para buscar cidade por estado
        var pais = URLEncoder.encode(dto.pais(), StandardCharsets.UTF_8); // colocar pais como enum
        var periodoInicial = dto.periodoInicial();
        var periodoFinal = dto.periodoFinal();


        URI uri = UriComponentsBuilder
                .fromUriString("https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline")
                .pathSegment(cidade + "," + estado + "," + pais)
                .pathSegment(periodoInicial.toString())
                .pathSegment(periodoFinal.toString())
                .queryParam("key", apiKey)
                .queryParam("unitGroup", "metric")
                .build()
                .toUri();
        log.info("URI gerada: {}", uri);
        return restTemplate.getForObject(uri, String.class);
    }
}
