package com.downtowncafe.cafemanagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
public class RootController {

    @GetMapping("/")
    public Map<String, String> index() {
        return Map.of(
                "status", "online",
                "message", "Downtown Cafe Backend is running successfully!",
                "api_endpoint", "/api/menu");
    }
}
