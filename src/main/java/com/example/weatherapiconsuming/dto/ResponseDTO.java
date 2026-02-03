package com.example.weatherapiconsuming.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ResponseDTO(@JsonProperty("address") String adress,
                          @JsonProperty("timezone") String timezone,
                          @JsonProperty("days") List<DaysListResponseDTO> days) {
}
