package com.flightcheckin.integration;

import com.flightcheckin.integration.dto.Reservation;
import com.flightcheckin.integration.dto.ReservationUpdateRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ReservationRestClientImpl implements ReservationRestClient {

    private static final String RESERVATION_REST_URI = "http://localhost:8080/flightreservation/reservations/";

    @Override
    public Reservation findReservation(Long id) {
        RestTemplate template = new RestTemplate();
        Reservation reservation = template.getForObject(
                RESERVATION_REST_URI + id,
                Reservation.class);
        return reservation;
    }

    @Override
    public Reservation updateReservation(ReservationUpdateRequest request) {
        RestTemplate template = new RestTemplate();
        Reservation reservation = template.postForObject(
                RESERVATION_REST_URI,
                request, Reservation.class);
        return reservation;
    }
}
