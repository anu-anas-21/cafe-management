package com.downtowncafe.cafemanagement.dto;

import java.math.BigDecimal;

public record MenuItemDTO(
        String id,
        String name,
        String description,
        BigDecimal price,
        String subCategory) {
}
