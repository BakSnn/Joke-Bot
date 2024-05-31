package ru.baksnn.project.JokeBot.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class JokesByIdExecutionAspect {
    @Pointcut("execution(* ru.baksnn.project.JokeBot.service.JokesService.getJokesById(..))")
    public void callJokesByIdController() {
    }

    @Around("callJokesByIdController()")
    public Object aroundJokesByIdController(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = proceedingJoinPoint.proceed();

        long endTime = System.currentTimeMillis();
        log.info("Start with user {}: {} in {} and end {} ms", SecurityContextHolder.getContext().getAuthentication().getName(), proceedingJoinPoint.getSignature().getName(), start, endTime);
        return proceed;}
}
