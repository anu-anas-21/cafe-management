package com.downtowncafe.cafemanagement.dto;

import java.util.List;
import java.util.Map;

public record MenuResponseDTO(
                Map<String, List<MenuItemDTO>> menuItems,
                Map<String, String> menuExperience) {
}
