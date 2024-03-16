package com.classichotel.entity;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tb_room")
public class Room {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "Type is mandatory")
  @Size(max = 100, message = "Type must be less than 100 characters")
  private String type;

  @NotNull(message = "Total people is mandatory")
  @Min(value = 1, message = "Total people must be at least 1")
  private int totalPeople;

  @NotNull(message = "Total beds is mandatory")
  @Min(value = 1)
  private int totalBeds;

  private String otherFurniture;

  @NotNull(message = "Room rate is mandatory")
  @DecimalMin(value = "0.0")
  private BigDecimal roomRate;

  @ManyToOne
  @JoinColumn(name = "building_id")
  private Building building;
}
