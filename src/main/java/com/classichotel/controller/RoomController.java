package com.classichotel.controller;

import com.classichotel.model.RoomDTO;
import com.classichotel.service.RoomService;
import java.net.URI;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@AllArgsConstructor
@RequestMapping("/rooms")
public class RoomController {

  private final RoomService roomService;

  @PostMapping
  public ResponseEntity<RoomDTO> createQuarto(@RequestBody RoomDTO roomDTO) {
    RoomDTO newRoom = roomService.createRoom(roomDTO);
    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(newRoom.id())
        .toUri();
    return ResponseEntity.created(location).body(newRoom);
  }

  @GetMapping
  public ResponseEntity<List<RoomDTO>> getAllRooms() {
    List<RoomDTO> rooms = roomService.getAllRooms();
    return ResponseEntity.ok(rooms);
  }

  @GetMapping("/{id}")
  public ResponseEntity<RoomDTO> getRoomById(@PathVariable Long id) {
    RoomDTO room = roomService.getRoomById(id);
    return ResponseEntity.ok(room);
  }

  @PutMapping("/{id}")
  public ResponseEntity<RoomDTO> updateRoom(@PathVariable Long id, @RequestBody RoomDTO roomDTO) {
    if (!id.equals(roomDTO.id())) {
      return ResponseEntity.badRequest().build();
    }
    RoomDTO updatedRoom = roomService.updateRoom(roomDTO);
    return ResponseEntity.ok(updatedRoom);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteRoom(@PathVariable Long id) {
    roomService.deleteRoom(id);
    return ResponseEntity.noContent().build();
  }

}
