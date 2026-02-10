package com.example.weatherapiconsuming.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record WeatherResponseDTO(@JsonProperty("address") String adress,
                                 @JsonProperty("timezone") String timezone,
                                 @JsonProperty("days") List<DaysListResponseDTO> days) {
}
