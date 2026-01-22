package com.downtowncafe.cafemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.downtowncafe.cafemanagement.model.MenuItem;


public interface MenuRepository extends JpaRepository<MenuItem, Long> {

}
