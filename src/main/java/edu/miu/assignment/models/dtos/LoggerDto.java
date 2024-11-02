package edu.miu.assignment.models.dtos;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class LoggerDto {
    private long transactionId;
    private LocalDate date;
    private LocalTime time;
    private String principle;
    private String operation;
}
