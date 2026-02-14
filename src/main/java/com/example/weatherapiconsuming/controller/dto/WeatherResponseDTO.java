package com.example.weatherapiconsuming.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.io.Serializable;
import java.util.List;

@Builder
public record WeatherResponseDTO(@JsonProperty("address") String adress,
                                 @JsonProperty("timezone") String timezone,
                                 @JsonProperty("days") List<WeatherDaysListResponseDTO> days) implements Serializable {
}
