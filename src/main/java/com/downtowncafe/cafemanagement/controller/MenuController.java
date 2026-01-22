package com.downtowncafe.cafemanagement.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.downtowncafe.cafemanagement.service.MenuService;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;


@RestController
@RequestMapping("/api/menu")
@CrossOrigin(origins = "*")
public class MenuController {

    private final MenuService service;
    
    public MenuController(MenuService service) {
        this.service = service;
    }

    @GetMapping
    pub
}
