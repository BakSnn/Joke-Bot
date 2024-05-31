package ru.baksnn.project.JokeBot.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Aspect
@Component
public class ControllerLogAspect {
    @Pointcut("execution(* ru.baksnn.project.JokeBot.controller.JokesController..*(..))")
    public void jokesController() {
    }

    @Before("jokesController()")
    public void beforeJokesController(JoinPoint joinPoint) {
        List<String> args = Arrays.stream(joinPoint.getArgs())
                .map(Object::toString).toList();
        log.info("Call {} with args {}", joinPoint.getSignature().getName(), args);
    }
}