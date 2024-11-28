package com.restaurant.reservation_system.system;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.restaurant.reservation_system.model.Reservation;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReservationSystemTest {

    @LocalServerPort
    private int port;

    private final RestTemplate restTemplate;

    private String BASE_URL;

    @Autowired
    public ReservationSystemTest(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Test
    void testCreateAndRetrieveReservation() {
        BASE_URL = "http://localhost:" + port + "/reservations";

        Reservation newReservation = new Reservation(null, "2024-12-01", "19:00", 4);
        ResponseEntity<Reservation> postResponse = restTemplate.postForEntity(BASE_URL, newReservation, Reservation.class);

        assertNotNull(postResponse, "A resposta do POST é nula.");
        assertEquals(HttpStatus.CREATED, postResponse.getStatusCode(), "O status HTTP do POST não é 201.");
        Reservation postBody = postResponse.getBody();
        assertNotNull(postBody, "O corpo da resposta do POST é nulo.");
        assertEquals("2024-12-01", postBody.getDate());
        assertEquals("19:00", postBody.getTime());
        assertEquals(4, postBody.getNumberOfPeople());

        ResponseEntity<Reservation[]> getResponse = restTemplate.getForEntity(BASE_URL, Reservation[].class);

        assertNotNull(getResponse, "A resposta do GET é nula.");
        assertEquals(HttpStatus.OK, getResponse.getStatusCode(), "O status HTTP do GET não é 200.");

        Reservation[] reservations = getResponse.getBody();
        assertNotNull(reservations, "O corpo da resposta do GET é nulo.");
        assertTrue(reservations.length > 0, "Não há reservas retornadas.");

        Reservation firstReservation = reservations[0];
        assertNotNull(firstReservation, "A primeira reserva retornada é nula.");
        assertEquals("2024-12-01", firstReservation.getDate());
        assertEquals("19:00", firstReservation.getTime());
        assertEquals(4, firstReservation.getNumberOfPeople());
    }
}
