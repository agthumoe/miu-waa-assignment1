package edu.miu.assignment.aspects;

import edu.miu.assignment.services.ExceptionService;
import edu.miu.assignment.services.LoggerService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {
    private final LoggerService loggerService;
    private final ExceptionService exceptionService;

    @Autowired
    public LoggerAspect(LoggerService loggerService, ExceptionService exceptionService) {
        this.loggerService = loggerService;
        this.exceptionService = exceptionService;
    }

    @Pointcut("execution( * edu.miu.assignment.controllers.*.*(..))")
    public void controllerAnyMethodPointcut() {
    }

    @Pointcut("execution(* edu.miu.assignment.services.*.*(..))")
    public void serviceAnyMethodPointCut() {
    }

    @Pointcut("@annotation(ExecutionTime)")
    public void executionTimePointCut() {
    }

    @After("controllerAnyMethodPointcut()")
    public void logAfter(JoinPoint joinPoint) {
        this.loggerService.log(joinPoint);
    }

    @Around(value = "executionTimePointCut()")
    public Object logExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.nanoTime();
        var result = proceedingJoinPoint.proceed();
        long finish = System.nanoTime();
        this.loggerService.log(proceedingJoinPoint.getSignature().getName() + " takes: " + (finish - start) / 1000000 + " ms");
        return result;
    }

    @AfterThrowing(value = "controllerAnyMethodPointcut()", throwing = "exception")
    public void logException(JoinPoint joinPoint, Exception exception) {
        this.exceptionService.log(joinPoint, exception);
    }
}
