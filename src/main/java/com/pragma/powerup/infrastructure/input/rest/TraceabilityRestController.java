package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.SaveTraceabilityRequestDto;
import com.pragma.powerup.application.handler.ITraceabilityHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/traceability")
@RequiredArgsConstructor
public class TraceabilityRestController {

    private final ITraceabilityHandler traceabilityHandler;

    @PostMapping("/")
    public ResponseEntity<Void> saveTraceability(@RequestBody SaveTraceabilityRequestDto saveTraceabilityRequestDto) {
        traceabilityHandler.saveTraceability(saveTraceabilityRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
