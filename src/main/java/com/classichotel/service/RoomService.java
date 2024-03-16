package com.classichotel.service;

import com.classichotel.entity.Room;
import com.classichotel.exception.NotFoundException;
import com.classichotel.mapper.RoomMapper;
import com.classichotel.model.RoomDTO;
import com.classichotel.repository.BuildingRepository;
import com.classichotel.repository.RoomRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class RoomService {

  private final RoomRepository roomRepository;
  private final BuildingRepository buildingRepository;

  @Transactional
  public RoomDTO createRoom(RoomDTO roomDTO) {
    Room room = RoomMapper.toEntity(roomDTO);
    room.setBuilding(buildingRepository.findById(roomDTO.buildingId()).orElseThrow(() -> new RuntimeException("Building not found")));
    Room savedRoom = roomRepository.save(room);
    return RoomMapper.fromEntity(savedRoom);
  }

  @Transactional(readOnly = true)
  public List<RoomDTO> getAllRooms() {
    return roomRepository.findAll().stream().map(RoomMapper::fromEntity).collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  public RoomDTO getRoomById(Long id) {
    Room room = roomRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Room not found with id: " + id));
    return RoomMapper.fromEntity(room);
  }

  @Transactional
  public RoomDTO updateRoom(RoomDTO roomDTO) {
    if (!roomRepository.existsById(roomDTO.id())) {
      throw new RuntimeException("Room not found");
    }
    Room room = RoomMapper.toEntity(roomDTO);
    room.setBuilding(buildingRepository.findById(roomDTO.buildingId()).orElseThrow(() -> new RuntimeException("Building not found")));
    Room updatedRoom = roomRepository.save(room);
    return RoomMapper.fromEntity(updatedRoom);
  }

  @Transactional
  public void deleteRoom(Long id) {
    if (!roomRepository.existsById(id)) {
      throw new RuntimeException("Room not found");
    }
    roomRepository.deleteById(id);
  }

  // Implementar findRoomsByBuildingAndLocale
}
