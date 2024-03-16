package com.classichotel.mapper;

import com.classichotel.entity.Locale;
import com.classichotel.model.BuildingDTO;
import com.classichotel.model.LocaleDTO;
import java.util.List;
import java.util.stream.Collectors;

public class LocaleMapper {

  public static LocaleDTO fromEntity(Locale locale) {
    List<BuildingDTO> buildingDTOList = locale.getBuildingList().stream()
        .map(BuildingMapper::fromEntity)
        .collect(Collectors.toList());

    return new LocaleDTO(
        locale.getId(),
        locale.getName(),
        buildingDTOList
    );
  }

  public static Locale toEntity(LocaleDTO localeDTO) {
    Locale locale = new Locale();
    locale.setId(localeDTO.id());
    locale.setName(localeDTO.name());
    return locale;
  }
}
