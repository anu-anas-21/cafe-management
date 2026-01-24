package com.downtowncafe.cafemanagement.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderDTO(
                String id,
                String tableNumber,
                List<MenuItemDTO> items,
                BigDecimal total,
                String status,
                LocalDateTime timestamp) {
}
