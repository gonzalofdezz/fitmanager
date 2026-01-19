package com.gonzalofdezz.fitmanager.infrastructure.adapters.input.rest.dtos.response;

import java.util.UUID;

public record AthleteResponse(UUID id, String name, Integer age) {
}
