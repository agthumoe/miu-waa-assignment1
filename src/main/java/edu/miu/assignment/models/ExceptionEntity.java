package edu.miu.assignment.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@Table(name = "exceptions")
public class ExceptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private long transactionId;
    private LocalDate date;
    private LocalTime time;
    private String principle;
    private String operation;
    @Column(name = "exception_type")
    private String exceptionType;
    private String message;
}
