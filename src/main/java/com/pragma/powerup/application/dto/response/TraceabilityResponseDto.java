package com.pragma.powerup.application.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TraceabilityResponseDto {
    private LocalDateTime date;
    private String previousStatus;
    private String newStatus;
    private Long durationInPreviousStatusSeconds;
}
