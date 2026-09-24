package com.pragma.powerup.domain.exception;

public class TraceabilityNotFoundException extends DomainException{
    public TraceabilityNotFoundException() {
        super("No hay trazabilidad registrada para esta orden");
    }
}
