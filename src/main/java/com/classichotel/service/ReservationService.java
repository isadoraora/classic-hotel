package com.classichotel.service;

import com.classichotel.entity.Reservation;
import com.classichotel.entity.ReservationServiceOption;
import com.classichotel.exception.NotFoundException;
import com.classichotel.mapper.ReservationMapper;
import com.classichotel.mapper.ReservationServiceOptionMapper;
import com.classichotel.model.ReservationDTO;
import com.classichotel.repository.ReservationRepository;
import com.classichotel.repository.RoomRepository;
import com.classichotel.repository.ServiceOptionRepository;
import com.classichotel.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.stream.Collectors;

@Service
public class ReservationService {

  private final ReservationRepository reservationRepository;
  private final UserRepository userRepository;
  private final RoomRepository roomRepository;
  private final ServiceOptionRepository serviceOptionRepository;

  @Autowired
  public ReservationService(ReservationRepository reservationRepository,
                            UserRepository userRepository,
                            RoomRepository roomRepository,
                            ServiceOptionRepository serviceOptionRepository) {
    this.reservationRepository = reservationRepository;
    this.userRepository = userRepository;
    this.roomRepository = roomRepository;
    this.serviceOptionRepository = serviceOptionRepository;
  }

  @Transactional
  public ReservationDTO createReservation(ReservationDTO reservationDTO) {
    Reservation reservation = ReservationMapper.toEntity(reservationDTO);
    reservation.setUser(userRepository.findById(reservationDTO.userId()).orElseThrow(() -> new NotFoundException("User not found")));
    reservation.setRooms(reservationDTO.roomIds().stream()
        .map(id -> roomRepository.findById(id).orElseThrow(() -> new NotFoundException("Room not found"))).collect(Collectors.toList()));
    Reservation finalReservation = reservation;
    reservation.setServiceOptions(reservationDTO.serviceOptions().stream().map(dto -> {
      ReservationServiceOption option = ReservationServiceOptionMapper.toEntity(dto);
      option.setServiceOption(
          serviceOptionRepository.findById(dto.serviceOptionId()).orElseThrow(() -> new NotFoundException("Service option not found")));
      option.setReservation(finalReservation);
      return option;
    }).collect(Collectors.toList()));

    reservation = reservationRepository.save(reservation);
    return ReservationMapper.fromEntity(reservation);
  }

  @Transactional
  public ReservationDTO updateReservation(Long id, ReservationDTO reservationDTO) {
    Reservation reservation = reservationRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Reservation not found"));

    reservation.setCheckInDate(reservationDTO.checkInDate());
    reservation.setCheckOutDate(reservationDTO.checkOutDate());
    reservation.setUser(userRepository.findById(reservationDTO.userId()).orElseThrow(() -> new RuntimeException("User not found")));
    reservation.setRooms(reservationDTO.roomIds().stream()
        .map(roomId -> roomRepository.findById(roomId).orElseThrow(() -> new RuntimeException("Room not found")))
        .collect(Collectors.toList()));

    reservation.getServiceOptions().clear();
    Reservation finalReservation = reservation;
    Reservation finalReservation1 = reservation;
    reservationDTO.serviceOptions().forEach(dto -> {
      ReservationServiceOption option = ReservationServiceOptionMapper.toEntity(dto);
      option.setServiceOption(serviceOptionRepository.findById(dto.serviceOptionId())
          .orElseThrow(() -> new RuntimeException("Service option not found")));
      option.setReservation(finalReservation);
      finalReservation1.getServiceOptions().add(option);
    });

    reservation = reservationRepository.save(reservation);
    return ReservationMapper.fromEntity(reservation);
  }

  @Transactional
  public void deleteReservation(Long id) {
    if (!reservationRepository.existsById(id)) {
      throw new NotFoundException("Reservation not found");
    }
    reservationRepository.deleteById(id);
  }

  @Transactional(readOnly = true)
  public List<ReservationDTO> findAllReservations() {
    return reservationRepository.findAll().stream()
        .map(ReservationMapper::fromEntity)
        .collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  public ReservationDTO findReservationById(Long id) {
    Reservation reservation = reservationRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Reservation not found"));
    return ReservationMapper.fromEntity(reservation);
  }

}
