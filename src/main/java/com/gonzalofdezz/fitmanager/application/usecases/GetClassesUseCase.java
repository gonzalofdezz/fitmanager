package com.gonzalofdezz.fitmanager.application.usecases;

import com.gonzalofdezz.fitmanager.domain.entity.GymClass;

import java.util.List;

public interface GetClassesUseCase {

    List<GymClass> execute();
}
