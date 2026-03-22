package com.gonzalofdezz.fitmanager.application.ports.input;

import com.gonzalofdezz.fitmanager.application.ports.output.LoadClassesPort;
import com.gonzalofdezz.fitmanager.application.usecases.GetClassesUseCase;
import com.gonzalofdezz.fitmanager.domain.entity.GymClass;

import java.util.List;

public class GetClassesService implements GetClassesUseCase {

    private final LoadClassesPort loadClassesPort;

    public GetClassesService(LoadClassesPort loadClassesPort) {
        this.loadClassesPort = loadClassesPort;
    }

    @Override
    public List<GymClass> execute() {
        return loadClassesPort.findAll();
    }
}
