package com.pragma.powerup.domain.exception;

public class ClientNotOwnerOfTraceabilityException extends DomainException{
    public ClientNotOwnerOfTraceabilityException() {
        super("El cliente logeado no pertenece al registro de este pedido");
    }
}