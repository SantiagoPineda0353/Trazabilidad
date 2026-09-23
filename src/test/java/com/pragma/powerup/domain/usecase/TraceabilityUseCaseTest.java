package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.model.TraceabilityModel;
import com.pragma.powerup.domain.spi.ITraceabilityPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TraceabilityUseCaseTest {

    @Mock
    private ITraceabilityPersistencePort traceabilityPersistencePort;

    @InjectMocks
    private TraceabilityUseCase traceabilityUseCase;

    private TraceabilityModel validTraceability;

    @BeforeEach
    void setUp(){
        validTraceability= new TraceabilityModel(null,1L,5L,"cliente@correo.com",
                null,"PENDIENTE","EN_PREPARACION",3L,"empleado@correo.com");
    }

    @Test
    void saveTraceability_whenValidData_thenSaveWithDateAssigned(){
        traceabilityUseCase.saveTraceability(validTraceability);
        verify(traceabilityPersistencePort).saveTraceability(any());
        assertNotNull(validTraceability.getDate());
    }

    @Test
    void saveTraceability_whenCalled_thenDelegatesToPersistencePort(){
        traceabilityUseCase.saveTraceability(validTraceability);
        verify(traceabilityPersistencePort, times(1)).saveTraceability(validTraceability);
    }
}