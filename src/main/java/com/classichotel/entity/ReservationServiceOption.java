package com.classichotel.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tb_reservation_service_option")
public class ReservationServiceOption {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "reservation_id")
  private Reservation reservation;

  @ManyToOne
  @JoinColumn(name = "service_option_id")
  private ServiceOption serviceOption;

  private int quantity;

}
