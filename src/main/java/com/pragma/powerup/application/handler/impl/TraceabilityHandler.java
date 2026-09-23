package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.SaveTraceabilityRequestDto;
import com.pragma.powerup.application.handler.ITraceabilityHandler;
import com.pragma.powerup.application.mapper.ITraceabilityRequestMapper;
import com.pragma.powerup.domain.api.ITraceabilityServicePort;
import com.pragma.powerup.domain.model.TraceabilityModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TraceabilityHandler implements ITraceabilityHandler {
    private final ITraceabilityServicePort traceabilityServicePort;
    private final ITraceabilityRequestMapper traceabilityRequestMapper;

    @Override
    public void saveTraceability(SaveTraceabilityRequestDto saveTraceabilityRequestDto) {
        TraceabilityModel traceabilityModel= traceabilityRequestMapper.toTraceability(saveTraceabilityRequestDto);
        traceabilityServicePort.saveTraceability(traceabilityModel);
    }
}
