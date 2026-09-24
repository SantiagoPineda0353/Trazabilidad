package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.TraceabilityModel;

import java.util.List;

public interface ITraceabilityPersistencePort {
    TraceabilityModel saveTraceability(TraceabilityModel traceabilityModel);
    List<TraceabilityModel> getOrderById(Long idOrder);
}
