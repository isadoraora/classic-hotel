package com.classichotel.model;

public record AddressDTO(
    Long id,
    String street,
    String city,
    String state,
    String country,
    String zipCode
) {
}
