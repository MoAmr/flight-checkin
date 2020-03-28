package com.flightcheckin.controllers;

import com.flightcheckin.integration.ReservationRestClient;
import com.flightcheckin.integration.dto.Reservation;
import com.flightcheckin.integration.dto.ReservationUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CheckInController {

    @Autowired
    private ReservationRestClient restClient;

    @RequestMapping("/showStartCheckin")
    public String showStartCheckin() {
        return "startCheckIn";
    }

    @RequestMapping("/startCheckIn")
    public String startCheckIn(@RequestParam("reservationId") Long reservationId,
                               ModelMap modelMap) {
        Reservation reservation = restClient.findReservation(reservationId);
        modelMap.addAttribute("reservation", reservation);
        return "displayReservationDetails";
    }

    @RequestMapping("/completeCheckIn")
    public String completeCheckIn(@RequestParam("reservationId") Long reservationId,
                                  @RequestParam("numberOfBags") int numberOfBags) {
        ReservationUpdateRequest updateRequest = new ReservationUpdateRequest();
        updateRequest.setId(reservationId);
        updateRequest.setCheckedIn(true);
        updateRequest.setNumberOfBags(numberOfBags);
        restClient.updateReservation(updateRequest);
        return "checkInConfirmation";
    }
}
