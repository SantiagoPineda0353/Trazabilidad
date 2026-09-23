package com.pragma.powerup.infrastructure.out.mongo.repository;

import com.pragma.powerup.infrastructure.out.mongo.document.TraceabilityDocument;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ITraceabilityRepository extends MongoRepository<TraceabilityDocument,String> {
}
