package br.com.project.TRFamilia.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.project.TRFamilia.dto.CreateNotificationDTO;
import br.com.project.TRFamilia.exceptions.ApiException;
import br.com.project.TRFamilia.models.Notification;
import br.com.project.TRFamilia.models.User;
import br.com.project.TRFamilia.repositories.NotificationRepository;
import br.com.project.TRFamilia.repositories.UserRepository;

@Service
public class NotificationService {
    @Autowired private NotificationRepository notificationRepository;

    @Autowired private UserRepository userRepository;

    public Notification saveNotification(CreateNotificationDTO createNotificationDTO) {

        Optional<User> user = userRepository.findById(createNotificationDTO.getUser());

        if(!user.isPresent()) throw new ApiException(404, "User not found", HttpStatus.NOT_FOUND); 

        Notification notification = new Notification(
            user.get(),
            createNotificationDTO.getTitle(),
            createNotificationDTO.getMessage(),
            createNotificationDTO.getNotificationType(),
            false,
            LocalDateTime.now(),
            LocalDateTime.now()
        );

        return notificationRepository.save(notification);
    }
}
