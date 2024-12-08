package com.evolvingsystem.Assignment.Controllers;

import com.evolvingsystem.Assignment.DTO.ReservationRequest;
import com.evolvingsystem.Assignment.Services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public ResponseEntity<String> reserveNumber(@RequestBody ReservationRequest request) {
            reservationService.reserveNumber(request.getNumber(), request.getUserId(), request.getIpAddress());
        return ResponseEntity.ok("Number reserved successfully");
    }
}

