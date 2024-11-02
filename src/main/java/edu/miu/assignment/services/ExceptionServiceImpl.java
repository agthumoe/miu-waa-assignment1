package edu.miu.assignment.services;

import edu.miu.assignment.models.ExceptionEntity;
import edu.miu.assignment.others.SecurityUtils;
import edu.miu.assignment.repositories.ExceptionRepository;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class ExceptionServiceImpl implements ExceptionService{
    private final ExceptionRepository exceptionRepository;

    @Autowired
    public ExceptionServiceImpl(ExceptionRepository exceptionRepository) {
        this.exceptionRepository = exceptionRepository;
    }

    @Override
    public void log(JoinPoint joinPoint, Exception exception) {
        ExceptionEntity exceptionEntity = new ExceptionEntity();
        exceptionEntity.setOperation(joinPoint.getSignature().getName());
        exceptionEntity.setExceptionType(exception.getClass().getName());
        exceptionEntity.setDate(LocalDate.now());
        exceptionEntity.setTime(LocalTime.now());
        exceptionEntity.setPrinciple(SecurityUtils.getPrinciple());
        exceptionEntity.setMessage(exception.getMessage());
        this.exceptionRepository.save(exceptionEntity);
    }
}
