package com.pragma.powerup.application.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveTraceabilityRequestDto {
    private Long idOrder;
    private Long idClient;
    private String clientEmail;
    private String previousStatus;
    private String newStatus;
    private Long idEmployee;
    private String employeeEmail;
}
