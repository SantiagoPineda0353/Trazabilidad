package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.SaveTraceabilityRequestDto;
import com.pragma.powerup.application.dto.response.TraceabilityResponseDto;
import com.pragma.powerup.application.handler.ITraceabilityHandler;
import com.pragma.powerup.infrastructure.security.AuthenticationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
    @GetMapping("/order/{idOrder}")
    public ResponseEntity<List<TraceabilityResponseDto>> getOrderTraceability(@PathVariable  Long idOrder) {
        Long idClient = AuthenticationUtils.getAuthenticatedUserId();
        return ResponseEntity.ok(traceabilityHandler.getOrderTraceability(idOrder,idClient));
    }
}
