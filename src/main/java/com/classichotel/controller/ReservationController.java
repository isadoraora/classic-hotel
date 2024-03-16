package com.classichotel.controller;

import com.classichotel.model.ReservationDTO;
import com.classichotel.service.ReservationService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/reservations")
public class ReservationController {

  private final ReservationService reservationService;

  @PostMapping
  public ResponseEntity<ReservationDTO> createReservation(@RequestBody ReservationDTO reservationDTO) {
    ReservationDTO newReservation = reservationService.createReservation(reservationDTO);
    return new ResponseEntity<>(newReservation, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ReservationDTO> updateReservation(@PathVariable Long id, @RequestBody ReservationDTO reservationDTO) {
    ReservationDTO updatedReservation = reservationService.updateReservation(id, reservationDTO);
    return ResponseEntity.ok(updatedReservation);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
    reservationService.deleteReservation(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping
  public ResponseEntity<List<ReservationDTO>> getAllReservations() {
    List<ReservationDTO> reservations = reservationService.findAllReservations();
    return ResponseEntity.ok(reservations);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ReservationDTO> getReservationById(@PathVariable Long id) {
    ReservationDTO reservation = reservationService.findReservationById(id);
    return ResponseEntity.ok(reservation);
  }
}
