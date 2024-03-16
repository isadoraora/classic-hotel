package com.classichotel.mapper;

import com.classichotel.entity.Address;
import com.classichotel.model.AddressDTO;

public class AddressMapper {

  public static AddressDTO fromEntity(Address address) {
    return new AddressDTO(
        address.getId(),
        address.getStreet(),
        address.getCity(),
        address.getState(),
        address.getCountry(),
        address.getZipCode()
    );
  }

  public static Address toEntity(AddressDTO addressDTO) {
    Address address = new Address();
    address.setStreet(addressDTO.street());
    address.setCity(addressDTO.city());
    address.setState(addressDTO.state());
    address.setCountry(addressDTO.country());
    address.setZipCode(addressDTO.zipCode());
    return address;
  }
}
