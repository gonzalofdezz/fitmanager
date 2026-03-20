package com.gonzalofdezz.fitmanager.room.infrastructure;

import com.gonzalofdezz.fitmanager.room.application.usecases.RoomService;
import com.gonzalofdezz.fitmanager.room.domain.entity.Room;
import com.gonzalofdezz.fitmanager.room.infrastructure.input.rest.dto.CreateRoomRequest;
import com.gonzalofdezz.fitmanager.room.infrastructure.input.rest.dto.RoomResponse;
import com.gonzalofdezz.fitmanager.room.infrastructure.input.rest.dto.UpdateRoomRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RoomResponse create(@Valid @RequestBody CreateRoomRequest request) {
        return toResponse(roomService.create(request.getGymId(), request.getName(), request.getCapacity()));
    }

    @GetMapping
    public List<RoomResponse> getAll() {
        return roomService.getAll().stream().map(this::toResponse).toList();
    }

    @GetMapping("/{id}")
    public RoomResponse getById(@PathVariable Long id) {
        return toResponse(roomService.getById(id));
    }

    @PutMapping("/{id}")
    public RoomResponse update(@PathVariable Long id, @Valid @RequestBody UpdateRoomRequest request) {
        return toResponse(roomService.update(id, request.getGymId(), request.getName(), request.getCapacity()));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        roomService.delete(id);
    }

    private RoomResponse toResponse(Room room) {
        return new RoomResponse(room.getId(), room.getGymId(), room.getName(), room.getCapacity());
    }
}