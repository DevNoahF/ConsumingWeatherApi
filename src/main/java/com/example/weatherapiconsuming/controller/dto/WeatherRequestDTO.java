package com.example.weatherapiconsuming.controller.dto;

import java.time.LocalDate;

public record WeatherRequestDTO(String city,
                                String state,
                                String country,
                                LocalDate initDate,
                                LocalDate finalDate st) {
}
