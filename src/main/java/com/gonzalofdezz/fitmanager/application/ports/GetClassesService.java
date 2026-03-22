package com.gonzalofdezz.fitmanager.application.ports;

import com.gonzalofdezz.fitmanager.application.ports.output.LoadClassesOutputPort;
import com.gonzalofdezz.fitmanager.application.usecases.GetClassesUseCase;
import com.gonzalofdezz.fitmanager.domain.entity.GymClass;

import java.util.List;

public class GetClassesService implements GetClassesUseCase {

    private final LoadClassesOutputPort loadClassesOutputPort;

    public GetClassesService(LoadClassesOutputPort loadClassesPort) {
        this.loadClassesOutputPort = loadClassesPort;
    }

    @Override
    public List<GymClass> execute() {
        return loadClassesOutputPort.findAll();
    }
}
