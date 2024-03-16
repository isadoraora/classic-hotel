package com.classichotel.model;

import java.math.BigDecimal;

public record RoomDTO(
    Long id,
    String type,
    int totalPeople,
    int totalBeds,
    String otherFurniture,
    BigDecimal roomRate,
    Long buildingId
) {
}
