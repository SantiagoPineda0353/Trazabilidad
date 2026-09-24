package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.exception.ClientNotOwnerOfTraceabilityException;
import com.pragma.powerup.domain.exception.TraceabilityNotFoundException;
import com.pragma.powerup.domain.model.TraceabilityModel;
import com.pragma.powerup.domain.spi.ITraceabilityPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

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
    void saveTraceability_whenValidData_thenAssignDateAndSave(){
        traceabilityUseCase.saveTraceability(validTraceability);
        assertNotNull(validTraceability.getDate());
        verify(traceabilityPersistencePort, times(1)).saveTraceability(validTraceability);
    }

    @Test
    void getOrderTraceability_whenValidData_thenReturnRecords(){
        TraceabilityModel record1= new TraceabilityModel("1",1L,5L,"cliente@correo.com",
                null,"PENDIENTE","EN_PREPARACION",3L,"empleado@correo.com");

        when(traceabilityPersistencePort.getOrderById(1L))
                .thenReturn(List.of(record1));

        List<TraceabilityModel> result=traceabilityUseCase.getOrderTraceability(1L,5L);

        assertEquals(1,result.size());
    }

    @Test
    void getOrderTraceability_whenNoRecordsFound_thenThrowsException(){
        when(traceabilityPersistencePort.getOrderById(99L))
                .thenReturn(List.of());
        assertThrows(TraceabilityNotFoundException.class, () ->traceabilityUseCase.getOrderTraceability(99L,5L));
    }

    @Test
    void getOrderTraceability_whenClientIsNotOwner_thenThrowsException(){
        TraceabilityModel record1= new TraceabilityModel("1",1L,5L,"cliente@correo.com",
                null,"PENDIENTE","EN_PREPARACION",3L,"empleado@correo.com");

        when(traceabilityPersistencePort.getOrderById(1L))
                .thenReturn(List.of(record1));

        Long otherClient=999L;

        assertThrows(ClientNotOwnerOfTraceabilityException.class,
                () ->traceabilityUseCase.getOrderTraceability(1L,otherClient));
    }
    @Test
    void getOrderTotalDurationSeconds_whenRecordsExist_thenReturnDuration(){
        LocalDateTime start= LocalDateTime.now().minusMinutes(10);
        LocalDateTime end= LocalDateTime.now();

        TraceabilityModel first= new TraceabilityModel("1",1L,5L,"c@correo.com",start,"PENDIENTE","EN_PREPARACION",3L,"e@correo.com");
        TraceabilityModel last= new TraceabilityModel("2",1L,5L,"c@correo.com",end,"LISTO","ENTREGADO",3L,"e@correo.com");

        when(traceabilityPersistencePort.getOrderById(1L))
                .thenReturn(List.of(first,last));

        Long duration=traceabilityUseCase.getOrderTotalDurationSeconds(1L);

        assertEquals(600,duration);
    }

    @Test
    void getOrderTotalDurationSeconds_whenNoRecords_thenReturnNull(){
        when(traceabilityPersistencePort.getOrderById(99L))
                .thenReturn(List.of());
        assertNull(traceabilityUseCase.getOrderTotalDurationSeconds(99L));
    }
}