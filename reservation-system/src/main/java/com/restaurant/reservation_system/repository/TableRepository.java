package com.restaurant.reservation_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurant.reservation_system.model.RestaurantTable;

public interface TableRepository extends JpaRepository<RestaurantTable, Long> {
}
