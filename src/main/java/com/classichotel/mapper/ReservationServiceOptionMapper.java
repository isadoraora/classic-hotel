package com.classichotel.mapper;

import com.classichotel.entity.ReservationServiceOption;
import com.classichotel.model.ReservationServiceOptionDTO;

public class ReservationServiceOptionMapper {

  public static ReservationServiceOptionDTO fromEntity(ReservationServiceOption serviceOption) {
    return new ReservationServiceOptionDTO(
        serviceOption.getServiceOption().getId(),
        serviceOption.getQuantity()
    );
  }

  public static ReservationServiceOption toEntity(ReservationServiceOptionDTO dto) {
    ReservationServiceOption serviceOption = new ReservationServiceOption();
    serviceOption.setQuantity(dto.quantity());
    return serviceOption;
  }
}
