package com.example.service_provider_management.calendar;

import com.example.service_provider_management.model.Availability;
import com.example.service_provider_management.repository.AvailabilityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AvailabilityService {

    private final AvailabilityRepository availabilityRepository;

    public Availability setAvailability(Availability availability) {
        return availabilityRepository.save(availability);
    }

    public List<Availability> getByProvider(Long providerId) {
        return availabilityRepository.findByProviderId(providerId);
    }

    public List<Availability> getAvailableByCategoryAndDate(String category, LocalDate date) {
        return availabilityRepository.findByCategoryAndAvailableDateAndIsAvailable(category, date, true);
    }

    public void blockSlot(Long providerId, LocalDate date) {
        List<Availability> slots = availabilityRepository.findByProviderId(providerId);
        slots.stream()
                .filter(slot -> slot.getAvailableDate().equals(date))
                .forEach(slot -> {
                    slot.setIsAvailable(false);
                    availabilityRepository.save(slot);
                });
    }
}
