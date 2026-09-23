package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.TraceabilityModel;

public interface ITraceabilityPersistencePort {
    TraceabilityModel saveTraceability(TraceabilityModel traceabilityModel);
}
