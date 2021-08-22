package com.continental.assignment.notification.service;

import com.continental.assignment.notification.domain.VehiclePenalty;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import java.util.List;

public interface NotificationService {
    public Boolean sendEmail(List<VehiclePenalty> penalties) throws AddressException, MessagingException;
}
