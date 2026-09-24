package com.pragma.powerup.domain.api;


import com.pragma.powerup.domain.model.TraceabilityModel;

import java.util.List;

public interface ITraceabilityServicePort {
    void saveTraceability(TraceabilityModel traceabilityModel);
    List<TraceabilityModel> getOrderTraceability(Long idOrder,Long idClient);
    Long getOrderTotalDurationSeconds(Long idOrder);
}
