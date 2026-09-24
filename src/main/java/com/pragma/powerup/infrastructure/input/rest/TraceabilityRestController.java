package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.SaveTraceabilityRequestDto;
import com.pragma.powerup.application.dto.response.TraceabilityResponseDto;
import com.pragma.powerup.application.handler.ITraceabilityHandler;
import com.pragma.powerup.infrastructure.security.AuthenticationUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Crear registro para trazabilidad de orden")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Registro creado", content = @Content),
            @ApiResponse(responseCode = "400", description = "Datos de registro incorrectos", content = @Content)
    })
    @PostMapping("/")
    public ResponseEntity<Void> saveTraceability(@RequestBody SaveTraceabilityRequestDto saveTraceabilityRequestDto) {
        traceabilityHandler.saveTraceability(saveTraceabilityRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Obtener trazabilidad por Orden")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Registros Obtenidos", content = @Content),
            @ApiResponse(responseCode = "404", description = "Orden no encontrada", content = @Content)
    })
    @GetMapping("/order/{idOrder}")
    public ResponseEntity<List<TraceabilityResponseDto>> getOrderTraceability(@PathVariable  Long idOrder) {
        Long idClient = AuthenticationUtils.getAuthenticatedUserId();
        return ResponseEntity.ok(traceabilityHandler.getOrderTraceability(idOrder,idClient));
    }

    @Operation(summary = "Obtener tiempo de registros por Orden")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Registros Obtenidos", content = @Content),
            @ApiResponse(responseCode = "404", description = "Orden no encontrada", content = @Content)
    })
    @GetMapping("/order/{idOrder}/total-duration")
    public ResponseEntity<Long> getOrderTotalDuration(@PathVariable  Long idOrder) {
        return ResponseEntity.ok(traceabilityHandler.getOrderTotalDurationSeconds(idOrder));
    }
}
