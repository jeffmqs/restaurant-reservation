package com.restaurant.reservation_system.integration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.restaurant.reservation_system.model.Reservation;
import com.restaurant.reservation_system.repository.ReservationRepository;

@DataJpaTest
public class ReservationIntegrationTest {

    @Autowired
    private ReservationRepository reservationRepository;

    @Test
    void testSaveAndRetrieveReservation() {
        // Criar reserva e salvar no banco
        Reservation reservation = new Reservation(null, "2024-12-01", "19:00", 4);
        reservationRepository.save(reservation);

        // Recuperar a reserva pelo repositório
        List<Reservation> reservations = reservationRepository.findByDateAndTime("2024-12-01", "19:00");

        assertEquals(1, reservations.size());
        assertEquals("2024-12-01", reservations.get(0).getDate());
        assertEquals("19:00", reservations.get(0).getTime());
        assertEquals(4, reservations.get(0).getNumberOfPeople());
    }
}

