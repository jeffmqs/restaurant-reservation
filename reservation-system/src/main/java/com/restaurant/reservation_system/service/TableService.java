package com.restaurant.reservation_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.reservation_system.model.RestaurantTable;
import com.restaurant.reservation_system.repository.TableRepository;

@Service
public class TableService {

    @Autowired
    private TableRepository tableRepository;

    public List<RestaurantTable> getAllTables() {
        return tableRepository.findAll();
    }
}
