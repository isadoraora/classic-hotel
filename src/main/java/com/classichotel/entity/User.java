package com.classichotel.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Getter
@Setter
@Table(name = "tb_user")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "Country is mandatory")
  private String country;

  @CPF
  private String cpf;


  private String passport;

  @NotBlank(message = "Complete name is mandatory")
  private String name;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private String birthDate;

  @ManyToOne
  @JoinColumn(name = "address_id")
  private Address address;

  @NotBlank(message = "Phone number is mandatory")
  private String phoneNumber;

  @Email(message = "Email is mandatory")
  private String email;

}
