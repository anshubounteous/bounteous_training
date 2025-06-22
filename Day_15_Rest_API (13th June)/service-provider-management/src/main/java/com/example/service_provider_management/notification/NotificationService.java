package com.example.service_provider_management.notification;

import com.example.service_provider_management.model.Notification;
import com.example.service_provider_management.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    /**
     * Sends (saves) a notification to a user.
     */
    public void send(Notification notification) {
        notification.setSentAt(LocalDateTime.now());
        notificationRepository.save(notification);
    }

    /**
     * Retrieves all notifications for a specific user.
     */
    public List<Notification> getForUser(Long userId) {
        return notificationRepository.findByUserId(userId);
    }
}
