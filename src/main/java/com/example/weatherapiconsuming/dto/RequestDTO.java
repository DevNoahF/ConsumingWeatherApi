package com.example.weatherapiconsuming.dto;

import java.time.LocalDate;

public record RequestDTO(String cidade,
                         String estado,
                         String pais,
                         LocalDate periodoInicial,
                         LocalDate periodoFinal) {
}
