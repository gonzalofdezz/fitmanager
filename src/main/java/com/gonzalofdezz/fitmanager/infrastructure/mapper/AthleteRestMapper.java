package com.gonzalofdezz.fitmanager.infrastructure.mapper;

import com.gonzalofdezz.fitmanager.domain.entity.Athlete;
import com.gonzalofdezz.fitmanager.infrastructure.adapters.input.rest.dtos.response.AthleteResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AthleteRestMapper {
    AthleteResponse toResponse(Athlete athlete);
}
