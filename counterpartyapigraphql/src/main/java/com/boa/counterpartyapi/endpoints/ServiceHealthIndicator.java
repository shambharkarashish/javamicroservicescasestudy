package com.boa.counterpartyapi.endpoints;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class ServiceHealthIndicator implements HealthIndicator {
        private final String message_key = "Custom Service API";
    @Override
    public Health health() {
        if (!isRunningCustomerService()) {
            return Health.down().withDetail(message_key, "Not Available").build();
        }
        return Health.up().withDetail(message_key, "Available").build();
    }
    private Boolean isRunningCustomerService() {
        Boolean isRunning = true;
        // Logic Skipped
        return isRunning;
    }
}