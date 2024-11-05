package edu.miu.assignment.services.impl;

import edu.miu.assignment.models.Logger;
import edu.miu.assignment.securities.SecurityUtils;
import edu.miu.assignment.repositories.LoggerRepository;
import edu.miu.assignment.services.LoggerService;
import org.aspectj.lang.JoinPoint;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

@Service
public class LoggerServiceImpl implements LoggerService {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(LoggerServiceImpl.class);
    private final LoggerRepository loggerRepository;

    @Autowired
    public LoggerServiceImpl(LoggerRepository loggerRepository) {
        this.loggerRepository = loggerRepository;
    }

    @Override
    public void log(JoinPoint joinPoint) {
        LOGGER.info("{}({})", joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
        Logger log = new Logger();
        log.setDate(LocalDate.now());
        log.setTime(LocalTime.now());
        log.setPrinciple(SecurityUtils.getPrinciple());
        log.setOperation(joinPoint.getSignature().getName());
        this.loggerRepository.save(log);
    }

    @Override
    public void log(String message) {
        LOGGER.info(message);
    }
}
