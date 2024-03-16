package com.classichotel.model;

import java.util.List;

public record BuildingDTO(
    Long id,
    String name,
    LocaleDTO locale,
    List<RoomDTO> roomList
) {
}
