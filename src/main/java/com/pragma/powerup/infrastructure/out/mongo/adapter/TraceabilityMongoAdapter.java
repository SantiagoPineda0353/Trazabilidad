package com.pragma.powerup.infrastructure.out.mongo.adapter;

import com.pragma.powerup.domain.model.TraceabilityModel;
import com.pragma.powerup.domain.spi.ITraceabilityPersistencePort;
import com.pragma.powerup.infrastructure.out.mongo.document.TraceabilityDocument;
import com.pragma.powerup.infrastructure.out.mongo.mapper.ITraceabilityDocumentMapper;
import com.pragma.powerup.infrastructure.out.mongo.repository.ITraceabilityRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TraceabilityMongoAdapter implements ITraceabilityPersistencePort {

    private final ITraceabilityRepository traceabilityRepository;
    private final ITraceabilityDocumentMapper traceabilityDocumentMapper;

    @Override
    public TraceabilityModel saveTraceability(TraceabilityModel traceabilityModel) {
        TraceabilityDocument document= traceabilityRepository.save(traceabilityDocumentMapper.toDocument(traceabilityModel));
        return traceabilityDocumentMapper.toModel(document);
    }
}