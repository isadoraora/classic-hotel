package com.classichotel.mapper;

import com.classichotel.entity.Reservation;
import com.classichotel.entity.Room;
import com.classichotel.model.ReservationDTO;
import java.util.stream.Collectors;

public class ReservationMapper {

  public static ReservationDTO fromEntity(Reservation reservation) {
    return new ReservationDTO(
        reservation.getId(),
        reservation.getUser() != null ? reservation.getUser().getId() : null,
        reservation.getRooms().stream().map(Room::getId).collect(Collectors.toList()),
        reservation.getCheckInDate(),
        reservation.getCheckOutDate(),
        reservation.getServiceOptions().stream()
            .map(ReservationServiceOptionMapper::fromEntity)
            .collect(Collectors.toList())
    );
  }

  public static Reservation toEntity(ReservationDTO dto) {
    Reservation reservation = new Reservation();
    reservation.setId(dto.userId());
    reservation.setCheckInDate(dto.checkInDate());
    reservation.setCheckOutDate(dto.checkOutDate());
    return reservation;
  }
}
