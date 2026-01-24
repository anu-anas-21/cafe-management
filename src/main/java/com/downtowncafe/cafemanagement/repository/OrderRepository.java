package com.downtowncafe.cafemanagement.repository;

import com.downtowncafe.cafemanagement.entity.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<CustomerOrder, String> {
    List<CustomerOrder> findAllByOrderByTimestampDesc();
}
