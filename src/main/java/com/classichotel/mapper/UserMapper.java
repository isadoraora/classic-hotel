package com.classichotel.mapper;

import com.classichotel.entity.User;
import com.classichotel.model.AddressDTO;
import com.classichotel.model.UserDTO;

public class UserMapper {
  public static UserDTO fromEntity(User user) {
    AddressDTO addressDTO = user.getAddress() != null ? AddressMapper.fromEntity(user.getAddress()) : null;
    return new UserDTO(
        user.getId(),
        user.getCountry(),
        user.getCpf(),
        user.getPassport(),
        user.getName(),
        user.getBirthDate(),
        addressDTO,
        user.getPhoneNumber(),
        user.getEmail()
    );
  }

  public static User toEntity(UserDTO userDTO) {
    User user = new User();
    user.setCountry(userDTO.country());
    user.setCpf(userDTO.cpf());
    user.setPassport(userDTO.passport());
    user.setName(userDTO.name());
    user.setBirthDate(userDTO.birthDate());
    user.setAddress(userDTO.address() != null ? AddressMapper.toEntity(userDTO.address()) : null);
    user.setPhoneNumber(userDTO.phoneNumber());
    user.setEmail(userDTO.email());
    return user;
  }
}
