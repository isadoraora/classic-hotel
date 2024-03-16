package com.classichotel.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.classichotel.model.ReservationDTO;
import com.classichotel.model.ReservationServiceOptionDTO;
import com.classichotel.service.ReservationService;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class ReservationControllerTest {

  @Mock
  private ReservationService reservationService;

  @InjectMocks
  private ReservationController reservationController;

  private ReservationDTO reservationDTO;

  @BeforeEach
  void setUp() {
    List<Long> roomIds = Arrays.asList(1L, 2L);
    List<ReservationServiceOptionDTO> serviceOptions = Collections.singletonList(
        new ReservationServiceOptionDTO(1L, 2)
    );
    reservationDTO = new ReservationDTO(
        1L,
        1L,
        roomIds,
        LocalDate.now(),
        LocalDate.now().plusDays(3),
        serviceOptions
    );
  }

  @Test
  void createReservation_ShouldReturnNewReservation() {
    given(reservationService.createReservation(any(ReservationDTO.class))).willReturn(reservationDTO);

    ResponseEntity<ReservationDTO> response = reservationController.createReservation(reservationDTO);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    assertThat(response.getBody()).isEqualTo(reservationDTO);
  }

  @Test
  void updateReservation_ShouldReturnUpdatedReservation() {
    Long reservationId = 1L;
    given(reservationService.updateReservation(eq(reservationId), any(ReservationDTO.class))).willReturn(reservationDTO);

    ResponseEntity<ReservationDTO> response = reservationController.updateReservation(reservationId, reservationDTO);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isEqualTo(reservationDTO);
  }

  @Test
  void deleteReservation_ShouldReturnNoContent() {
    Long reservationId = 1L;
    doNothing().when(reservationService).deleteReservation(reservationId);

    ResponseEntity<Void> response = reservationController.deleteReservation(reservationId);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    verify(reservationService, times(1)).deleteReservation(reservationId);
  }

  @Test
  void getAllReservations_ShouldReturnReservationsList() {
    given(reservationService.findAllReservations()).willReturn(Collections.singletonList(reservationDTO));

    ResponseEntity<List<ReservationDTO>> response = reservationController.getAllReservations();

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).hasSize(1);
    assertThat(response.getBody().get(0)).isEqualTo(reservationDTO);
  }

  @Test
  void getReservationById_ShouldReturnReservation() {
    Long reservationId = 1L;
    given(reservationService.findReservationById(reservationId)).willReturn(reservationDTO);

    ResponseEntity<ReservationDTO> response = reservationController.getReservationById(reservationId);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isEqualTo(reservationDTO);
  }
}
