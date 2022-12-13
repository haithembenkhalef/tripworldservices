package com.tripworld.rooms;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/rooms")
public class RoomController {

    private final RoomService roomService;

    @GetMapping("{id}")
    ResponseEntity<?> getRoom(@PathVariable Long id) {
        Room room = roomService.findById(id);
        return ResponseEntity.ok(room);
    }

    @DeleteMapping("{id}")
    ResponseEntity<?> deleteRoom(@PathVariable Long id) {
        roomService.deleteRoomByID(id);
        return ResponseEntity.noContent().build();
    }
}
