package com.pragma.powerup.domain.api;


import com.pragma.powerup.domain.model.TraceabilityModel;

public interface ITraceabilityServicePort {
    void saveTraceability(TraceabilityModel traceabilityModel);
}
