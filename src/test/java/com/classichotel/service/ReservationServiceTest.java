package com.classichotel.service;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.*;

import com.classichotel.entity.Reservation;
import com.classichotel.entity.Room;
import com.classichotel.entity.User;
import com.classichotel.model.ReservationDTO;
import com.classichotel.repository.ReservationRepository;
import com.classichotel.repository.RoomRepository;
import com.classichotel.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {

  @Mock
  private ReservationRepository reservationRepository;

  @Mock
  private UserRepository userRepository;

  @Mock
  private RoomRepository roomRepository;

  @InjectMocks
  private ReservationService reservationService;

  private ReservationDTO reservationDTO;
  private Reservation reservation;

  @BeforeEach
  void setUp() {
    reservation = new Reservation();
    reservation.setId(1L);
    reservationDTO = new ReservationDTO(1L, 2L, Collections.emptyList(), null, null, Collections.emptyList());

    lenient().when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));

    lenient().when(reservationRepository.findAll()).thenReturn(Collections.singletonList(reservation));
    lenient().when(roomRepository.findById(anyLong())).thenReturn(Optional.of(new Room()));
    lenient().when(userRepository.findById(anyLong())).thenReturn(Optional.of(new User()));
    lenient().when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));

  }

  @Test
  void createReservation_ShouldReturnReservationDTO() {
    given(roomRepository.findById(anyLong())).willReturn(Optional.of(new Room()));

    ReservationDTO createdReservationDTO = reservationService.createReservation(reservationDTO);

    assertNotNull(createdReservationDTO);
  }

  @Test
  void updateReservation_ShouldReturnUpdatedReservationDTO() {
    given(roomRepository.findById(anyLong())).willReturn(Optional.of(new Room()));

    ReservationDTO updatedReservationDTO = reservationService.updateReservation(1L, reservationDTO);

    assertNotNull(updatedReservationDTO);
  }

  @Test
  void deleteReservation_ShouldSucceed() {
    reservationService.deleteReservation(1L);

    verify(reservationRepository).deleteById(1L);
  }

  @Test
  void findAllReservations_ShouldReturnListOfReservations() {
    given(reservationRepository.findAll()).willReturn(Collections.singletonList(reservation));

    List<ReservationDTO> allReservations = reservationService.findAllReservations();

    assertFalse(allReservations.isEmpty());
    assertEquals(1, allReservations.size());
  }

  @Test
  void findReservationById_ShouldReturnReservationDTO() {
    ReservationDTO foundReservationDTO = reservationService.findReservationById(1L);

    assertNotNull(foundReservationDTO);
  }
}
