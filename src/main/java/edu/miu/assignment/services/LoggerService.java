package edu.miu.assignment.services;

import org.aspectj.lang.JoinPoint;

public interface LoggerService {
    void log(JoinPoint joinPoint);

    void log(String message);
}
