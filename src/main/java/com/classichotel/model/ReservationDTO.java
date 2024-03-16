package com.classichotel.model;

import java.time.LocalDate;
import java.util.List;

public record ReservationDTO(
    Long id,
    Long userId,
    List<Long> roomIds,
    LocalDate checkInDate,
    LocalDate checkOutDate,
    List<ReservationServiceOptionDTO> serviceOptions
) {

}
