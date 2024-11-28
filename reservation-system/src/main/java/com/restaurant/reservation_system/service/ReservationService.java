package com.restaurant.reservation_system.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.reservation_system.model.Reservation;
import com.restaurant.reservation_system.repository.ReservationRepository;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Reservation createReservation(Reservation reservation) {
        int maxPeople = 10;
        String openingTime = "10:00";
        String closingTime = "22:00";
        if (reservation.getNumberOfPeople() > maxPeople) {
            throw new IllegalArgumentException("Number of people exceeds the limit.");
        }
        if (isPastDate(reservation.getDate())) {
            throw new IllegalArgumentException("Date is in the past.");
        }

        if (!isWithinOperatingHours(reservation.getTime(), openingTime, closingTime)) {
            throw new IllegalArgumentException("Invalid time. Restaurant is closed.");
        }
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
    private boolean isWithinOperatingHours(String reservationTime, String openingTime, String closingTime) {
        return reservationTime.compareTo(openingTime) >= 0 && reservationTime.compareTo(closingTime) <= 0;
    }
    private boolean isPastDate(String date) {
        LocalDate reservationDate = LocalDate.parse(date);
        return reservationDate.isBefore(LocalDate.now());
    }
}
