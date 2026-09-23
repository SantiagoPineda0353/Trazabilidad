package com.pragma.powerup.infrastructure.out.mongo.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection="traceability")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TraceabilityDocument {
    @Id
    private String id;
    private Long idOrder;
    private Long idClient;
    private String clientEmail;
    private LocalDateTime date;
    private String previousStatus;
    private String newStatus;
    private Long idEmployee;
    private String employeeEmail;
}
