package com.downtowncafe.cafemanagement.controller;


import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.downtowncafe.cafemanagement.model.MenuItem;
import com.downtowncafe.cafemanagement.service.MenuService;


@RestController
@RequestMapping("/api/menu")
@CrossOrigin(origins = "http://localhost:3000")
public class MenuController {

    private final MenuService service;
    
    public MenuController(MenuService service) {
        this.service = service;
    }

    @GetMapping
    public List<MenuItem> getMenu() {
        return service.getAllMenu();
    }

    @PostMapping
    public MenuItem addMenu(@RequestBody MenuItem item) {
        return service.addMenu(item);
    }
}
