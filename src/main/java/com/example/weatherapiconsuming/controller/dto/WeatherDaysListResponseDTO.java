package com.example.weatherapiconsuming.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public record WeatherDaysListResponseDTO(@JsonProperty("tempmax") String tempMax,
                                         @JsonProperty("tempmin") String tempMin,
                                         @JsonProperty("temp") String temp,
                                         @JsonProperty("datetime") String dateTime,
                                         @JsonProperty("humidity") String humidity,
                                         @JsonProperty("conditions") String conditions) implements Serializable {
}
