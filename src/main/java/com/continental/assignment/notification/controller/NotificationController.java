package com.continental.assignment.notification.controller;

import com.continental.assignment.notification.domain.VehiclePenalty;
import com.continental.assignment.notification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.util.List;

@RestController
@RequestMapping("/notify")
public class NotificationController {

    @Autowired
    NotificationService service;

    @PostMapping("/email")
    public ResponseEntity<Boolean> sendEmail(@RequestBody List<VehiclePenalty> penalties) throws MessagingException {
        return new ResponseEntity<Boolean>(service.sendEmail(penalties), HttpStatus.OK);
    }
}
