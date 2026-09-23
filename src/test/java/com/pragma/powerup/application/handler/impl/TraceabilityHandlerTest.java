package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.SaveTraceabilityRequestDto;
import com.pragma.powerup.application.mapper.ITraceabilityRequestMapper;
import com.pragma.powerup.domain.api.ITraceabilityServicePort;
import com.pragma.powerup.domain.model.TraceabilityModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TraceabilityHandlerTest {

    @Mock
    private ITraceabilityServicePort traceabilityServicePort;
    @Mock
    private ITraceabilityRequestMapper traceabilityRequestMapper;

    @InjectMocks
    private TraceabilityHandler traceabilityHandler;

    @Test
    void saveTraceability_whenCalled_thenMapAndDelegateToService(){
        SaveTraceabilityRequestDto dto= new SaveTraceabilityRequestDto();
        TraceabilityModel model= new TraceabilityModel();

        when(traceabilityRequestMapper.toTraceability(dto))
                .thenReturn(model);

        traceabilityHandler.saveTraceability(dto);

        verify(traceabilityServicePort).saveTraceability(model);
    }
}