package com.pragma.powerup.infrastructure.configuration;

import com.pragma.powerup.domain.api.ITraceabilityServicePort;
import com.pragma.powerup.domain.spi.*;
import com.pragma.powerup.domain.usecase.TraceabilityUseCase;
import com.pragma.powerup.infrastructure.out.mongo.adapter.*;
import com.pragma.powerup.infrastructure.out.mongo.mapper.ITraceabilityDocumentMapper;
import com.pragma.powerup.infrastructure.out.mongo.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final ITraceabilityRepository traceabilityRepository;
    private final ITraceabilityDocumentMapper traceabilityDocumentMapper;

    @Bean
    public ITraceabilityPersistencePort traceabilityPersistencePort(){
        return new TraceabilityMongoAdapter(traceabilityRepository,traceabilityDocumentMapper);
    }
    @Bean
    public ITraceabilityServicePort traceabilityServicePort() {
        return new TraceabilityUseCase(traceabilityPersistencePort());
    }
}