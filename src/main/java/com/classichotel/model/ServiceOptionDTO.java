package com.classichotel.model;

import com.classichotel.entity.ServiceOptionType;
import java.math.BigDecimal;

public record ServiceOptionDTO(
    Long id,
    String name,
    BigDecimal price,
    ServiceOptionType type
) {
}
