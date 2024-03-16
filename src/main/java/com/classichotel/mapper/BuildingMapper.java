package com.classichotel.mapper;

import com.classichotel.entity.Building;
import com.classichotel.model.BuildingDTO;
import java.util.stream.Collectors;

public class BuildingMapper {
  public static BuildingDTO fromEntity(Building building) {
    return new BuildingDTO(
        building.getId(),
        building.getName(),
        LocaleMapper.fromEntity(building.getLocale()),
        building.getRoomList().stream().map(RoomMapper::fromEntity).collect(Collectors.toList())
    );
  }

  public static Building toEntity(BuildingDTO buildingDTO) {
    Building building = new Building();
    building.setName(buildingDTO.name());
    building.setLocale(LocaleMapper.toEntity(buildingDTO.locale()));
    building.setRoomList(buildingDTO.roomList().stream().map(RoomMapper::toEntity).collect(Collectors.toList()));
    return building;
  }
}
