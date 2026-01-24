package com.downtowncafe.dto;

import java.math.BigDecimal;

public record MenuItemDTO(String id, String name, String description, BigDecimal price) {
}
