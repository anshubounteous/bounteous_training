package com.example.service_provider_management.service;

import com.example.service_provider_management.calendar.AvailabilityService;
import com.example.service_provider_management.model.*;
import com.example.service_provider_management.notification.NotificationService;
import com.example.service_provider_management.repository.ServiceRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceRequestService {

    private final ServiceRequestRepository requestRepository;
    private final AvailabilityService availabilityService;
    private final NotificationService notificationService;

    /**
     * Create a new service request and notify available providers.
     */
    public ServiceRequest createRequest(ServiceRequest request) {
        request.setStatus(RequestStatus.PENDING);
        ServiceRequest saved = requestRepository.save(request);

        // Notify available service providers
        List<Availability> availableProviders = availabilityService.getAvailableByCategoryAndDate(
                request.getCategory(), request.getPreferredDate());

        for (Availability availability : availableProviders) {
            Notification notification = Notification.builder()
                    .userId(availability.getProviderId())
                    .message("New service request in your category: " + request.getCategory())
                    .build();
            notificationService.send(notification);
        }

        return saved;
    }

    /**
     * Get all requests for a given customer.
     */
    public List<ServiceRequest> getByCustomerId(Long customerId) {
        return requestRepository.findByCustomerId(customerId);
    }
}
