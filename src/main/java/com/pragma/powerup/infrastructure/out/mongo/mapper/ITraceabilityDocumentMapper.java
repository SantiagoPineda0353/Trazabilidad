package com.pragma.powerup.infrastructure.out.mongo.mapper;

import com.pragma.powerup.domain.model.TraceabilityModel;
import com.pragma.powerup.infrastructure.out.mongo.document.TraceabilityDocument;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface ITraceabilityDocumentMapper {
    TraceabilityDocument toDocument(TraceabilityModel traceabilityModel);
    TraceabilityModel toModel(TraceabilityDocument traceabilityDocument);
}