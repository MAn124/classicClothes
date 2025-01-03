package com.ttma.classicClothes.repository;

import com.ttma.classicClothes.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders,Long> {
    List<Orders>findByUserId(long id);
}
