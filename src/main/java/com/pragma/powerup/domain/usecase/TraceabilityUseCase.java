package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.ITraceabilityServicePort;
import com.pragma.powerup.domain.exception.ClientNotOwnerOfTraceabilityException;
import com.pragma.powerup.domain.exception.TraceabilityNotFoundException;
import com.pragma.powerup.domain.model.*;
import com.pragma.powerup.domain.spi.ITraceabilityPersistencePort;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

public class TraceabilityUseCase implements ITraceabilityServicePort {

    private final ITraceabilityPersistencePort traceabilityPersistencePort;
    private static final ZoneId ZONE_ID= ZoneId.of("America/Bogota");

    public TraceabilityUseCase(ITraceabilityPersistencePort traceabilityPersistencePort) {
        this.traceabilityPersistencePort=traceabilityPersistencePort;
    }

    @Override
    public void saveTraceability(TraceabilityModel traceabilityModel) {
        traceabilityModel.setDate(LocalDateTime.now(ZONE_ID));
        traceabilityPersistencePort.saveTraceability(traceabilityModel);
    }

    @Override
    public List<TraceabilityModel> getOrderTraceability(Long idOrder, Long idClient) {
        List<TraceabilityModel> records = traceabilityPersistencePort.getOrderById(idOrder);

        if(records.isEmpty()){
            throw new TraceabilityNotFoundException();
        }
        if(!records.get(0).getIdClient().equals(idClient)){
            throw new ClientNotOwnerOfTraceabilityException();
        }
        return records;
    }
}
