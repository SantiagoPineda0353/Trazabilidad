package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.SaveTraceabilityRequestDto;
import com.pragma.powerup.application.dto.response.TraceabilityResponseDto;
import com.pragma.powerup.application.handler.ITraceabilityHandler;
import com.pragma.powerup.application.mapper.ITraceabilityRequestMapper;
import com.pragma.powerup.application.mapper.ITraceabilityResponseMapper;
import com.pragma.powerup.domain.api.ITraceabilityServicePort;
import com.pragma.powerup.domain.model.TraceabilityModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TraceabilityHandler implements ITraceabilityHandler {
    private final ITraceabilityServicePort traceabilityServicePort;
    private final ITraceabilityRequestMapper traceabilityRequestMapper;
    private final ITraceabilityResponseMapper traceabilityResponseMapper;
    private static final ZoneId ZONE_ID= ZoneId.of("America/Bogota");

    @Override
    public void saveTraceability(SaveTraceabilityRequestDto saveTraceabilityRequestDto) {
        TraceabilityModel traceabilityModel= traceabilityRequestMapper.toTraceability(saveTraceabilityRequestDto);
        traceabilityServicePort.saveTraceability(traceabilityModel);
    }

    @Override
    public List<TraceabilityResponseDto> getOrderTraceability(Long idOrder, Long idClient) {
        List<TraceabilityModel> records = traceabilityServicePort.getOrderTraceability(idOrder,idClient);

        List<TraceabilityResponseDto> response= records.stream()
                .map(traceabilityResponseMapper::toResponse)
                .collect(Collectors.toList());

        for (int i=0; i <response.size()-1; i++){
            long timeOrderDuration= Duration.between(response.get(i).getDate().atZone(ZONE_ID),response.get(i+1).getDate().atZone(ZONE_ID)).getSeconds();
            response.get(i).setDurationInPreviousStatusSeconds(timeOrderDuration);
        }
        return response;
    }

    @Override
    public Long getOrderTotalDurationSeconds(Long idOrder) {
        return traceabilityServicePort.getOrderTotalDurationSeconds(idOrder);
    }
}
