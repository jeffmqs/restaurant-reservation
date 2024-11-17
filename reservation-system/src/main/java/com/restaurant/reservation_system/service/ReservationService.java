package com.restaurant.reservation_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.reservation_system.model.Reservation;
import com.restaurant.reservation_system.repository.ReservationRepository;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public Reservation createReservation(Reservation reservation) {
        if (reservation.getDate().isEmpty() || reservation.getTime().isEmpty()) {
            throw new IllegalArgumentException("Date or time cannot be empty.");
        }
        if (reservation.getNumberOfPeople() <= 0) {
            throw new IllegalArgumentException("Number of people must be positive.");
        }
        if (!reservationRepository.findByDateAndTime(reservation.getDate(), reservation.getTime()).isEmpty()) {
            throw new IllegalStateException("Table is not available for the selected date and time.");
        }
        return reservationRepository.save(reservation);
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }
}
