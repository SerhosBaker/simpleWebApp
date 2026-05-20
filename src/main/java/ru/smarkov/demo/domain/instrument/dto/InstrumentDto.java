package ru.smarkov.demo.domain.instrument.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record InstrumentDto(
        @NotNull(message = "ID не может быть null") Long id,

        @NotBlank(message = "Тикер не может быть пустым") String ticker,

        @NotNull(message = "Цена обязательна")
        @Positive(message = "Цена должна быть больше 0") Double price
) {}
