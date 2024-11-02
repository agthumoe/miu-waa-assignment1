package edu.miu.assignment.services;

import org.aspectj.lang.JoinPoint;

public interface ExceptionService {
    void log(JoinPoint joinPoint, Exception exception);
}
