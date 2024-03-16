package com.classichotel.mapper;

import com.classichotel.entity.Room;
import com.classichotel.model.RoomDTO;

public class RoomMapper {

  public static RoomDTO fromEntity(Room room) {
    return new RoomDTO(
        room.getId(),
        room.getType(),
        room.getTotalPeople(),
        room.getTotalBeds(),
        room.getOtherFurniture(),
        room.getRoomRate(),
        room.getBuilding().getId()
    );
  }

  public static Room toEntity(RoomDTO roomDTO) {
    Room room = new Room();
    room.setType(roomDTO.type());
    room.setTotalPeople(roomDTO.totalPeople());
    room.setTotalBeds(roomDTO.totalBeds());
    room.setOtherFurniture(roomDTO.otherFurniture());
    room.setRoomRate(roomDTO.roomRate());
    return room;
  }
}
