package com.example.service_provider_management.audit;

import com.example.service_provider_management.model.AuditLog;
import com.example.service_provider_management.repository.AuditLogRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuditService {

    private final AuditLogRepository auditLogRepository;

    public void log(String username, String action, String resource, HttpServletRequest request) {
        AuditLog log = AuditLog.builder()
                .username(username)
                .action(action)
                .resource(resource)
                .timestamp(LocalDateTime.now())
                .ip(request.getRemoteAddr())
                .userAgent(request.getHeader("User-Agent"))
                .build();

        auditLogRepository.save(log);
    }

    public void log(String username, String action, String resource) {
        log(username, action, resource, null);
    }
}
