package com.downtowncafe.cafemanagement.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
        return service.getAll();
    }

    @PostMapping
    public MenuItem addMenu(@RequestBody MenuItem item) {
        return service.save(item);
    }

    @PutMapping("/{id}")
    public MenuItem updateMenu(@PathVariable Long id, @RequestBody MenuItem item) {
        MenuItem existing = service.getById(id);
        existing.setName(item.getName());
        existing.setCategory(item.getCategory());
        existing.setPrice(item.getPrice());
        existing.setAvailable(item.isAvailable());
        return service.save(existing);
    }

    @DeleteMapping("/{id}")
    public void deleteMenu(@PathVariable Long id) {
        service.delete(id);
    }
}

