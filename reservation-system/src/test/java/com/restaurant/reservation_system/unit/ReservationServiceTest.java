package com.restaurant.reservation_system.unit;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.restaurant.reservation_system.model.Reservation;
import com.restaurant.reservation_system.repository.ReservationRepository;
import com.restaurant.reservation_system.service.ReservationService;

@SpringBootTest
public class ReservationServiceTest {

    @Autowired
    private ReservationService reservationService;

    @MockBean
    private ReservationRepository reservationRepository;

    // 1. Criar reserva válida
    @Test
    void testCreateReservationWithValidData() {
        Reservation reservation = new Reservation(null, "2024-12-01", "19:00", 4);
        when(reservationRepository.findByDateAndTime("2024-12-01", "19:00")).thenReturn(Collections.emptyList());
        when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);

        Reservation saved = reservationService.createReservation(reservation);

        assertNotNull(saved);
        assertEquals("2024-12-01", saved.getDate());
        assertEquals("19:00", saved.getTime());
        assertEquals(4, saved.getNumberOfPeople());
    }

    // 2. Criar reserva com tabela já ocupada
    @Test
    void testCreateReservationWithUnavailableTable() {
        Reservation reservation = new Reservation(null, "2024-12-01", "19:00", 4);
        when(reservationRepository.findByDateAndTime("2024-12-01", "19:00")).thenReturn(Collections.singletonList(reservation));

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            reservationService.createReservation(reservation);
        });

        assertEquals("Table is not available for the selected date and time.", exception.getMessage());
    }

    // 3. Criar reserva com data no passado
    @Test
    void testCreateReservationWithPastDate() {
        Reservation reservation = new Reservation(null, "2023-01-01", "19:00", 4);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            reservationService.createReservation(reservation);
        });

        assertEquals("Date is in the past.", exception.getMessage());
    }

    // 4. Criar reserva com número de pessoas excedendo o limite
    @Test
    void testCreateReservationWithTooManyPeople() {
        Reservation reservation = new Reservation(null, "2024-12-01", "19:00", 20);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            reservationService.createReservation(reservation);
        });

        assertEquals("Number of people exceeds the limit.", exception.getMessage());
    }

    // 5. Criar reserva com horário fora do funcionamento
    @Test
    void testCreateReservationWithInvalidTime() {
        Reservation reservation = new Reservation(null, "2024-12-01", "03:00", 4);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            reservationService.createReservation(reservation);
        });

        assertEquals("Invalid time. Restaurant is closed.", exception.getMessage());
    }

    // 6. Criar reserva com número negativo de pessoas
    @Test
    void testCreateReservationWithNegativePeople() {
        Reservation reservation = new Reservation(null, "2024-12-01", "19:00", -5);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            reservationService.createReservation(reservation);
        });

        assertEquals("Number of people must be positive.", exception.getMessage());
    }

    // 7. Criar reserva com campos vazios
    @Test
    void testCreateReservationWithEmptyFields() {
        Reservation reservation = new Reservation(null, "", "19:00", 4);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            reservationService.createReservation(reservation);
        });

        assertEquals("Date or time cannot be empty.", exception.getMessage());
    }

    // 8. Validar sucesso ao salvar no banco
    @Test
    void testSaveReservationToDatabase() {
        Reservation reservation = new Reservation(null, "2024-12-01", "19:00", 4);
        when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);

        Reservation saved = reservationService.createReservation(reservation);

        assertNotNull(saved);
        assertEquals("2024-12-01", saved.getDate());
        assertEquals("19:00", saved.getTime());
        assertEquals(4, saved.getNumberOfPeople());
    }

    // 9. Criar reserva com horário de almoço válido
    @Test
    void testCreateReservationWithValidLunchTime() {
        Reservation reservation = new Reservation(null, "2024-12-01", "12:00", 4);
        when(reservationRepository.findByDateAndTime("2024-12-01", "12:00")).thenReturn(Collections.emptyList());
        when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);

        Reservation saved = reservationService.createReservation(reservation);

        assertNotNull(saved);
        assertEquals("2024-12-01", saved.getDate());
        assertEquals("12:00", saved.getTime());
        assertEquals(4, saved.getNumberOfPeople());
    }

    // 10. Criar reserva com número máximo de pessoas permitido
    @Test
    void testCreateReservationWithMaximumPeopleAllowed() {
        Reservation reservation = new Reservation(null, "2024-12-01", "19:00", 10);
        when(reservationRepository.findByDateAndTime("2024-12-01", "19:00")).thenReturn(Collections.emptyList());
        when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);

        Reservation saved = reservationService.createReservation(reservation);

        assertNotNull(saved);
        assertEquals("2024-12-01", saved.getDate());
        assertEquals("19:00", saved.getTime());
        assertEquals(10, saved.getNumberOfPeople());
    }
}

