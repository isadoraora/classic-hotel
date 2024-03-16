package com.classichotel.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tb_building")
public class Building {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "Name is mandatory")
  @Size(max = 155, message = "Name must be less than 155 characters")
  private String name;

  @ManyToOne
  @JoinColumn(name = "locale_id")
  private Locale locale;

  @OneToMany(mappedBy = "building", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Room> roomList = new ArrayList<>();
}

