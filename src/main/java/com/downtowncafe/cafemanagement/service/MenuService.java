package com.downtowncafe.cafemanagement.service;


import java.util.List;
import org.springframework.stereotype.Service;
import com.downtowncafe.cafemanagement.model.MenuItem;
import com.downtowncafe.cafemanagement.repository.MenuRepository;


@Service
public class MenuService {

    private final MenuRepository repo;

    public MenuService(MenuRepository repo) {
        this.repo = repo;
    }

    public List<MenuItem> getAllMenu() {
        return repo.findAll();
    }  

    public MenuItem save(MenuItem item) {
        return repo.save(item);
    }
}
