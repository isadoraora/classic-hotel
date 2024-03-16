package com.classichotel.model;

import com.classichotel.entity.Address;

public record UserDTO(
    Long id,

    String country,

    String cpf,


    String passport,

    String name,

    String birthDate,

    AddressDTO address,

    String phoneNumber,

    String email
) {
}
