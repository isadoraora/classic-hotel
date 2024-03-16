package com.classichotel.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tb_reservation")
public class Reservation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToMany
  @JoinTable(
      name = "reservation_room",
      joinColumns = @JoinColumn(name = "reservation_id"),
      inverseJoinColumns = @JoinColumn(name = "room_id"))
  private List<Room> rooms;

  private LocalDate checkInDate;
  private LocalDate checkOutDate;

  @OneToMany(mappedBy = "reservation")
  private List<ReservationServiceOption> serviceOptions;

  public Reservation() {
    this.rooms = new ArrayList<>();
    this.serviceOptions = new ArrayList<>();
  }
}

