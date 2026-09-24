package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.SaveTraceabilityRequestDto;
import com.pragma.powerup.application.dto.response.TraceabilityResponseDto;

import java.util.List;

public interface ITraceabilityHandler {
    void saveTraceability(SaveTraceabilityRequestDto saveTraceabilityRequestDto);
    List<TraceabilityResponseDto> getOrderTraceability(Long idOrder, Long idClient);
}
