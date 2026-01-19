package com.gonzalofdezz.fitmanager.infrastructure.adapters.input.rest;

import java.time.Instant;

public record ErrorResponse(
        Instant timestamp,
        int status,
        String error,
        String message,
        String path
) {
}
