package com.example.weatherapiconsuming.utility;

import com.example.weatherapiconsuming.controller.dto.WeatherRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
@Slf4j
public class WeatherFormatterUrl {

    private final RestTemplate restTemplate;

    private String apiKey;

    public WeatherFormatterUrl(RestTemplate restTemplate, @Value("${API_KEY}") String apiKey) {
        this.restTemplate = restTemplate;
        this.apiKey = apiKey;
    }

    public String formatUrl(WeatherRequestDTO dto) {
        log.info("iniciando formatação da url");
        URI uri = UriComponentsBuilder
                .fromUriString("https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline")
                .pathSegment(dto.city() + "," + dto.state() + "," + dto.country())
                .pathSegment(dto.initDate().toString())
                .pathSegment(dto.finalDate().toString())
                .queryParam("key", apiKey)
                .build()
                .toUri();
        log.info("URL gerada: {}", uri);
        return restTemplate.getForObject(uri, String.class);
    }
}
