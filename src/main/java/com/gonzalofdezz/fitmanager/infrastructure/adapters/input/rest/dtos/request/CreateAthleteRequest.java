package com.gonzalofdezz.fitmanager.infrastructure.adapters.input.rest.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record CreateAthleteRequest(
        @NotBlank String name,
        @NotNull @PositiveOrZero Integer age
) {
}
