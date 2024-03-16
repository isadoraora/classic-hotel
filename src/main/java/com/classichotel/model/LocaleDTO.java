package com.classichotel.model;

import com.classichotel.entity.Building;
import java.util.List;

public record LocaleDTO(
    Long id,
    String name,
    List<BuildingDTO> buildingList
) {
}

