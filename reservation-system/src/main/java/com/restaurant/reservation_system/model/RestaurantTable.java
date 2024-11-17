package com.restaurant.reservation_system.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "restaurant_table") // Nome ajustado
@Data
public class RestaurantTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int capacity;
    private boolean reserved;
}
