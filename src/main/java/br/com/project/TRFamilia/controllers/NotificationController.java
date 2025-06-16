package br.com.project.TRFamilia.controllers; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.project.TRFamilia.annotations.JustAdmin;
import br.com.project.TRFamilia.dto.CreateNotificationDTO;
import br.com.project.TRFamilia.models.Notification;
import br.com.project.TRFamilia.services.NotificationService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("notification")
public class NotificationController {
    @Autowired private NotificationService notificationService;

    @PostMapping("/create")
    @JustAdmin
    public Notification createNotification(@RequestBody @Valid CreateNotificationDTO createNotificationDTO) {
        return notificationService.saveNotification(createNotificationDTO);
    }
}
