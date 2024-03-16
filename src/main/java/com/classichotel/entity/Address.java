package com.classichotel.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tb_address")
public class Address {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "Street is mandatory")
  private String street;

  @NotBlank(message = "City is mandatory")
  private String city;

  @NotBlank(message = "State is mandatory")
  private String state;

  @NotBlank(message = "Country is mandatory")
  private String country;

  @Pattern(regexp = "\\d{5}-\\d{3}", message = "Invalid ZIP code format")
  private String zipCode;

}
