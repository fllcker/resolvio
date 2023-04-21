package ru.fllcker.resolvio.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* ru.fllcker.resolvio.services.*.*(..))")
    public void logMethodCall(JoinPoint joinPoint) {
        logger.info("Method {} called with arguments {}", joinPoint.getSignature().getName(), joinPoint.getArgs());
    }

    @AfterReturning(pointcut = "execution(* ru.fllcker.resolvio.services.*.*(..))", returning = "result")
    public void logMethodReturn(JoinPoint joinPoint, Object result) {
        logger.info("Method {} returned {}", joinPoint.getSignature().getName(), result);
    }

    @AfterThrowing(pointcut = "execution(* ru.fllcker.resolvio.services.*.*(..))", throwing = "ex")
    public void logMethodException(JoinPoint joinPoint, Exception ex) {
        logger.error("Method {} threw exception {}", joinPoint.getSignature().getName(), ex.getMessage());
    }
}
